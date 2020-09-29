package com.hhm.bussplat.agent.service;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhm.bussplat.agent.bean.LayPage;
import com.hhm.bussplat.entity.AgentMenu;
import com.hhm.bussplat.mapper.AgentMenuMapper;
import com.hhm.bussplat.util.constants.CommonStatusConstants;
import com.hhm.bussplat.util.util.StringUtil;

@Service
public class AgentMenuService {

	@Autowired
	private AgentMenuMapper agentMenuMapper;

	/**
	 * 查询用户所有菜单
	 * 
	 * @param operatorId 用户id
	 * @return
	 */
	public List<AgentMenu> allMenusOfOperator(Long operatorId) {
		return agentMenuMapper.allMenusOfOperator(operatorId);
	}

	/**
	 * 查询角色所有菜单
	 * 
	 * @param roleId 角色id
	 * @return
	 */
	public List<AgentMenu> allMenusOfRole(Long roleId) {
		return agentMenuMapper.allMenusOfRole(roleId);
	}

	/**
	 * 查询用户的一级菜单
	 * 
	 * @param operatorId 用户id
	 * @return
	 */
	public List<AgentMenu> firstMenusOfOperator(Long operatorId) {
		return agentMenuMapper.firstMenusOfOperator(operatorId);
	}

	/**
	 * 查询用户的子级菜单
	 * 
	 * @param operatorId 用户id
	 * @param parentId   父级菜单id
	 * @return
	 */
	public List<AgentMenu> leafMenusOfOperatorAndParent(Long operatorId, Long parentId) {
		return agentMenuMapper.leafMenusOfOperatorAndParent(operatorId, parentId);
	}

	/**
	 * 查询所有菜单（分页）
	 * 
	 * @param query
	 * @param queryPage
	 * @return
	 */
	public PageInfo<List<AgentMenu>> allMenuOfPage(AgentMenu query, LayPage layPage) {
		PageHelper.startPage(layPage.getPage(),layPage.getLimit());
		List<AgentMenu> list = agentMenuMapper.findAllMenus(query);
		PageInfo<List<AgentMenu>> page = new PageInfo(list);
		return page;
	}

	/**
	 * 查询所有菜单
	 * 
	 * @param query
	 * @return
	 */
	public List<AgentMenu> allMenus(AgentMenu query) {
		return agentMenuMapper.allMenus(query);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public boolean createOrUpdate(AgentMenu menu) {
		int i = 0;
		menu.setCreateTime(new Date());
		if (menu.getId() == null) {
			if ("1".equals(menu.getLevels())) {
				menu.setParentId(0L);
				menu.setIsLeaf("N");
			} else {
				menu.setIsLeaf("Y");
			}
			i = agentMenuMapper.insert(menu);
		} else {
			i = agentMenuMapper.updateByPrimaryKey(menu);
		}
		return i == 1 ? true : false;
	}

	public AgentMenu findMenuById(Long menuId) {
		return agentMenuMapper.selectByPrimaryKey(menuId);
	}

	/**
	 * 查询父级菜单列表
	 * 
	 * @param resType 菜单类型（0：菜单，1：按钮）
	 * @param levels  菜单级别（1、2）
	 * @return
	 */
	public List<AgentMenu> findParentMenu(String resType, String levels) {
		List<AgentMenu> menus = null;
		if (StringUtil.isNotBlank(resType) && StringUtil.isNotBlank(levels)) {
			AgentMenu query = new AgentMenu();
			if ("0".equals(resType)) {
				if ("1".equals(levels)) {
					// 一级菜单没有父级菜单
					return menus;
				} else if ("2".equals(levels)) {
					// 二级菜单的父级为一级菜单
					query.setResType("0");
					query.setLevels("1");
					query.setStatus(CommonStatusConstants.TRUE);
				}
			} else if ("1".equals(resType)) {
				// 按钮的父级菜单为二级菜单
				query.setResType("0");
				query.setLevels("2");
				query.setStatus(CommonStatusConstants.TRUE);
			}
			menus = agentMenuMapper.allMenus(query);
		}
		return menus;
	}
}
