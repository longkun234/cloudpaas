/**
 * 
 */
package com.cloudpaas.admin.ui.system.web;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cloudpaas.admin.ui.base.BaseController;
import com.cloudpaas.admin.ui.constants.ApiConstants;
import com.cloudpaas.admin.ui.system.biz.RoleBiz;
import com.cloudpaas.common.model.Role;
import com.cloudpaas.common.result.ObjectRestResponse;
import com.cloudpaas.common.result.TableResultResponse;
import com.google.gson.Gson;

/**
 * @author 大鱼
 *
 * @date 2019年8月14日 下午2:35:55
 */
@Controller
@RequestMapping("role")
public class RoleController extends BaseController{
	
	
	@Autowired
	private RoleBiz roleBiz;

	@RequestMapping("/index.html")
	public String index(){
		return "admin/layui/system/role";
	}
	
	@RequestMapping("/add.html")
	public String add(){
		return "admin/layui/system/roleAdd";
	}
	
	@RequestMapping(value="/add.json",method=RequestMethod.POST)
	@ResponseBody
	public ObjectRestResponse<Role> add(@RequestBody Role entity){
		ObjectRestResponse<Role> result= roleBiz.add(entity, ApiConstants.API_ROLE_ADD_URL);
		return result;
	}
	
	@RequestMapping("/edit.html")
	public String edit(@RequestParam Integer id,ModelMap modelMap){
		Role role = roleBiz.get(id, ApiConstants.API_ROLE_SINGLE_URL);
		modelMap.put("role", role);
		return "admin/layui/system/roleEdit";
	}
	
	@RequestMapping(value="/update.json",method=RequestMethod.PUT)
	@ResponseBody
	public ObjectRestResponse<Role> update(@RequestBody Role entity){
		ObjectRestResponse<Role> result= roleBiz.update(entity, entity.getId(), ApiConstants.API_ROLE_SINGLE_URL);
		return result;
	}
	
	
	@RequestMapping(value="/delete.json",method=RequestMethod.DELETE)
	@ResponseBody
	public ObjectRestResponse<Role> remove(@RequestParam Integer id){
		ObjectRestResponse<Role> result= roleBiz.remove(id, ApiConstants.API_ROLE_SINGLE_URL);
		return result;
	}
	
	@RequestMapping(value="/deleteBatch.json",method=RequestMethod.DELETE)
	@ResponseBody
	public ObjectRestResponse<Role> remove(@RequestBody List<Role> lists){
		ObjectRestResponse<Role> result= roleBiz.deleteBatch(lists, ApiConstants.API_ROLE_DELBATCH_URL);
		return result;
	}
	
	@RequestMapping(value="/page.json",method=RequestMethod.GET)
	@ResponseBody
	public TableResultResponse<Role> page(@RequestParam Map<String, Object> params){
		TableResultResponse<Role> result= roleBiz.list(params, ApiConstants.API_ROLE_PAGE_URL);
		return result;
	}
	
	
	
}
