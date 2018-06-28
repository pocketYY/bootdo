package com.bootdo.zyctd.service.impl;

import com.alibaba.fastjson.JSON;
import com.bootdo.common.config.HttpAPIService;
import com.bootdo.common.utils.HttpResult;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.zyctd.domain.ServerMonitorResultDO;
import com.bootdo.zyctd.service.BaseTbService;
import com.bootdo.zyctd.service.ServerMonitorAPIService;
import com.bootdo.zyctd.vo.BaseTbDOVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ServerMonitorAPIServiceImpl implements ServerMonitorAPIService {

    @Value("${serverMonitorAPI.url}")
    private String serverMonitorAPIUrl;
    @Resource
    private HttpAPIService httpAPIService;
    @Autowired
    private BaseTbService baseTbService;

    @Override
    public List<ServerMonitorResultDO> list() throws Exception {
        String result = httpAPIService.doGet(serverMonitorAPIUrl);
        if (StringUtils.isEmpty(result)) {
            return null;
        }
        List<ServerMonitorResultDO> list = JSON.parseArray(result, ServerMonitorResultDO.class);
        for (ServerMonitorResultDO serverMonitorResultDO : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", serverMonitorResultDO.getBase());
            map.put("offset", 0);
            map.put("limit", 1);
            BaseTbDOVO baseTbDOVO = (BaseTbDOVO) baseTbService.list(map).get(0);
            serverMonitorResultDO.setBaseTbDOVO(baseTbDOVO);
        }
        return list;
    }
}
