package com.bootdo.zyctd.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.annotation.Log;
import com.sun.tools.corba.se.idl.constExpr.ShiftLeft;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.zyctd.domain.AreaTbDO;
import com.bootdo.zyctd.service.AreaTbService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 区域
 *
 * @author liumingli
 * @date 2018-05-15 17:28:49
 */

@Controller
@RequestMapping("/zyctd/areaTb")
public class AreaTbController {
    @Autowired
    private AreaTbService areaTbService;

    @GetMapping()
    @RequiresPermissions("zyctd:areaTb:areaTb")
    String AreaTb() {
        return "zyctd/areaTb/areaTb";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("zyctd:areaTb:areaTb")
    public List<AreaTbDO> list(@RequestParam Map<String, Object> params) {
        List<AreaTbDO> areaTbList = areaTbService.list(params);
        return areaTbList;
    }

    @GetMapping("/add/{pId}")
    @RequiresPermissions("zyctd:areaTb:add")
    String add(Model model, @PathVariable("pId") int pId) {
        AreaTbDO areaTbDO = areaTbService.get(pId);
        String pName = "总区域";
        String pIds = "0";
        Integer level = 0;
        if (null != areaTbDO && areaTbDO.getId() > 0) {
            pName = areaTbDO.getName();
            pIds = areaTbDO.getParentIds();
            level = areaTbDO.getLevel();
        }
        model.addAttribute("pId", pId);
        model.addAttribute("level", level);
        model.addAttribute("pName", pName);
        model.addAttribute("pIds", pIds);
        return "zyctd/areaTb/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("zyctd:areaTb:edit")
    String edit(@PathVariable("id") Integer id, Model model) {
        AreaTbDO areaTb = areaTbService.get(id);
        model.addAttribute("areaTb", areaTb);
        return "zyctd/areaTb/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("zyctd:areaTb:add")
    public R save(AreaTbDO areaTb) {
        areaTbService.save(areaTb);
        if (areaTb.getId() > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("zyctd:areaTb:edit")
    public R update(AreaTbDO areaTb) {
        areaTbService.update(areaTb);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("zyctd:areaTb:remove")
    public R remove(Integer id) {
        if (areaTbService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("zyctd:areaTb:batchRemove")
    public R remove(@RequestParam("ids[]") int[] ids) {
        areaTbService.batchRemove(ids);
        return R.ok();
    }

}
