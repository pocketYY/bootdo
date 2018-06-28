package com.bootdo.zyctd.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 摄像头
 *
 * @author liumingli
 * @date 2018-05-15 17:28:49
 */
public class CameraTbDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //摄像头型号
    private String model;
    //摄像头编号
    private String code;
    //摄像头原始流地址
    private String originalStreamUrl;
    //摄像头转码后流地址
    private String turnedStreamUrl;
    //关联种植基地id
    private String baseId;
    //摄像头状态（0：正常运行；10：故障）
    private Integer status = 0;
    //删除标志
    private Integer isDelete = 1;
    //排序号
    private Integer orderNum = 0;
    //创建时间
    private Date createTime = new Date();

    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：摄像头型号
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 获取：摄像头型号
     */
    public String getModel() {
        return model;
    }

    /**
     * 设置：摄像头编号
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取：摄像头编号
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置：摄像头原始流地址
     */
    public void setOriginalStreamUrl(String originalStreamUrl) {
        this.originalStreamUrl = originalStreamUrl;
    }

    /**
     * 获取：摄像头原始流地址
     */
    public String getOriginalStreamUrl() {
        return originalStreamUrl;
    }

    /**
     * 设置：摄像头转码后流地址
     */
    public void setTurnedStreamUrl(String turnedStreamUrl) {
        this.turnedStreamUrl = turnedStreamUrl;
    }

    /**
     * 获取：摄像头转码后流地址
     */
    public String getTurnedStreamUrl() {
        return turnedStreamUrl;
    }

    /**
     * 设置：关联种植基地id
     */
    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }

    /**
     * 获取：关联种植基地id
     */
    public String getBaseId() {
        return baseId;
    }

    /**
     * 设置：摄像头状态（0：正常运行；10：故障）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：摄像头状态（0：正常运行；10：故障）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：删除标志
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取：删除标志
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
