package com.bootdo.zyctd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.zyctd.dao.ServerTbDao;
import com.bootdo.zyctd.domain.ServerTbDO;
import com.bootdo.zyctd.service.ServerTbService;



@Service
public class ServerTbServiceImpl implements ServerTbService {
	@Autowired
	private ServerTbDao serverTbDao;
	
	@Override
	public ServerTbDO get(Integer id){
		return serverTbDao.get(id);
	}
	
	@Override
	public List<ServerTbDO> list(Map<String, Object> map){
		return serverTbDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return serverTbDao.count(map);
	}
	
	@Override
	public int save(ServerTbDO serverTb){
		return serverTbDao.save(serverTb);
	}
	
	@Override
	public int update(ServerTbDO serverTb){
		return serverTbDao.update(serverTb);
	}
	
	@Override
	public int remove(Integer id){
		return serverTbDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return serverTbDao.batchRemove(ids);
	}
	
}
