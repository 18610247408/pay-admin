package com.hhm.bussplat.agent.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.hhm.bussplat.agent.service.AgentMenuService;
import com.hhm.bussplat.agent.service.AgentOperatorService;
import com.hhm.bussplat.entity.AgentMenu;
import com.hhm.bussplat.entity.AgentOperator;
import com.hhm.bussplat.util.constants.CommonStatusConstants;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ShiroDbRealm  extends AuthorizingRealm {
	
	@Autowired
    private AgentMenuService agentMenuService;
	
	@Autowired
    private AgentOperatorService agentOperatorService;
   

    /**
     * 获取认证信息
     * @return
     * @throws
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        String username = userToken.getUsername();
        if (StringUtils.isEmpty(username)) {
            log.error("获取认证信息失败，原因:用户名为空");
            throw new AccountException("用户名为空");
        }
        // 根据登陆用户名查询用户信息
        AgentOperator operator = agentOperatorService.findOperator(username, CommonStatusConstants.TRUE);
        if (operator == null) {
            throw new AccountException("用户信息为空");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(operator, operator.getPassword(), getName());
        if (null != info) {
        	log.info("用户认证通过:登陆用户名:" + operator.getRealname());
        }
        return info;
    }

    /**
     * 获取授权信息
     * @return
     * @throws
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("Principal对象不能为空");
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        AgentOperator operator = (AgentOperator) getAvailablePrincipal(principals);
        log.info("加载用户权限信息，当前登陆用户名:" + operator.getUsername());

        List<AgentMenu> menus = agentMenuService.allMenusOfOperator(operator.getId());
        //赋予用户菜单
        if(menus != null && !menus.isEmpty()){
            for (AgentMenu menu:menus) {
                info.addStringPermission(menu.getLabel());
            }
        }
        return  info;
    }


    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo (){
    	log.info("清除所有账号缓存");
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null){
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }
}
