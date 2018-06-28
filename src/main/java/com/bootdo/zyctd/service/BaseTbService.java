package com.bootdo.zyctd.service;

import com.bootdo.common.domain.Tree;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.zyctd.domain.BaseTbDO;
import com.bootdo.zyctd.vo.BaseTbDOVO;

import java.util.List;
import java.util.Map;

/**
 * 种植基地
 *
 * @author liumingli
 * @date 2018-05-15 17:28:49
 */
public interface BaseTbService {

    BaseTbDOVO get(Integer id);

    List<Object> list(Map<String, Object> map);

    List<Object> apiList(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(BaseTbDO baseTb);

    int update(BaseTbDO baseTb);

    int remove(Integer id);

    int batchRemove(int[] ids);

    Tree<BaseTbDOVO> getTree();

    /**
     * 基地编号重复验证
     *
     * @param code
     * @return
     */
    boolean baseCodeRepeatCheck(String code);
}
