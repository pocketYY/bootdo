package com.bootdo.zyctd.service.impl;

import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.zyctd.domain.BaseTbDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import com.bootdo.zyctd.dao.AreaTbDao;
import com.bootdo.zyctd.domain.AreaTbDO;
import com.bootdo.zyctd.service.AreaTbService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AreaTbServiceImpl implements AreaTbService {
    @Autowired
    private AreaTbDao areaTbDao;

    @Override
    public AreaTbDO get(Integer id) {
        return areaTbDao.get(id);
    }

    @Override
    public List<AreaTbDO> list(Map<String, Object> map) {

        List<AreaTbDO> list = areaTbDao.list(map);
        if (null == list || list.isEmpty()) {
            AreaTbDO areaTbDO = new AreaTbDO();
            areaTbDO.setName("总区域");
            areaTbDO.setParentId(0);
            areaTbDO.setParentIds("0");
            areaTbDao.save(areaTbDO);
            if (areaTbDO.getId() > 0) {
                list.add(areaTbDO);
            }
        }
        return list;
    }

    @Override
    public List<AreaTbDO> apiList(Map<String, Object> map) {
        return areaTbDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return areaTbDao.count(map);
    }

    @Transactional
    @Override
    public int save(AreaTbDO areaTb) {
        areaTb.setLevel(areaTb.getLevel() + 1);
        areaTbDao.save(areaTb);
        areaTb.setParentIds(areaTb.getParentIds() + "," + areaTb.getId());
        if (areaTbDao.update(areaTb) > 0) {
            return areaTb.getId();
        } else {
            return 0;
        }
    }

    @Override
    public int update(AreaTbDO areaTb) {
        return areaTbDao.update(areaTb);
    }

    @Override
    public int remove(Integer id) {
        String ids = areaTbDao.getIdByParentIds(id);
        if (StringUtils.isNotEmpty(ids)) {
            int[] x = Arrays.stream(ids.split(",")).mapToInt(Integer::valueOf).toArray();
            return areaTbDao.batchRemove(x);
        } else {
            return 0;
        }
    }

    @Override
    public int batchRemove(int[] ids) {
        return areaTbDao.batchRemove(ids);
    }

}
