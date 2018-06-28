package com.bootdo.zyctd.dao;

import com.bootdo.zyctd.domain.BaseTbDO;

import java.util.List;
import java.util.Map;

import com.bootdo.zyctd.vo.BaseTbDOVO;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

/**
 * 种植基地
 *
 * @author liumingli
 * @date 2018-05-15 17:28:49
 */
@Mapper
public interface BaseTbDao {

    BaseTbDOVO get(Integer id);

    List<Object> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(BaseTbDO baseTb);

    int update(BaseTbDO baseTb);

    int remove(Integer id);

    int batchRemove(int[] ids);

    String getIdByParentIds(Integer id);

    /**
     * 基地编号重复验证
     *
     * @param code
     * @return
     */
    Integer baseCodeRepeatCheck(String code);

    /**
     * 获取基地最大编号
     *
     * @return
     */
    Integer getMaxCode();
}
