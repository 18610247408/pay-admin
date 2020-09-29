package com.hhm.bussplat.agent.bean.resultBean;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class MenuBean implements Serializable {

	private static final long serialVersionUID = 5664474605919622817L;

	private String name;
	private Long parentId;
	private String icon;
	private List<MenuChild> children;

}
