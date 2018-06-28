package com.bootdo.zyctd.dao;

import com.bootdo.zyctd.domain.AreaTbDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 区域
 * @author liumingli
 * @date 2018-05-15 17:28:49
 */
@Mapper
public interface AreaTbDao {

	AreaTbDO get(Integer id);
	
	List<AreaTbDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AreaTbDO areaTb);
	
	int update(AreaTbDO areaTb);
	
	int remove(Integer id);
	
	int batchRemove(int[] ids);

	String getIdByParentIds(Integer id);
}
