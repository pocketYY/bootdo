package com.bootdo.zyctd.vo;

import com.bootdo.zyctd.domain.CameraTbDO;

import java.io.Serializable;

public class CameraTbDOVO extends CameraTbDO implements Serializable {

    private static final long serialVersionUID = 1L;

    //摄像头所属基地名称
    private String baseName;

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }
}
