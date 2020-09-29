package com.hhm.bussplat.agent.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhm.bussplat.agent.bean.IReturnBean;
import com.hhm.bussplat.agent.bean.LayPage;
import com.hhm.bussplat.entity.AgentOperator;
import com.hhm.bussplat.entity.AgentOperatorRole;
import com.hhm.bussplat.entity.AgentRole;
import com.hhm.bussplat.mapper.AgentOperatorMapper;
import com.hhm.bussplat.mapper.AgentOperatorRoleMapper;
import com.hhm.bussplat.mapper.AgentRoleMapper;
import com.hhm.bussplat.util.constants.CommonStatusConstants;
import com.hhm.bussplat.util.exception.BussplatException;
import com.hhm.bussplat.util.util.DigestUtil;


@Service
public class AgentOperatorService {
	
	@Autowired
    private AgentOperatorMapper agentOperatorMapper;
	@Autowired
    private  AgentRoleMapper agentRoleMapper;
	@Autowired
    private  AgentOperatorRoleMapper  agentOperatorRoleMapper;

    public AgentOperator findOperator(String username,String status) {
        return agentOperatorMapper.findOperator(username,status);
    }

    /**
     * 查询所有角色（分页）
     * @param operator
     * @param layPage
     * @return
     */
    public PageInfo<List<AgentOperator>> allOperatorOfPage(AgentOperator operator, LayPage layPage) {
        PageHelper.startPage(layPage.getPage(),layPage.getLimit());
        List<AgentOperator> list = agentOperatorMapper.findAllOfPage(operator);
        PageInfo<List<AgentOperator>> page = new PageInfo(list);
        return page;
    }

    
    public AgentOperator findById(Long operatorId) {
        return agentOperatorMapper.selectByPrimaryKey(operatorId);
    }

    /**
     *
     * @param operator
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public boolean insertOrUpdateUser(AgentOperator operator) {
        int i = 0;
        if(operator.getId() == null){
        	AgentOperator operator1 = findOperator(operator.getUsername(),CommonStatusConstants.TRUE);
            if(operator1 != null && CommonStatusConstants.TRUE.equals(operator1.getStatus())) throw new BussplatException(IReturnBean.FAIL,"用户名已存在");
            AgentOperator operator2 = agentOperatorMapper.findOperatorByRealName(operator.getRealname(),CommonStatusConstants.TRUE);
            if(operator2 != null && CommonStatusConstants.TRUE.equals(operator2.getStatus())) throw new BussplatException(IReturnBean.FAIL,"姓名已存在");

            operator.setPassword(DigestUtil.md5("123456"));//默认密码
            operator.setStatus(CommonStatusConstants.TRUE);
            operator.setCreateTime(new Date());
            i = agentOperatorMapper.insert(operator);
        }else{
            AgentOperator dbOperator = agentOperatorMapper.selectByPrimaryKey(operator.getId());
            dbOperator.setStatus(operator.getStatus());
            dbOperator.setLastUpdateTime(new Date());
            i = agentOperatorMapper.updateByPrimaryKey(dbOperator);
        }
        return i == 1?true:false;
    }

    public List<AgentRole> findOperatorRoles(Long operatorId) {
        return agentRoleMapper.findOperatorRoles(operatorId);
    }

    public List<AgentRole> findUnOperatorRoles(Long operatorId) {
        List<AgentRole> unOperatorRoles = null;
        AgentRole query = new AgentRole();
        query.setStatus(CommonStatusConstants.TRUE);
        List<AgentRole> allRoles = agentRoleMapper.allRoles(query);
        if(allRoles != null && !allRoles.isEmpty()){
            List<AgentRole> operatorRoles = findOperatorRoles(operatorId);
            if(operatorRoles == null || operatorRoles.isEmpty()){
                unOperatorRoles = allRoles;
            }else{
                Iterator<AgentRole> iterator = allRoles.iterator();
                while (iterator.hasNext()){
                	AgentRole next = iterator.next();
                    for (AgentRole role1:operatorRoles){
                        if(next.getId() == role1.getId()){
                            iterator.remove();
                        }
                    }
                }
                unOperatorRoles = allRoles;
            }
        }
        return unOperatorRoles;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void grantRoles(Long operatorId, String roleIds, String createOperator) {
        //先删除操作员所有角色
        agentOperatorRoleMapper.deleteByOperator(operatorId);
        String[] split = roleIds.split(",");
        if(split.length > 0){
            for(String roleIdStr:split){
                AgentOperatorRole operatorRole = new AgentOperatorRole();
                operatorRole.setOperatorId(operatorId);
                operatorRole.setRoleId(Long.valueOf(roleIdStr));
                operatorRole.setCreator(createOperator);
                operatorRole.setCreateTime(new Date());
                agentOperatorRoleMapper.insert(operatorRole);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void updatePassword(Long operatorId, String newPassword) {
        AgentOperator dbOperator = agentOperatorMapper.selectByPrimaryKey(operatorId);
        dbOperator.setPassword(DigestUtil.md5(newPassword));
        dbOperator.setLastUpdateTime(new Date());
        agentOperatorMapper.updateByPrimaryKey(dbOperator);
    }
	
}
