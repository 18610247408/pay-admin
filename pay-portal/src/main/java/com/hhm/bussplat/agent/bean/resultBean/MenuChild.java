package com.hhm.bussplat.agent.bean.resultBean;

import java.io.Serializable;

import lombok.Data;

@Data
public class MenuChild implements Serializable {
	
	private static final long serialVersionUID = -2819937549826864567L;

    private String name;
    private String url;
    private String icon;

}
