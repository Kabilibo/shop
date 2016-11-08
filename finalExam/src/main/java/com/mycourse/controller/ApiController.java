package com.mycourse.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycourse.dao.LoginDao;
import com.mycourse.dao.TrxDao;
import com.mycourse.dao.ContentDao;
import com.mycourse.meta.User;


@Controller
public class ApiController {
	
	@Resource
	private ContentDao contentDao;
	
	@Resource
	private TrxDao trxDao;
	
	@Resource
	private LoginDao loginDao;
	
	@RequestMapping(path="/api/login")
	public String isUser(@RequestParam("userName") String userName, @RequestParam("password") String password, ModelMap map,
			HttpServletResponse response, HttpServletRequest request) throws NoSuchAlgorithmException{
		
		User aUser = loginDao.getUser(userName);
		
		if (password.equals(md5Encode(aUser.getPassword()))){
					
			HttpSession session = request.getSession();

			session.setAttribute("user", aUser);
		
			map.addAttribute("code", 200);
			map.addAttribute("message", "");
			map.addAttribute("result", true);
			return "blabla";
		}
		else {
			map.addAttribute("code", 400);
			map.addAttribute("message", "输入错误");
			map.addAttribute("result", false);
			return "plapla";
		}
		
	}
	
	@RequestMapping(path="/api/delete")
	public String deleteProduct(@RequestParam("id") int id, ModelMap map){
		
		if (contentDao.deleteProduct(id)){
			map.addAttribute("code", 200);
			map.addAttribute("message", "");
			map.addAttribute("result", true);
			return "blabla";
		}
		else {
			map.addAttribute("code", 400);
			map.addAttribute("message", "删除失败");
			map.addAttribute("result", false);
			return "plapla";
		}
	}
	
	@RequestMapping(path="/api/buy")
	public String buyProduct(@RequestParam("id") int productId, HttpServletRequest request, ModelMap map){
		
		double price = contentDao.getProductById(productId).getPrice();
		long time = (new java.sql.Date(new java.util.Date().getTime())).getTime();
		int userId = ((User)(request.getSession().getAttribute("user"))).getId();
		if (trxDao.addTrx(productId, userId, (int)price*100, time)){
			map.addAttribute("code", 200);
			map.addAttribute("message", "");
			map.addAttribute("result", true);
			return "blabla";
		}
		else{
			map.addAttribute("code", 400);
			map.addAttribute("message", "购买失败");
			map.addAttribute("result", false);
			return "plapla";
		}
	}
	
	public static String md5Encode(String msg) throws NoSuchAlgorithmException{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(msg.getBytes());
        StringBuffer sb = new StringBuffer();
        for (byte b : md5.digest()) {
        	int i = b & 0xff;
        	String hexString = Integer.toHexString(i);
        	if (hexString.length() < 2) {
        		hexString = "0" + hexString;
        	}
        	sb.append(hexString);
        }
        return sb.toString();
	}
	
}
