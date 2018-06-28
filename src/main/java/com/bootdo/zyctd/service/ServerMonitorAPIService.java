package com.bootdo.zyctd.service;

import com.bootdo.zyctd.domain.ServerMonitorResultDO;
import com.bootdo.zyctd.domain.ServerTbDO;

import java.util.List;
import java.util.Map;

/**
 * 服务端服务器信息监控服务
 *
 * @author liumingli
 * @date 2018-05-15 17:28:49
 */
public interface ServerMonitorAPIService {

    List<ServerMonitorResultDO> list() throws Exception;
}
