package com.bootdo.zyctd.service.impl;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.zyctd.vo.BaseTbDOVO;
import com.sun.mail.imap.protocol.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.bootdo.zyctd.dao.BaseTbDao;
import com.bootdo.zyctd.domain.BaseTbDO;
import com.bootdo.zyctd.service.BaseTbService;


@Service
public class BaseTbServiceImpl implements BaseTbService {
    @Autowired
    private BaseTbDao baseTbDao;

    @Override
    public BaseTbDOVO get(Integer id) {
        return baseTbDao.get(id);
    }

    @Override
    public List<Object> list(Map<String, Object> map) {
        List<Object> list = baseTbDao.list(map);
        if (null == list || list.isEmpty()) {
            BaseTbDO baseTbDO = new BaseTbDO();
            baseTbDO.setName("总基地");
            baseTbDO.setParentId(0);
            baseTbDO.setParentIds("0");
//            Integer maxCode = baseTbDao.getMaxCode();
//            if (null == maxCode) {
//                maxCode = 999;
//            }
            baseTbDO.setCode("1000");
            baseTbDao.save(baseTbDO);
            if (baseTbDO.getId() > 0) {
                list.add(baseTbDO);
            }
        }
        return list;
    }

    @Override
    public List<Object> apiList(Map<String, Object> map) {
        return baseTbDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return baseTbDao.count(map);
    }

    @Override
    public int save(BaseTbDO baseTb) {
//        Integer maxCode = baseTbDao.getMaxCode();
//        if (null == maxCode) {
//            maxCode = 999;
//        }
//        baseTb.setCode(String.valueOf(maxCode + 1));
        baseTbDao.save(baseTb);
        if (baseTb.getParentIds().equals("0")) {
            BaseTbDO parent = baseTbDao.get(baseTb.getParentId());
            baseTb.setParentIds(parent.getId() + "," + baseTb.getId());
        } else {
            baseTb.setParentIds(baseTb.getParentIds() + "," + baseTb.getId());
        }
        if (baseTbDao.update(baseTb) > 0) {
            return baseTb.getId();
        } else {
            return 0;
        }
    }

    @Override
    public int update(BaseTbDO baseTb) {
        return baseTbDao.update(baseTb);
    }

    @Override
    public int remove(Integer id) {
        String ids = baseTbDao.getIdByParentIds(id);
        if (StringUtils.isNotEmpty(ids)) {
            int[] x = Arrays.stream(ids.split(",")).mapToInt(Integer::valueOf).toArray();
            return baseTbDao.batchRemove(x);
        } else {
            return 0;
        }
    }

    @Override
    public int batchRemove(int[] ids) {
        return baseTbDao.batchRemove(ids);
    }

    @Override
    public Tree<BaseTbDOVO> getTree() {
        List<Tree<BaseTbDOVO>> trees = new ArrayList<Tree<BaseTbDOVO>>();
        List<Object> baseTbDOVOS = baseTbDao.list(new HashMap<String, Object>(16));
        for (Object obj : baseTbDOVOS) {
            BaseTbDOVO baseTbDOVO = (BaseTbDOVO) obj;
            Tree<BaseTbDOVO> tree = new Tree<BaseTbDOVO>();
            tree.setId(baseTbDOVO.getId().toString());
            tree.setParentId(baseTbDOVO.getParentId().toString());
            tree.setText(baseTbDOVO.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<BaseTbDOVO> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public boolean baseCodeRepeatCheck(String code) {
        return null == baseTbDao.baseCodeRepeatCheck(code);
    }

}
