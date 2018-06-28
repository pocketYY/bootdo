package com.bootdo.zyctd.service;

import com.bootdo.zyctd.domain.CameraTbDO;
import com.bootdo.zyctd.vo.CameraTbDOVO;

import java.util.List;
import java.util.Map;

/**
 * 摄像头
 *
 * @author liumingli
 * @date 2018-05-15 17:28:49
 */
public interface CameraTbService {

    CameraTbDOVO get(Integer id);

    List<CameraTbDOVO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(CameraTbDO cameraTb);

    int update(CameraTbDO cameraTb);

    int remove(Integer id);

    int batchRemove(Integer[] ids);

    int updateStatus(Integer[] ids,String status);

    /**
     * 摄像头编号重复验证
     *
     * @param code
     * @return
     */
    boolean cameraCodeRepeatCheck(String code);

    /**
     * 摄像头原始流地址重复验证
     *
     * @param originalStreamUrl
     * @return
     */
    boolean osUrlRepeatCheck(String originalStreamUrl);

    /**
     * 获取摄像头原始流地址
     *
     * @param baseCode   基地编号
     * @param cameraCode 摄像头编号
     * @return
     */
    String getCameraUrl(String baseCode, String cameraCode);
}
