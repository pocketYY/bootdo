package com.bootdo.zyctd.service.impl;

import com.bootdo.zyctd.domain.BaseTbDO;
import com.bootdo.zyctd.service.BaseTbService;
import com.bootdo.zyctd.vo.CameraTbDOVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.zyctd.dao.CameraTbDao;
import com.bootdo.zyctd.domain.CameraTbDO;
import com.bootdo.zyctd.service.CameraTbService;


@Service
public class CameraTbServiceImpl implements CameraTbService {
    @Autowired
    private CameraTbDao cameraTbDao;
    @Autowired
    private BaseTbService baseTbService;

    @Override
    public CameraTbDOVO get(Integer id) {
        return cameraTbDao.get(id);
    }

    @Override
    public List<CameraTbDOVO> list(Map<String, Object> map) {
        return cameraTbDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return cameraTbDao.count(map);
    }

    @Override
    public int save(CameraTbDO cameraTb) {
//        Integer maxCode = cameraTbDao.getMaxCode();
//        if (null == maxCode) {
//            maxCode = 999;
//        }
//        cameraTb.setCode(String.valueOf(maxCode + 1));
        String[] baseIdArr = cameraTb.getBaseId().trim().substring(0, cameraTb.getBaseId().lastIndexOf(",")).split(",");
        cameraTb.setBaseId(baseIdArr[baseIdArr.length - 1]);
//        BaseTbDO baseTbDO = baseTbService.get(Integer.valueOf(cameraTb.getBaseId()));
//        cameraTb.setTurnedStreamUrl(baseTbDO.getClientUrl() + "/" + baseTbDO.getCode() + "/" + cameraTb.getCode());
        return cameraTbDao.save(cameraTb);
    }

    @Override
    public int update(CameraTbDO cameraTb) {
        try {
            String[] baseIdArr = cameraTb.getBaseId().trim().substring(0, cameraTb.getBaseId().lastIndexOf(",")).split(",");
            cameraTb.setBaseId(baseIdArr[baseIdArr.length - 1]);
        } catch (StringIndexOutOfBoundsException e) {
        }
//        BaseTbDO baseTbDO = baseTbService.get(Integer.valueOf(cameraTb.getBaseId()));
//        cameraTb.setTurnedStreamUrl(baseTbDO.getClientUrl() + "/" + baseTbDO.getCode() + "/" + cameraTb.getCode());
        return cameraTbDao.update(cameraTb);
    }

    @Override
    public int remove(Integer id) {
        return cameraTbDao.remove(id);
    }

    @Override
    public int batchRemove(Integer[] ids) {
        return cameraTbDao.batchRemove(ids);
    }

    @Override
    public int updateStatus(Integer[] ids, String status) {
        return cameraTbDao.updateStatus(ids, status);
    }

    @Override
    public boolean cameraCodeRepeatCheck(String code) {
        return null == cameraTbDao.cameraCodeRepeatCheck(code);
    }

    @Override
    public boolean osUrlRepeatCheck(String originalStreamUrl) {
        return null == cameraTbDao.osUrlRepeatCheck(originalStreamUrl);
    }

    @Override
    public String getCameraUrl(String baseCode, String cameraCode) {
        return cameraTbDao.getCameraUrl(baseCode, cameraCode);
    }

}
