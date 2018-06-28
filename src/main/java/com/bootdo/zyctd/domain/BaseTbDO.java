package com.bootdo.zyctd.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 种植基地
 *
 * @author liumingli
 * @date 2018-05-15 17:28:49
 */
public class BaseTbDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //基地名称
    private String name;
    //所属区域id集合
    private String areaId;
    //基地详细地址
    private String detailAddress;
    //父级基地id
    private Integer parentId;
    //父级基地id集合
    private String parentIds;
    //所属客户端服务器id
    private Integer clientId;
    //删除标志
    private Integer isDelete = 1;
    //基地编号
    private String code;
    //排序号
    private Integer orderNum = 0;
    //创建时间
    private Date createTime = new Date();
    //所属客户端服务器ip地址
    private String clientUrl;

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
     * 设置：基地名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：基地名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：所属区域id
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    /**
     * 获取：所属区域id
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * 设置：基地详细地址
     */
    public String getDetailAddress() {
        return detailAddress;
    }

    /**
     * 获取：基地详细地址
     */
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }


    /**
     * 设置：父级基地id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：父级基地id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置：父级基地id集合
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    /**
     * 获取：父级基地id集合
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * 设置：所属客户端服务器id
     */
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    /**
     * 获取：所属客户端服务器id
     */
    public Integer getClientId() {
        return clientId;
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

    /**
     * 设置：基地编号
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取：基地编号
     */
    public String getCode() {
        return code;
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

    public String getClientUrl() {
        return clientUrl;
    }

    public void setClientUrl(String clientUrl) {
        this.clientUrl = clientUrl;
    }
}
