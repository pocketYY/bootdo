package com.bootdo.zyctd.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 服务端服务器信息
 * 
 * @author liumingli
 * @date 2018-05-15 17:28:49
 */
public class ServerTbDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//服务器型号
	private String model;
	//服务器ip地址
	private String ip;
	//服务器状态（0：正常运行；10：故障）
	private Integer status=0;
	//删除标志
	private Integer isDelete=1;

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
	 * 设置：服务器型号
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * 获取：服务器型号
	 */
	public String getModel() {
		return model;
	}
	/**
	 * 设置：服务器ip地址
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * 获取：服务器ip地址
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * 设置：服务器状态（0：正常运行；10：故障）
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：服务器状态（0：正常运行；10：故障）
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
}
