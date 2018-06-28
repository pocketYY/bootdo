package com.bootdo.zyctd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.zyctd.dao.ClientTbDao;
import com.bootdo.zyctd.domain.ClientTbDO;
import com.bootdo.zyctd.service.ClientTbService;



@Service
public class ClientTbServiceImpl implements ClientTbService {
	@Autowired
	private ClientTbDao clientTbDao;
	
	@Override
	public ClientTbDO get(Integer id){
		return clientTbDao.get(id);
	}
	
	@Override
	public List<ClientTbDO> list(Map<String, Object> map){
		return clientTbDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return clientTbDao.count(map);
	}
	
	@Override
	public int save(ClientTbDO clientTb){
		return clientTbDao.save(clientTb);
	}
	
	@Override
	public int update(ClientTbDO clientTb){
		return clientTbDao.update(clientTb);
	}
	
	@Override
	public int remove(Integer id){
		return clientTbDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return clientTbDao.batchRemove(ids);
	}
	
}
