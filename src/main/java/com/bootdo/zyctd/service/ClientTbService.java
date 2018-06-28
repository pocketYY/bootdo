package com.bootdo.zyctd.service;

import com.bootdo.zyctd.domain.ClientTbDO;

import java.util.List;
import java.util.Map;

/**
 * 客户端服务器信息
 * 
 * @author liumingli
 * @date 2018-05-15 17:28:49
 */
public interface ClientTbService {
	
	ClientTbDO get(Integer id);
	
	List<ClientTbDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ClientTbDO clientTb);
	
	int update(ClientTbDO clientTb);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
