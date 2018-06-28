package com.bootdo.zyctd.vo;

import com.bootdo.zyctd.domain.BaseTbDO;

import java.io.Serializable;

public class BaseTbDOVO extends BaseTbDO implements Serializable {

    private static final long serialVersionUID = 1L;

    //基地所属区域名称
    private String areaName;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
