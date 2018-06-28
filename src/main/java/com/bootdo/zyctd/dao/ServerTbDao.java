package com.bootdo.zyctd.dao;

import com.bootdo.zyctd.domain.ServerTbDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 服务端服务器信息
 * @author liumingli
 * @date 2018-05-15 17:28:49
 */
@Mapper
public interface ServerTbDao {

	ServerTbDO get(Integer id);
	
	List<ServerTbDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ServerTbDO serverTb);
	
	int update(ServerTbDO serverTb);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
