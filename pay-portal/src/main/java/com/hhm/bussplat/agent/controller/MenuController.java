package com.hhm.bussplat.agent.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.hhm.bussplat.agent.bean.LayPage;
import com.hhm.bussplat.agent.bean.LayResult;
import com.hhm.bussplat.agent.bean.resultBean.MenuBean;
import com.hhm.bussplat.agent.bean.resultBean.MenuChild;
import com.hhm.bussplat.agent.service.AgentMenuService;
import com.hhm.bussplat.entity.AgentMenu;
import com.hhm.bussplat.util.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("menu")
public class MenuController extends BasicController{

    @Resource
    private AgentMenuService agentMenuService;

    /**
     * 该用户下的菜单列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/menuLeft.do", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String menuLeft(HttpServletRequest request){
        try {
            //一级菜单
        	log.error("getCurrentLoginId-----------------------{}",getCurrentLoginId());
            List<AgentMenu> menus = agentMenuService.firstMenusOfOperator(getCurrentLoginId());
            if (menus != null && !menus.isEmpty()){
                List<MenuBean> allMenu = new ArrayList<>();
                for (AgentMenu menu : menus) {
                    MenuBean bean  = new MenuBean();
                    bean.setName(menu.getName());
                    bean.setParentId(menu.getParentId());
                    bean.setIcon(menu.getIcon());
                    //二级菜单
                    List<AgentMenu> childMenus = agentMenuService.leafMenusOfOperatorAndParent(getCurrentLoginId(),menu.getId());
                    if(childMenus != null && !childMenus.isEmpty()){
                        List<MenuChild> childList = new ArrayList<>();
                        for (AgentMenu childMenu : childMenus) {
                            MenuChild child = new MenuChild();
                            child.setName(childMenu.getName());
                            child.setUrl(childMenu.getUrl());
                            child.setIcon(childMenu.getIcon());
                            childList.add(child);
                        }
                        bean.setChildren(childList);
                    }
                    allMenu.add(bean);
                }
                return JsonUtil.toJson(allMenu);
            }
        }catch (Exception e){
            log.error("左菜单栏menuLeft error:",e);
        }
        return null;
    }

    /**
     * 获取父级菜单
     * @param resType
     * @param levels
     * @return
     */
    @RequestMapping(value = "/findParentMenu.do", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String findParentMenu(String resType, String levels){
        try{
            List<AgentMenu> menus = agentMenuService.findParentMenu(resType,levels);
            if(menus != null && !menus.isEmpty()){
                return JsonUtil.toJson(LayResult.success((long)menus.size(),menus));
            }
        }catch (Exception e){
            log.error("findParentMenu err",e);
            return JsonUtil.toJson(LayResult.error());
        }
        return JsonUtil.toJson(LayResult.fail());
    }

    /**
     * 获取所有的图标信息
     */
    @RequestMapping(value = "/iconImage.do", produces = "text/html; charset=UTF-8")
    public String iconImage(){
        return "menu/menuIconPage";
    }
    
    /**
     * 菜单管理-跳转
     * @return
     */
    @RequestMapping(value = "/menuListPage.do", produces = "text/html; charset=UTF-8")
    public String menuListPage(){
        return "menu/menuList";
    }

    /**
     * 查询菜单列表
     * @param request
     * @param page
     * @return
     */
    @RequestMapping(value = "/findMenuList.do", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String findMenuList(HttpServletRequest request, LayPage page){
        try {
            AgentMenu query = new AgentMenu();
            query.setName(request.getParameter("name"));
            query.setResType(request.getParameter("resType"));
            query.setLevels(request.getParameter("levels"));
            
            PageInfo<List<AgentMenu>> result = agentMenuService.allMenuOfPage(query,page);
            return JsonUtil.toJson(LayResult.success(result.getTotal(),result.getList()));
        } catch (Exception e) {
            log.error("查询菜单列表 error:" + e.getMessage());
            return JsonUtil.toJson(LayResult.error());
        }
    }

    /**
     * 跳转到新增菜单页面
     */
    @RequestMapping(value = "/toInsertMenu.do", produces = "text/html; charset=UTF-8")
    public String toInsertMenu(HttpServletRequest request){
        request.setAttribute("pageFlag", "addPage");
        return "menu/menuAddAndEdit";
    }

    /**
     *  跳转到修改菜单页面
     */
    @RequestMapping(value = "/toUpdateMenu.do", produces = "text/html; charset=UTF-8")
    public String userUpdatePage(HttpServletRequest request,Long resId){
        AgentMenu  menu = agentMenuService.findMenuById(resId);
        AgentMenu query = new AgentMenu();
        query.setStatus("TRUE");
        query.setResType("0");
        query.setParentId(resId);
        List<AgentMenu> childMenus = agentMenuService.allMenus(query);//子菜单列表（不包括按钮）

        request.setAttribute("pageFlag", "updatePage");//修改页面标识
        request.setAttribute("menu", menu);
        request.setAttribute("resParentCount", childMenus == null?0:childMenus.size());
        return "menu/menuAddAndEdit";
    }

    /**
     * 新增或者修改菜单
     */
    @RequestMapping(value = "/ajaxSaveAndEditMenu.do", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String ajaxSaveAndEdit(HttpServletRequest request,AgentMenu menu){
        try {
            menu.setOperator(getCurrentLoginName());
            boolean b = agentMenuService.createOrUpdate(menu);
            if(b){
                return JsonUtil.toJson(LayResult.success());
            }
        }catch (Exception e){
            log.error("ajaxSaveAndEditMenu error:"+e.getMessage());
            return JsonUtil.toJson(LayResult.error());
        }
        return JsonUtil.toJson(LayResult.fail());
    }

}
