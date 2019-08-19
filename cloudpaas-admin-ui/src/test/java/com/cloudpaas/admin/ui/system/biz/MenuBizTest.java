/**
 * 
 */
package com.cloudpaas.admin.ui.system.biz;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.cloudpaas.admin.ui.AdminUIApplication;
import com.cloudpaas.admin.ui.base.BaseBiz;
import com.cloudpaas.admin.ui.constants.ApiConstants;
import com.cloudpaas.admin.ui.utils.RestTemplateUtils;
import com.cloudpaas.common.model.Menu;
import com.cloudpaas.common.model.User;
import com.cloudpaas.common.result.ObjectRestResponse;
import com.google.common.collect.Maps;

/**
 * @author 大鱼
 *
 * @date 2019年8月19日 下午2:52:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminUIApplication.class)
@AutoConfigureMockMvc  //测试接口用
public class MenuBizTest extends BaseBiz<Menu>{
	
	protected MockMvc mockMvc;
	
	@Autowired
    protected WebApplicationContext context;
	
	@Autowired
	private   MenuBiz menuBiz;

	
	@Before
    public void testBefore(){
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        System.out.println("测试前");   
    }

    @After
    public void testAfter(){
        System.out.println("测试后");   
    }
	
	
	
	@Test
	public void MenuSelectOneTest(){
		ParameterizedTypeReference<ObjectRestResponse<Menu>> responseBodyType = new ParameterizedTypeReference<ObjectRestResponse<Menu>>() {};

		Map<String, Object> params = Maps.newHashMap();
		params.put("id", "1");
		HttpEntity<User> httpEntity = new HttpEntity<User>(getHttpHeaders());
	
		ObjectRestResponse<Menu> result = RestTemplateUtils.exchange(ApiConstants.API_MENU_GET_URL+"{id}", 
				HttpMethod.GET, httpEntity, responseBodyType,params)
				.getBody();

		Menu entity =  result.getData();
		System.out.print(JSON.toJSONString(entity));
		
	}
	
	@Test
	public void MenuGetByIdTest(){
		Menu menu = menuBiz.getMenuByID(1);
		
		System.out.println(JSON.toJSONString(menu));
		
	}

}
