package com.hhm.bussplat.agent.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.hhm.bussplat.agent.bean.LayPage;
import com.hhm.bussplat.agent.bean.LayResult;
import com.hhm.bussplat.agent.config.ShiroDbRealm;
import com.hhm.bussplat.agent.service.AgentOperatorService;
import com.hhm.bussplat.entity.AgentOperator;
import com.hhm.bussplat.entity.AgentRole;
import com.hhm.bussplat.util.exception.BussplatException;
import com.hhm.bussplat.util.util.DigestUtil;
import com.hhm.bussplat.util.util.JsonUtil;
import com.hhm.bussplat.util.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *  操作员
 * Created by shuyan.qi on 18-9-5.
 */
@Slf4j
@Controller
@RequestMapping("operator")
public class OperatorController extends BasicController{

    @Resource
    private AgentOperatorService agentOperatorService;

    /**
     * 操作员管理-跳转
     * @return
     */
    @RequestMapping(value = "/operatorListPage.do", produces = "text/html; charset=UTF-8")
    public String operatorListPage(){
        return "operator/operatorList";
    }

    /**
     * 查询操作员列表
     * @return
     */
    @RequestMapping(value = "/findOperatorList.do", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String operatorList(HttpServletRequest request, LayPage layPage, AgentOperator operator){
        LayResult result = null;
        try {
            PageInfo<List<AgentOperator>> page = agentOperatorService.allOperatorOfPage(operator,layPage);
            result = LayResult.success((long)page.getTotal(),page.getList());
        } catch (Exception e) {
            log.error("operatorList error:" + e.getMessage());
            result = LayResult.error(null,null);
        }
        return JsonUtil.toJson(result);
    }

    /**
     * 新增 页面
     */
    @RequestMapping(value = "/operatorAdd.do", produces = "text/html; charset=UTF-8")
    public String operatorAdd(HttpServletRequest request){
        request.setAttribute("pageFlag", "addPage");
        return "operator/operatorAddAndUpdate";
    }

    /**
     * 编辑 user
     */
    @RequestMapping(value = "/operatorUpdate.do", produces = "text/html; charset=UTF-8")
    public String userUpdate(HttpServletRequest request, Long operatorId){
        AgentOperator operator = agentOperatorService.findById(operatorId);
        request.setAttribute("pageFlag", "updatePage");
        request.setAttribute("operator",operator);
        return "operator/operatorAddAndUpdate";
    }

    /**
     * 新增或者修改 角色
     */
    @RequestMapping(value = "/ajaxSaveAndEditOperator.do", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String ajaxSaveAndEditRole(HttpServletRequest request,AgentOperator operator){
        LayResult result = null;
        try {
            operator.setCreateOperator(getCurrentLoginName());
            boolean b = agentOperatorService.insertOrUpdateUser(operator);
            if(b){
                result = LayResult.success();
            }else{
                result = LayResult.fail("保存失败");
            }
        }catch (BussplatException de){
            result = LayResult.error(de.getMessage());
        }catch (Exception e){
            log.error("ajaxSaveAndEditRole error:"+e.getMessage());
            result = LayResult.error();
        }
        return JsonUtil.toJson(result);
    }

    /**
     *  用户分配角色信息
     */
    @RequestMapping(value = "/operatorGrant.do", produces = "text/html; charset=UTF-8")
    public String userGrant(HttpServletRequest request,Long operatorId){
        request.setAttribute("operatorId",operatorId);
        return "operator/operatorGrant";
    }

    /**
     *  查询未分配的角色  用以给用户分配角色时显示
     */
    @RequestMapping(value = "/ajaxUndistributedRoleList.do", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String ajaxUndistributedRoleList(Long operatorId){
        List<AgentRole> roles = agentOperatorService.findUnOperatorRoles(operatorId);
        if(roles != null && !roles.isEmpty()){
            return JsonUtil.toJson(roles);
        }
        return null;
    }

    /**
     * 查询状态为有效,已分配的角色信息(用已用户分配角色显示)
     */
    @RequestMapping(value = "/ajaxDeceasedRoleList.do", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String ajaxDeceasedRoleList(Long operatorId){
        List<AgentRole> roles = agentOperatorService.findOperatorRoles(operatorId);
        if(roles != null && !roles.isEmpty()){
            return JsonUtil.toJson(roles);
        }
        return null;
    }

    /**
     *  保存已处理的角色信息
     */
    @RequestMapping(value = "/ajaxSaveOperatorRole.do", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String ajaxSaveOperatorRole(@RequestParam Long operatorId, String roleIds){
        LayResult result = null;
        try {
            if(StringUtil.isBlank(roleIds)){
                return JsonUtil.toJson(LayResult.fail("未选择角色"));
            }
            agentOperatorService.grantRoles(operatorId, roleIds, this.getCurrentLoginName());
            RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
            ShiroDbRealm userRealm = (ShiroDbRealm) securityManager.getRealms().iterator().next();
            userRealm.clearAllCachedAuthorizationInfo();
            result = LayResult.success();
        }catch (Exception e){
            log.error("ajaxSaveOperatorRole error ",e);
            result = LayResult.error();
        }
        return JsonUtil.toJson(result);
    }

    /**
     *  修改密码跳转
     */
    @RequestMapping(value = "/toUpdatePassword.do", produces = "text/html; charset=UTF-8")
    public String toUpdatePassword(HttpServletRequest request){
        Subject currentUser = SecurityUtils.getSubject();
        AgentOperator user = currentUser.getPrincipals().oneByType(AgentOperator.class);
        log.info("updatePassword currentUser:"+user.getUsername());
        request.setAttribute("operator",user);
        return "operator/updatePassword";
    }

    /**
     *  修改密码
     */
    @RequestMapping(value = "/updatePassword.do", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String updatePassword(HttpServletRequest request, AgentOperator operator, String newPassword){
        LayResult result = null;
        try{
        	log.info(" updatePassword 修改用户：{}",operator.getUsername());
            Subject currentUser = SecurityUtils.getSubject();
            AgentOperator user = currentUser.getPrincipals().oneByType(AgentOperator.class);
            log.info(" updatePassword 登录用户:{}",user.getUsername());

            if(!user.getUsername().equals(operator.getUsername())){
            	log.error("updatePassword 登录和修改不是同一用户，禁止修改");
                return JsonUtil.toJson(LayResult.fail("禁止修改他人账户密码"));
            }

            if(!user.getPassword().equals(DigestUtil.md5(operator.getPassword()))){
            	log.error("updatePassword 原始密码错误");
                return JsonUtil.toJson(LayResult.fail("原始密码错误"));
            }
            agentOperatorService.updatePassword(operator.getId(),newPassword);
            currentUser.logout();
            result = LayResult.success();
        }catch (Exception e){
        	log.error("updatePassword error:"+e);
            result = LayResult.error();
        }
        return JsonUtil.toJson(result);
    }
}
