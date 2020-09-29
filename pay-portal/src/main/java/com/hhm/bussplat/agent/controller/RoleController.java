package com.hhm.bussplat.agent.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.hhm.bussplat.agent.bean.LayPage;
import com.hhm.bussplat.agent.bean.LayResult;
import com.hhm.bussplat.agent.bean.LayTree;
import com.hhm.bussplat.agent.config.ShiroDbRealm;
import com.hhm.bussplat.agent.service.AgentRoleService;
import com.hhm.bussplat.entity.AgentRole;
import com.hhm.bussplat.util.exception.BussplatException;
import com.hhm.bussplat.util.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("role")
public class RoleController extends BasicController {
    @Resource
    private AgentRoleService agentRoleService;

    /**
     * 角色管理-跳转
     * @return
     */
    @RequestMapping(value = "/roleListPage.do", produces = "text/html; charset=UTF-8")
    public String roleListPage(){
        return "role/roleList";
    }

    /**
     * 查询角色列表
     * @return
     */
    @RequestMapping(value = "/findRoleList.do", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String findRoleList(HttpServletRequest request, LayPage layPage, AgentRole role){
        try {
            PageInfo<List<AgentRole>> page = agentRoleService.allRolesOfPage(role,layPage);
            return JsonUtil.toJson(LayResult.success((long)page.getTotal(),page.getList()));
        } catch (Exception e) {
            log.error("findRoleList menu error:" + e.getMessage());
            return JsonUtil.toJson(LayResult.error());
        }
    }

    /**
     * 跳转到新增角色页面
     */
    @RequestMapping(value = "/roleAdd.do", produces = "text/html; charset=UTF-8")
    public String roleAdd(HttpServletRequest request){
        request.setAttribute("pageFlag", "addPage");
        return "role/roleAddAndUpdate";
    }

    /**
     * 编辑 Role
     */
    @RequestMapping(value = "/roleUpdate.do", produces = "text/html; charset=UTF-8")
    public String roleUpdate(HttpServletRequest request, Long roleId){
        AgentRole role = agentRoleService.findRoleById(roleId);
        request.setAttribute("pageFlag", "updatePage");
        request.setAttribute("role",role);
        return "role/roleAddAndUpdate";
    }

    /**
     * 新增或者修改 角色
     */
    @RequestMapping(value = "/ajaxSaveAndEditRole.do", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String ajaxSaveAndEditRole(HttpServletRequest request,AgentRole role){
        LayResult result = null;
        try {
            boolean b = agentRoleService.insertOrUpdateRole(role);
            if(b){
                result = LayResult.success();
            }else{
                result = LayResult.fail();
            }
        }catch (BussplatException de){
            result  = LayResult.fail(de.getMessage());
        }catch (Exception e){
            log.error("新增或者修改 error id={}",role.getId(),e);
            result =  LayResult.error();
        }
        return JsonUtil.toJson(result);
    }

    /**
     * 角色权限 分配
     */
    @RequestMapping(value = "/roleGrant.do", produces = "text/html; charset=UTF-8")
    public String roleGrant(HttpServletRequest request,Long roleId){
        AgentRole PortalRoleModel = agentRoleService.toRoleGrant(roleId);
        request.setAttribute("role",PortalRoleModel);
        return "role/roleGrant";
    }

    /**
     * 获取有效的角色权限信息
     */
    @RequestMapping("/ajaxRoleTreeList.do")
    @ResponseBody
    public List<LayTree> ajaxResourceTreeList(){
        return agentRoleService.findMenuAllTree();
    }

    /**
     * 角色授权
     */
    @RequestMapping(value = "/ajaxEditRoleGrant.do", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String ajaxEditRoleGrant(Long roleId, @RequestParam(value = "resourceIds[]",required = false) Long[] resourceIds ){
        LayResult result = null;
        try {
            agentRoleService.roleGrant( getCurrentLoginName(),roleId, resourceIds);
            RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
            ShiroDbRealm userRealm = (ShiroDbRealm)securityManager.getRealms().iterator().next();
            userRealm.clearAllCachedAuthorizationInfo();
            result = LayResult.success();
        }catch (Exception e){
            log.error("ajaxEditRoleGrant error:"+e.getMessage());
            result = LayResult.error();
        }
        return JsonUtil.toJson(result);
    }
    
}
