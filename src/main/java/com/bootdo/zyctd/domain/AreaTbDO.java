package com.bootdo.zyctd.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 区域
 * 
 * @author liumingli
 * @date 2018-05-15 17:28:49
 */
public class AreaTbDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//区域名称
	private String name;
	//区域父级id
	private Integer parentId;
	//区域父级id集合
	private String parentIds;
	//区域行政级别（0：国家；1：省；2：市；3：区县；4：乡镇；5：村社）
	private Integer level=0;
	//删除标志
	private Integer isDelete=1;
	//排序号
	private Integer orderNum=0;
	//创建时间
	private Date createTime=new Date();

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
	 * 设置：区域名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：区域名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：区域父级id
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：区域父级id
	 */
	public Integer getParentId() {
		return parentId;
	}
	/**
	 * 设置：区域父级id集合
	 */
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	/**
	 * 获取：区域父级id集合
	 */
	public String getParentIds() {
		return parentIds;
	}
	/**
	 * 设置：区域行政级别（0：国家；1：省；2：市；3：区县；4：乡镇；5：村社）
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * 获取：区域行政级别（0：国家；1：省；2：市；3：区县；4：乡镇；5：村社）
	 */
	public Integer getLevel() {
		return level;
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
