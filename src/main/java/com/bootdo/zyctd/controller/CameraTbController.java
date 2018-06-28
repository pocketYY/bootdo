package com.bootdo.zyctd.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.*;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.zyctd.domain.BaseTbDO;
import com.bootdo.zyctd.service.BaseTbService;
import com.bootdo.zyctd.vo.BaseTbDOVO;
import com.bootdo.zyctd.vo.CameraTbDOVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.zyctd.domain.CameraTbDO;
import com.bootdo.zyctd.service.CameraTbService;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;

/**
 * 摄像头
 *
 * @author liumingli
 * @date 2018-05-15 17:28:49
 */

@Controller
@RequestMapping("/zyctd/cameraTb")
public class CameraTbController {
    @Autowired
    private CameraTbService cameraTbService;
    @Autowired
    private BaseTbService baseTbService;
    @Value("${jwplayer.playUrl}")
    private String playUrl;

    @GetMapping()
    @RequiresPermissions("zyctd:cameraTb:cameraTb")
    String CameraTb() {
        return "zyctd/cameraTb/cameraTb";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("zyctd:cameraTb:cameraTb")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<CameraTbDOVO> cameraTbList = cameraTbService.list(query);
        for (CameraTbDOVO cameraTbDOVO : cameraTbList) {
            BaseTbDO baseTbDO = baseTbService.get(Integer.valueOf(cameraTbDOVO.getBaseId()));
            String turned_stream_url = baseTbDO.getClientUrl() + "/" + baseTbDO.getCode() + "/" + cameraTbDOVO.getCode();
            cameraTbDOVO.setTurnedStreamUrl(turned_stream_url);
        }
        int total = cameraTbService.count(query);
        PageUtils pageUtils = new PageUtils(cameraTbList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("zyctd:cameraTb:add")
    String add() {
        return "zyctd/cameraTb/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("zyctd:cameraTb:edit")
    String edit(@PathVariable("id") Integer id, Model model) {
        CameraTbDOVO cameraTbDOVO = cameraTbService.get(id);
        BaseTbDO baseTbDO = baseTbService.get(Integer.valueOf(cameraTbDOVO.getBaseId()));
        String turned_stream_url = baseTbDO.getClientUrl() + "/" + baseTbDO.getCode() + "/" + cameraTbDOVO.getCode();
        cameraTbDOVO.setTurnedStreamUrl(turned_stream_url);
        model.addAttribute("cameraTb", cameraTbDOVO);
        return "zyctd/cameraTb/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("zyctd:cameraTb:add")
    public R save(CameraTbDO cameraTb) {
        if (cameraTbService.save(cameraTb) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("zyctd:cameraTb:edit")
    public R update(CameraTbDO cameraTb) {
        cameraTbService.update(cameraTb);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("zyctd:cameraTb:remove")
    public R remove(Integer id) {
        if (cameraTbService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("zyctd:cameraTb:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        cameraTbService.batchRemove(ids);
        return R.ok();
    }

    /**
     * 摄像头编号重复验证
     */
    @PostMapping("/cameraCodeRepeatCheck/{code}")
    @ResponseBody
    public boolean cameraCodeRepeatCheck(@PathVariable("code") String code) {
        return cameraTbService.cameraCodeRepeatCheck(code);
    }

    /**
     * 摄像头原始流地址重复验证
     */
    @PostMapping("/osUrlRepeatCheck")
    @ResponseBody
    public boolean osUrlRepeatCheck(@RequestParam("originalStreamUrl") String originalStreamUrl) {
        return cameraTbService.osUrlRepeatCheck(originalStreamUrl);
    }

    /**
     * 批量设置摄像头状态
     */
    @PostMapping("/updateStatus")
    @ResponseBody
    @RequiresPermissions("zyctd:cameraTb:updateStatus")
    public R updateStatus(@RequestParam("ids[]") Integer[] ids, @RequestParam("status") String status) {
        cameraTbService.updateStatus(ids, status);
        return R.ok();
    }

    /**
     * 摄像头原始流地址重复验证
     */
    @GetMapping("/play/{id}")
    @RequiresPermissions("zyctd:cameraTb:play")
    public String play(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
        CameraTbDOVO cameraTbDOVO = cameraTbService.get(id);
        BaseTbDO baseTbDO = baseTbService.get(Integer.valueOf(cameraTbDOVO.getBaseId()));
        String turned_stream_url = baseTbDO.getClientUrl() + "/" + baseTbDO.getCode() + "/" + cameraTbDOVO.getCode();
        model.addAttribute("turnedStreamUrl", turned_stream_url);
        model.addAttribute("remoteUrl", playUrl);
        return "zyctd/cameraTb/play";
    }
}
