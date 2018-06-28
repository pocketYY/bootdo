package com.bootdo.zyctd.api;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.R1;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.zyctd.domain.AreaTbDO;
import com.bootdo.zyctd.domain.BaseTbDO;
import com.bootdo.zyctd.service.AreaTbService;
import com.bootdo.zyctd.service.BaseTbService;
import com.bootdo.zyctd.service.CameraTbService;
import com.bootdo.zyctd.vo.CameraTbDOVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 云视频监控API
 *
 * @author liumingli
 * @date 2018-05-15 17:28:49
 */

@Controller
@RequestMapping("/zyctd/api/v1/")
public class APIController {

    @Autowired
    private AreaTbService areaTbService;
    @Autowired
    private BaseTbService baseTbService;
    @Autowired
    private CameraTbService cameraTbService;

    @ResponseBody
    @GetMapping("/area/list")
    public R1 areaList(@RequestParam Map<String, Object> params) {
        try {
            List<AreaTbDO> baseTbList = areaTbService.apiList(params);
            int total = areaTbService.count(params);
            PageUtils pageUtil = new PageUtils(baseTbList, total);
            return R1.ok(pageUtil);
        } catch (Exception e) {
            e.printStackTrace();
            return R1.error();
        }
    }

    @ResponseBody
    @GetMapping("/base/list")
    public R1 baseList(@RequestParam Map<String, Object> params) {
        try {
            List<Object> baseTbList = baseTbService.apiList(params);
            int total = baseTbService.count(params);
            PageUtils pageUtil = new PageUtils(baseTbList, total);
            return R1.ok(pageUtil);
        } catch (Exception e) {
            e.printStackTrace();
            return R1.error();
        }
    }

    @ResponseBody
    @GetMapping("/camera/list")
    public R1 cameraList(@RequestParam Map<String, Object> params) {
        try {
            params.put("status", 0);
            List<CameraTbDOVO> cameraTbDOVOList = cameraTbService.list(params);
            for (CameraTbDOVO cameraTbDOVO : cameraTbDOVOList) {
                BaseTbDO baseTbDO = baseTbService.get(Integer.valueOf(cameraTbDOVO.getBaseId()));
                String turned_stream_url = baseTbDO.getClientUrl() + "/" + baseTbDO.getCode() + "/" + cameraTbDOVO.getCode();
                cameraTbDOVO.setTurnedStreamUrl(turned_stream_url);
            }
            int total = cameraTbService.count(params);
            PageUtils pageUtil = new PageUtils(cameraTbDOVOList, total);
            return R1.ok(pageUtil);
        } catch (Exception e) {
            e.printStackTrace();
            return R1.error();
        }
    }

    @ResponseBody
    @GetMapping("/camera/detail/{id}")
    public R1 cameraDetail(@PathVariable("id") Integer id) {
        try {
            CameraTbDOVO cameraTbDOVO = cameraTbService.get(id);
            BaseTbDO baseTbDO = baseTbService.get(Integer.valueOf(cameraTbDOVO.getBaseId()));
            String turned_stream_url = baseTbDO.getClientUrl() + "/" + baseTbDO.getCode() + "/" + cameraTbDOVO.getCode();
            cameraTbDOVO.setTurnedStreamUrl(turned_stream_url);
            return R1.ok(cameraTbDOVO);
        } catch (Exception e) {
            e.printStackTrace();
            return R1.error();
        }
    }

    @ResponseBody
    @GetMapping("/camera/url/{baseCode}/{cameraCode}")
    public R1 cameraUrl(@PathVariable("baseCode") String baseCode, @PathVariable("cameraCode") String cameraCode) {
        try {
            String cameraUrl = cameraTbService.getCameraUrl(baseCode, cameraCode);
            if (StringUtils.isEmpty(cameraUrl)) {
                cameraUrl = "";
            }
            return R1.okWithStringData(cameraUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return R1.error();
        }
    }
}
