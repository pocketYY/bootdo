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

import com.bootdo.zyctd.domain.ClientTbDO;
import com.bootdo.zyctd.service.ClientTbService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 客户端服务器信息
 * 
 * @author liumingli
 * @date 2018-05-15 17:28:49
 */
 
@Controller
@RequestMapping("/zyctd/clientTb")
public class ClientTbController {
	@Autowired
	private ClientTbService clientTbService;
	
	@GetMapping()
	@RequiresPermissions("zyctd:clientTb:clientTb")
	String ClientTb(){
	    return "zyctd/clientTb/clientTb";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("zyctd:clientTb:clientTb")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ClientTbDO> clientTbList = clientTbService.list(query);
		int total = clientTbService.count(query);
		PageUtils pageUtils = new PageUtils(clientTbList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("zyctd:clientTb:add")
	String add(){
	    return "zyctd/clientTb/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("zyctd:clientTb:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ClientTbDO clientTb = clientTbService.get(id);
		model.addAttribute("clientTb", clientTb);
	    return "zyctd/clientTb/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("zyctd:clientTb:add")
	public R save( ClientTbDO clientTb){
		if(clientTbService.save(clientTb)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("zyctd:clientTb:edit")
	public R update( ClientTbDO clientTb){
		clientTbService.update(clientTb);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("zyctd:clientTb:remove")
	public R remove( Integer id){
		if(clientTbService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("zyctd:clientTb:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		clientTbService.batchRemove(ids);
		return R.ok();
	}
	
}
