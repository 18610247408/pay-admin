package com.hhm.bussplat.agent.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhm.bussplat.entity.AgentOperator;


/**
 * Created by shuyan.qi on 18-9-5.
 */
@Controller
@Scope("prototype")
public class BasicController {

    /**
     * 登录用户名
     */
    public String getCurrentLoginName() {
        Subject currentUser = SecurityUtils.getSubject();
        AgentOperator operator = currentUser.getPrincipals().oneByType(AgentOperator.class);
        return operator.getRealname();
    }

    /**
     * 登陆用户id
     * @return
     */
    public long getCurrentLoginId(){
        Subject currentUser = SecurityUtils.getSubject();
        AgentOperator operator = currentUser.getPrincipals().oneByType(AgentOperator.class);
        return operator.getId();
    }

    /**
     * 登录用户对象
     */
    public AgentOperator getCurrentUser() {
        Subject currentUser = SecurityUtils.getSubject();
        AgentOperator operator = currentUser.getPrincipals().oneByType(AgentOperator.class);
        return operator;
    }

    
    /**
     * 获取用户服务商编号
     */
    public String getAgentNo(){
        Subject currentUser = SecurityUtils.getSubject();
        AgentOperator operator = currentUser.getPrincipals().oneByType(AgentOperator.class);
        return operator.getAgentNo();
    }

}
