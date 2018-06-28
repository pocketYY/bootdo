package com.bootdo.testDemo;

import com.bootdo.zyctd.domain.ServerMonitorResultDO;
import com.bootdo.zyctd.service.ServerMonitorAPIService;
import org.apache.xerces.util.SynchronizedSymbolTable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController()
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo {

    @Autowired
    private ServerMonitorAPIService serverMonitorAPIService;

    @Test
    public void name() throws Exception {
        List<ServerMonitorResultDO> list = serverMonitorAPIService.list();
        for (ServerMonitorResultDO serverMonitorResultDO : list) {
            System.out.println(serverMonitorResultDO.toString());
        }
    }
}
