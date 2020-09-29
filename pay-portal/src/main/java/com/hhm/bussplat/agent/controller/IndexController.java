package com.hhm.bussplat.agent.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhm.bussplat.agent.bean.IReturnBean;
import com.hhm.bussplat.entity.AgentOperator;
import com.hhm.bussplat.util.util.DigestUtil;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class IndexController extends BasicController{
    /**
     *跳转到主页
     * @return
     */
    @RequestMapping("/index.do")
    public String toIndexPage() {
        return "main/index";
    }

    /**
     * 登陆代理，跳转到顶级父窗口
     **/
    @RequestMapping("/loginProxy.do")
    public String toLoginProxy() {
        return "login";
    }

    /**
     * 跳转到登录页面
     */
    @RequestMapping("/login.do")
    public String toLoginPage()  {
        return "login";
    }

    @RequestMapping("/")
    public String toPage()  {
        return "login";
    }

    /**
     * 跳转到欢迎页
     * @return
     */
    @RequestMapping("/home.do")
    public String toHomePage() {
        return "main/home";
    }

    /**
     * 跳转到权限不足页面
     * @return
     */
    @RequestMapping("/unauthorized.do")
    public String toUnauthorizedPage() {
        return "error/unauthorized";
    }


    /**
     *  登录验证处理
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping("/loginCheck.do")
    @ResponseBody
    public IReturnBean<Object> loginCheck(String username, String password, String code, HttpServletRequest request){
        log.info("loginCheck username:"+username);
        IReturnBean<Object> returnBean = new IReturnBean<>(IReturnBean.SUCCESS,IReturnBean.SUCCESS_DESC);
        long start = System.currentTimeMillis();
        try {
            //1.用户名不能为空
            if (StringUtils.isEmpty(username)) {
            	log.error("登陆验证失败,原因:用户名不能为空");
                return new IReturnBean<>(IReturnBean.FAIL,"用户名不能为空");
            }
            //2.密码不能为空
            if (StringUtils.isEmpty(password)) {
            	log.error("登陆验证失败,原因:密码不能为空");
                return new IReturnBean<>(IReturnBean.FAIL,"密码不能为空");
            }
            UsernamePasswordToken token = new UsernamePasswordToken(username, DigestUtil.md5(password));
            token.setRememberMe(true);
            SecurityUtils.getSubject().getSession().setTimeout(1000L*60*60*12);
            Subject currentUser = SecurityUtils.getSubject();

            currentUser.login(token);
            if (currentUser.isAuthenticated()) {
                request.getSession().setAttribute("LOGIN_NAME",getCurrentUser());
            }else{
                returnBean = new IReturnBean<>(IReturnBean.FAIL,"用户名或密码不匹配");
            }
        } catch (IncorrectCredentialsException ice) {
        	log.error("登陆验证失败,原因:用户名或密码不匹配");
            returnBean = new IReturnBean<>(IReturnBean.FAIL,"用户名或密码不匹配");
        }catch (AccountException e){
        	log.error("登陆验证失败,原因:用户名或密码不匹配");
            returnBean = new IReturnBean<>(IReturnBean.FAIL,"用户名或密码不匹配");
        }catch (Exception e) {
        	log.error("登陆验证失败,原因:系统登陆异常", e);
            returnBean = new IReturnBean<>(IReturnBean.FAIL,"系统登陆异常");
        } finally {
        	log.info("登陆验证处理结束,用时" + (System.currentTimeMillis() - start) + "毫秒");
        }
        return returnBean;
    }
    /**
     * 退出
     * @return
     */
    @RequestMapping(value = "/logout.do", produces = "text/html; charset=UTF-8")
    public String logout(){
        Subject currentUser = SecurityUtils.getSubject();
        log.info("logout currentUser:"+currentUser.getPrincipals().oneByType(AgentOperator.class).getUsername());
        currentUser.logout();
        return "login";
    }

    public static void main(String[] args) {
    	System.out.println(DigestUtil.md5("123456"));
	}

}
