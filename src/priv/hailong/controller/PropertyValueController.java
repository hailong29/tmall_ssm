package priv.hailong.controller;

import priv.hailong.pojo.Product;
import priv.hailong.pojo.PropertyValue;
import priv.hailong.service.CategoryService;
import priv.hailong.service.ProductService;
import priv.hailong.service.PropertyService;
import priv.hailong.service.PropertyValueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * PropertyValue ¿ØÖÆÆ÷
 * 
 * @author: @hailong
 */
@Controller
@RequestMapping("")
public class PropertyValueController {

	@Autowired
	PropertyValueService propertyValueService;

	@Autowired
	ProductService productService;

	@Autowired
	PropertyService propertyService;
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/admin_propertyValue_edit")
	public String edit(Integer pid, Model model) {
		List<PropertyValue> propertyValues = propertyValueService.listByProductId(pid);
		for(PropertyValue propertyValue : propertyValues){
			propertyValue.setProperty(propertyService.get(propertyValue.getPtid())); 
		}
		Product product = productService.get(pid);
		product.setCategory(categoryService.get(product.getCid()));   
		model.addAttribute("p", product);
		model.addAttribute("pvs", propertyValues);
		return "../admin/editPropertyValue";
	}
}
