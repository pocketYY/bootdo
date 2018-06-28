package com.bootdo.zyctd.controller;

import java.util.List;
import java.util.Map;

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

import com.bootdo.zyctd.domain.ServerTbDO;
import com.bootdo.zyctd.service.ServerTbService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 服务端服务器信息
 * 
 * @author liumingli
 * @date 2018-05-15 17:28:49
 */
 
@Controller
@RequestMapping("/zyctd/serverTb")
public class ServerTbController {
	@Autowired
	private ServerTbService serverTbService;
	
	@GetMapping()
	@RequiresPermissions("zyctd:serverTb:serverTb")
	String ServerTb(){
	    return "zyctd/serverTb/serverTb";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("zyctd:serverTb:serverTb")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ServerTbDO> serverTbList = serverTbService.list(query);
		int total = serverTbService.count(query);
		PageUtils pageUtils = new PageUtils(serverTbList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("zyctd:serverTb:add")
	String add(){
	    return "zyctd/serverTb/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("zyctd:serverTb:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ServerTbDO serverTb = serverTbService.get(id);
		model.addAttribute("serverTb", serverTb);
	    return "zyctd/serverTb/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("zyctd:serverTb:add")
	public R save( ServerTbDO serverTb){
		if(serverTbService.save(serverTb)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("zyctd:serverTb:edit")
	public R update( ServerTbDO serverTb){
		serverTbService.update(serverTb);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("zyctd:serverTb:remove")
	public R remove( Integer id){
		if(serverTbService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("zyctd:serverTb:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		serverTbService.batchRemove(ids);
		return R.ok();
	}
	
}
