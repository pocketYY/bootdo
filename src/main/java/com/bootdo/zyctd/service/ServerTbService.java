package com.bootdo.zyctd.service;

import com.bootdo.zyctd.domain.ServerTbDO;

import java.util.List;
import java.util.Map;

/**
 * 服务端服务器信息
 * 
 * @author liumingli
 * @date 2018-05-15 17:28:49
 */
public interface ServerTbService {
	
	ServerTbDO get(Integer id);
	
	List<ServerTbDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ServerTbDO serverTb);
	
	int update(ServerTbDO serverTb);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
