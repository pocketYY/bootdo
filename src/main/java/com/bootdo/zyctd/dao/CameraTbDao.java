package com.bootdo.zyctd.dao;

import com.bootdo.zyctd.domain.CameraTbDO;

import java.util.List;
import java.util.Map;

import com.bootdo.zyctd.vo.CameraTbDOVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 摄像头
 *
 * @author liumingli
 * @date 2018-05-15 17:28:49
 */
@Mapper
public interface CameraTbDao {

    CameraTbDOVO get(Integer id);

    List<CameraTbDOVO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(CameraTbDO cameraTb);

    int update(CameraTbDO cameraTb);

    int remove(Integer id);

    int batchRemove(Integer[] ids);

    int updateStatus(@Param("array") Integer[] ids, @Param("status") String status);

    /**
     * 摄像头编号重复验证
     *
     * @param code
     * @return
     */
    Integer cameraCodeRepeatCheck(String code);

    /**
     * 摄像头原始流地址重复验证
     *
     * @param originalStreamUrl
     * @return
     */
    Integer osUrlRepeatCheck(String originalStreamUrl);

    /**
     * 获取摄像头原始流地址
     *
     * @param baseCode   基地编号
     * @param cameraCode 摄像头编号
     * @return
     */
    String getCameraUrl(@Param("baseCode") String baseCode, @Param("cameraCode") String cameraCode);

    /**
     * 获取摄像头最大编号
     * @return
     */
    Integer getMaxCode();
}
