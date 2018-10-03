package priv.hailong.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import priv.hailong.pojo.Category;
import priv.hailong.pojo.Property;
import priv.hailong.pojo.User;
import priv.hailong.service.CategoryService;
import priv.hailong.service.PropertyService;
import priv.hailong.service.UserService;
import priv.hailong.util.Page;

import java.util.List;

/**
 * Property ¿ØÖÆÆ÷
 * 
 * @author: @hailong
 */
@Controller
@RequestMapping("")
public class PropertyController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	PropertyService propertyService;

	@RequestMapping("/admin_property_list")
	public String list(Model model, Integer cid,Page page) {
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Property> ps = propertyService.list(cid);
        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&cid="+cid);
        
		Category category = categoryService.get(cid);
		model.addAttribute("c", category);
        model.addAttribute("ps", ps);
        model.addAttribute("page", page);

		return "../admin/listProperty";
	}

	@RequestMapping("/admin_property_add")
	public String add(Property property) {
		propertyService.add(property);
		return "redirect:/admin_property_list?cid=" + property.getCid();
	}

	@RequestMapping("/admin_property_delete")
	public String delete(Integer id) {
		int category_id = propertyService.get(id).getCid();
		propertyService.delete(id);
		return "redirect:/admin_property_list?cid=" + category_id;
	}

	@RequestMapping("/admin_property_edit")
	public String edit(Integer id, Model model) {
		Property property = propertyService.get(id);
		Category category = categoryService.get(property.getCid());
		property.setCategory(category);
		model.addAttribute("p", property);
		return "../admin/editProperty";
	}

	@RequestMapping("/admin_property_update")
	public String update(Property property) {
		propertyService.update(property);
		return "redirect:/admin_property_list?cid=" + property.getCid();
	}
}
