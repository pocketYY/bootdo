package com.bootdo.zyctd.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 客户端服务器信息
 * 
 * @author liumingli
 * @date 2018-05-15 17:28:49
 */
public class ClientTbDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//删除标志
	private Integer id;
	//客户端服务器型号
	private String model;
	//ip地址
	private String ip;
	//服务器状态（0：正常运行；10：故障）
	private Integer status=0;
	//当前正在推流的摄像头个数
	private Integer connectNums;
	//当前正在推流的 基地编号/摄像头编号
	private String connectCamera;
	//关联服务端服务器id
	private Integer serverId;
	//
	private Integer isDelete=1;

	/**
	 * 设置：删除标志
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：删除标志
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：客户端服务器型号
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * 获取：客户端服务器型号
	 */
	public String getModel() {
		return model;
	}
	/**
	 * 设置：ip地址
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * 获取：ip地址
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
	 * 设置：当前正在推流的摄像头个数
	 */
	public void setConnectNums(Integer connectNums) {
		this.connectNums = connectNums;
	}
	/**
	 * 获取：当前正在推流的摄像头个数
	 */
	public Integer getConnectNums() {
		return connectNums;
	}
	/**
	 * 设置：当前正在推流的 基地编号/摄像头编号
	 */
	public void setConnectCamera(String connectCamera) {
		this.connectCamera = connectCamera;
	}
	/**
	 * 获取：当前正在推流的 基地编号/摄像头编号
	 */
	public String getConnectCamera() {
		return connectCamera;
	}
	/**
	 * 设置：关联服务端服务器id
	 */
	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}
	/**
	 * 获取：关联服务端服务器id
	 */
	public Integer getServerId() {
		return serverId;
	}
	/**
	 * 设置：
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
}
