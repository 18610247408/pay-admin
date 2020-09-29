package com.hhm.bussplat.agent.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhm.bussplat.agent.bean.IReturnBean;
import com.hhm.bussplat.agent.bean.LayPage;
import com.hhm.bussplat.agent.bean.LayTree;
import com.hhm.bussplat.entity.AgentMenu;
import com.hhm.bussplat.entity.AgentRole;
import com.hhm.bussplat.entity.AgentRoleMenu;
import com.hhm.bussplat.mapper.AgentRoleMapper;
import com.hhm.bussplat.mapper.AgentRoleMenuMapper;
import com.hhm.bussplat.util.constants.CommonStatusConstants;
import com.hhm.bussplat.util.exception.BussplatException;
import com.hhm.bussplat.util.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service
public class AgentRoleService {
    @Resource
    private AgentRoleMapper agentRoleMapper;
    @Resource
    private AgentRoleMenuMapper agentRoleMenuMapper;
    @Resource
    private AgentMenuService agentMenuService;

    /**
     * 查询用户角色
     * @param operatorId 用户id
     * @return
     */
    public List<AgentRole> operatorRoles(Long operatorId) {
        return agentRoleMapper.findOperatorRoles(operatorId);
    }

    /**
     * 查询所有角色（分页）
     * @param query
     * @param layPage
     * @return
     */
    public PageInfo<List<AgentRole>> allRolesOfPage(AgentRole query, LayPage layPage) {
    	PageHelper.startPage(layPage.getPage(), layPage.getLimit());
        List<AgentRole> list = agentRoleMapper.findAllRolesOfPage(query);
        PageInfo<List<AgentRole>> page = new PageInfo(list);
        return page;
    }

    public AgentRole findRoleById(Long roleId) {
        return agentRoleMapper.selectByPrimaryKey(roleId);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public boolean insertOrUpdateRole(AgentRole role) {
        //校验唯一性
        List<AgentRole> roles = agentRoleMapper.checkOnlyOnlyOne(role.getName(),role.getRoleCode());
        if(role != null && !roles.isEmpty()){
            if(role.getId() == null){
                //新增
                throw new BussplatException(IReturnBean.FAIL,"该角色已存在");
            }else{
                //修改,排除自身
                if(!(roles.size() == 1 && roles.get(0).getId() == role.getId())) throw new BussplatException(IReturnBean.FAIL,"该角色已存在");
            }
        }

        int i = 0;
        if(role.getId() == null){
            i = agentRoleMapper.insert(role);
        }else{
        	AgentRole dbRole = agentRoleMapper.selectByPrimaryKey(role.getId());
            dbRole.setName(role.getName());
            dbRole.setStatus(role.getStatus());
            dbRole.setRemark(role.getRemark());
            i = agentRoleMapper.updateByPrimaryKey(dbRole);
        }
        if(i == 1){
            return true;
        }
        return false;
    }

    public AgentRole toRoleGrant(Long roleId) {
    	AgentRole role = agentRoleMapper.selectByPrimaryKey(roleId);
        if(role != null){
            List<AgentMenu> menus = agentMenuService.allMenusOfRole(roleId);
            if(menus != null && !menus.isEmpty()){
                StringBuffer resourceIds = new StringBuffer();
                StringBuffer resourceNames = new StringBuffer();
                for (AgentMenu menu : menus) {
                    resourceIds.append(menu.getId()).append(",");
                    resourceNames.append(menu.getName()).append(",");
                }
                //将拼接后的资源信息的最后一个逗号删除
                if(resourceIds.length()>0){
                    resourceIds.deleteCharAt(resourceIds.length()-1);
                }
                if(resourceNames.length()>0){
                    resourceNames.deleteCharAt(resourceNames.length()-1);
                }
                role.setResourceIds(resourceIds.toString());
                role.setResourceNames(resourceNames.toString());
            }
        }
        return role;
    }

    public List<LayTree> findMenuAllTree() {
        List<LayTree> trees = null;
        AgentMenu query = new AgentMenu();
        query.setStatus(CommonStatusConstants.TRUE);
        List<AgentMenu> menus = agentMenuService.allMenus(query);
        if(menus != null && !menus.isEmpty()){
            trees = new ArrayList<>(menus.size());
            for (AgentMenu menu:menus) {
                LayTree tree = new LayTree();
                tree.setId(menu.getId().intValue());
                if (null != menu.getParentId()) {
                    tree.setPid(menu.getParentId().intValue());
                }
                tree.setText(menu.getName());
                tree.setIconCls(menu.getIcon());
                Map<String, Object> attr = new HashMap<String, Object>();
                attr.put("url", menu.getUrl());
                tree.setAttributes(attr);
                trees.add(tree);
            }
        }
        return trees;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void roleGrant(String operator, Long roleId, Long[] menuIds) {
        try {
            //清除该角色和菜单的关系
            agentRoleMenuMapper.delete(roleId,null);
            for (Long menuId:menuIds) {
            	AgentRoleMenu roleMenu = new AgentRoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menuId);
                roleMenu.setCreator(operator);
                roleMenu.setCreateTime(new Date());
                agentRoleMenuMapper.insert(roleMenu);
            }
        } catch (Exception e) {
            log.error("角色授权失败 roleId={},operator={},menuIds={}",roleId,operator, JsonUtil.toJson(menuIds),e);
            throw e;
        }
    }
}
