package com.mycourse.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycourse.dao.ContentDao;
import com.mycourse.dao.TrxDao;
import com.mycourse.meta.BuyProduct;
import com.mycourse.meta.Product;
import com.mycourse.meta.User;

@Controller
public class OneController {
	
	@Resource
	private ContentDao contentDao;
	
	@Resource
	private TrxDao trxDao;
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap map) throws UnsupportedEncodingException{
		List<Product> products = contentDao.getProducts();
		if (products!=null){
			for (Product each : products){
				if (trxDao.getProductByProduct(each.getId())!=null){
					each.setIsBuy("o");
					each.setIsSell("o");
				}
				each.setImage(new String(each.getImageByte()));
				each.setDetail(new String(each.getDetailByte(),"UTF-8"));
				each.setPrice();
			}
			map.addAttribute("productList", products);
		}
		else{
			map.addAttribute("productList", null);
		}
		
		return "index";
	}
	
	@RequestMapping(path="/login", method=RequestMethod.GET)
	public String login(ModelMap map){
		return "login";
	}
	
	@RequestMapping(path="/logout", method=RequestMethod.GET)
	public String logout(ModelMap map){
		return "login";
	}
	
	@RequestMapping(path="/show", method=RequestMethod.GET)
	public String show(HttpServletRequest request, ModelMap map) throws UnsupportedEncodingException{
		int productId = Integer.parseInt(request.getParameter("id"));
		Product product = contentDao.getProductById(productId);
		if (trxDao.getProductByProduct(product.getId())!=null){
			product.setIsBuy("o");
			product.setIsSell("o");
			product.setBuyPrice((trxDao.getProductByProduct(product.getId())).getBuyPrice());
		}
		product.setImage(new String(product.getImageByte()));
		product.setDetail(new String(product.getDetailByte(),"UTF-8"));
		product.setPrice();
		map.addAttribute("product", product);
		return "show";
	}
	
	
	@RequestMapping(path="/account", method=RequestMethod.GET)
	public String account(HttpServletRequest request, ModelMap map){
		int userId = ((User)(request.getSession().getAttribute("user"))).getId();
		List<BuyProduct> buyList = trxDao.getProductsByUser(userId);
		for (BuyProduct each : buyList){
			byte[] image = ((Product)contentDao.getProductById(each.getId())).getImageByte();
			each.setImage(new String(image));
			String title = ((Product)contentDao.getProductById(each.getId())).getTitle();
			each.setTitle(title);
		}
		map.addAttribute("buyList", buyList);
		return "account";
	}
	
	@RequestMapping(path="/public", method=RequestMethod.GET)
	public String publicThing(ModelMap map){
		return "public";
	}
	
	@RequestMapping(path="/publicSubmit", method=RequestMethod.POST)
	public String publicSubmit(@RequestParam String title, @RequestParam String summary, @RequestParam String image,
			@RequestParam String detail, @RequestParam double price, ModelMap map) 
					throws SerialException, UnsupportedEncodingException, SQLException{
		Blob imageBlob = new SerialBlob(image.getBytes("UTF-8"));
		Blob detailBlob = new SerialBlob(detail.getBytes("UTF-8"));
		
		contentDao.addProduct(title, (int)(price*100), imageBlob, summary, detailBlob);
		
		Product product = contentDao.getProductByTitle(title);
		map.addAttribute("product", product);
		
		return "publicSubmit";
	}
	
	@RequestMapping(path="/edit", method=RequestMethod.GET)
	public String edit(HttpServletRequest request, ModelMap map){
		int productId = Integer.parseInt(request.getParameter("id"));
		Product product = contentDao.getProductById(productId);
		map.addAttribute("product", product);
		return "edit";
	}
	
	@RequestMapping(path="/editSubmit", method=RequestMethod.POST)
	public String editSubmit(@RequestParam String title, @RequestParam String summary, @RequestParam String image,
			@RequestParam String detail, @RequestParam double price, ModelMap map, HttpServletRequest request) 
					throws SerialException, UnsupportedEncodingException, SQLException{
		Blob imageBlob = new SerialBlob(image.getBytes("GBK"));
		Blob detailBlob = new SerialBlob(detail.getBytes("UTF-8"));
		int productId = Integer.parseInt(request.getParameter("id"));
		
		contentDao.updateProduct(productId, title, (int)(price*100), imageBlob, summary, detailBlob);
		
		Product product = contentDao.getProductById(productId);
		
		map.addAttribute("product", product);
		
		return "editSubmit";
	}
	
}
