package com.bootdo.zyctd.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.domain.Tree;
import com.bootdo.zyctd.domain.AreaTbDO;
import com.bootdo.zyctd.vo.BaseTbDOVO;
import com.sun.tools.javac.jvm.Code;
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

import com.bootdo.zyctd.domain.BaseTbDO;
import com.bootdo.zyctd.service.BaseTbService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 种植基地
 *
 * @author liumingli
 * @date 2018-05-15 17:28:49
 */

@Controller
@RequestMapping("/zyctd/baseTb")
public class BaseTbController {
    @Autowired
    private BaseTbService baseTbService;

    @GetMapping()
    @RequiresPermissions("zyctd:baseTb:baseTb")
    String BaseTb() {
        return "zyctd/baseTb/baseTb";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("zyctd:baseTb:baseTb")
    public List<Object> list(@RequestParam Map<String, Object> params) {
        List<Object> baseTbList = baseTbService.list(params);
        return baseTbList;
    }

    @GetMapping("/add/{pId}")
    @RequiresPermissions("zyctd:baseTb:add")
    String add(Model model, @PathVariable("pId") int pId) {
        BaseTbDO baseTbDO = baseTbService.get(pId);
        String pName = "总基地";
        String pIds = "0";
        if (null != baseTbDO && baseTbDO.getId() > 0) {
            pName = baseTbDO.getName();
            pIds = baseTbDO.getParentIds();
        }
        model.addAttribute("pId", pId);
        model.addAttribute("pName", pName);
        model.addAttribute("pIds", pIds);
        return "zyctd/baseTb/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("zyctd:baseTb:edit")
    String edit(@PathVariable("id") Integer id, Model model) {
        BaseTbDOVO baseTb = baseTbService.get(id);
        model.addAttribute("baseTb", baseTb);
        return "zyctd/baseTb/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("zyctd:baseTb:add")
    public R save(BaseTbDO baseTb) {
        if (baseTbService.save(baseTb) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("zyctd:baseTb:edit")
    public R update(BaseTbDO baseTb) {
        baseTbService.update(baseTb);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("zyctd:baseTb:remove")
    public R remove(Integer id) {
        if (baseTbService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("zyctd:baseTb:batchRemove")
    public R remove(@RequestParam("ids[]") int[] ids) {
        baseTbService.batchRemove(ids);
        return R.ok();
    }

    /**
     * 基地编号重复验证
     */
    @PostMapping("/baseCodeRepeatCheck/{code}")
    @ResponseBody
    public boolean baseCodeRepeatCheck(@PathVariable("code") String code) {
        return baseTbService.baseCodeRepeatCheck(code);
    }

    @GetMapping("/tree")
    @ResponseBody
    public Tree<BaseTbDOVO> tree() {
        Tree<BaseTbDOVO> tree = new Tree<BaseTbDOVO>();
        tree = baseTbService.getTree();
        return tree;
    }

    @GetMapping("/treeView")
    String treeView() {
        return "/zyctd/cameraTb/deptTree";
    }
}
