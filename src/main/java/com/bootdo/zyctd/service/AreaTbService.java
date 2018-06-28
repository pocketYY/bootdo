package com.bootdo.zyctd.service;

import com.bootdo.zyctd.domain.AreaTbDO;

import java.util.List;
import java.util.Map;

/**
 * 区域
 * 
 * @author liumingli
 * @date 2018-05-15 17:28:49
 */
public interface AreaTbService {
	
	AreaTbDO get(Integer id);
	
	List<AreaTbDO> list(Map<String, Object> map);

	List<AreaTbDO> apiList(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AreaTbDO areaTb);
	
	int update(AreaTbDO areaTb);
	
	int remove(Integer id);
	
	int batchRemove(int[] ids);
}
