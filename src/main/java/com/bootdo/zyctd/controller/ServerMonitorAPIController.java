package com.bootdo.zyctd.controller;

import com.bootdo.zyctd.domain.ServerMonitorResultDO;
import com.bootdo.zyctd.service.ServerMonitorAPIService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 服务器监控API
 *
 * @author liumingli
 * @date 2018-05-15 17:28:49
 */
@Controller
@RequestMapping("/zyctd/serverMonitor")
public class ServerMonitorAPIController {
    @Autowired
    private ServerMonitorAPIService serverMonitorAPIService;

    @GetMapping()
    @RequiresPermissions("zyctd:serverMonitor:serverMonitor")
    String serverMonitor() {
        return "zyctd/serverMonitor/serverMonitor";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("zyctd:serverMonitor:serverMonitor")
    public List<ServerMonitorResultDO> list() throws Exception {
        return serverMonitorAPIService.list();
    }
}
