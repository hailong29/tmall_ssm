package priv.hailong.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import priv.hailong.pojo.Category;
import priv.hailong.pojo.Product;
import priv.hailong.pojo.ProductImage;
import priv.hailong.service.CategoryService;
import priv.hailong.service.ProductImageService;
import priv.hailong.service.ProductService;
import priv.hailong.service.PropertyValueService;
import priv.hailong.util.ImageUtil;
import priv.hailong.util.Page;
import priv.hailong.util.UploadedImageFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Product 控制器
 *
 * @author: @hailong
 */
@Controller
@RequestMapping("")
public class ProductController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;

	@Autowired
	ProductImageService productImageService;

	@Autowired
	PropertyValueService propertyValueService;

	@RequestMapping("/admin_product_list")
	public String list(Model model, Integer cid, Page page) {
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Product> ps = productService.list(cid);
        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&cid="+cid);
        
        productImageService.fill(ps);
		model.addAttribute("ps", ps);
		Category category = categoryService.get(cid);
		model.addAttribute("c", category);
		model.addAttribute("page", page);
        
		return "../admin/listProduct";
	}

	@RequestMapping("/admin_product_add")
	public String add(Product p, HttpSession session, UploadedImageFile uploadedImageFile) {
		productService.add(p);
		return "redirect:/admin_product_list?cid=" + p.getCid();
	}

	@RequestMapping("/admin_product_delete")
	public String delete(Integer id, HttpServletRequest request) {
		List<ProductImage> productImages = productImageService.list(id);
		for(ProductImage productImage : productImages){
			String filePath;
			if(productImage.getType().equals("type_detail")){
				filePath = request.getSession().getServletContext().getRealPath("img/productDetail/");
				new File(filePath, id+".jpg").delete();
			}else{
				filePath = request.getSession().getServletContext().getRealPath("img/productSingle/");
				new File(filePath,id+".jpg").delete();
				filePath = request.getSession().getServletContext().getRealPath("img/productSingle_middel");
				new File(filePath,id+".jpg").delete();
				filePath = request.getSession().getServletContext().getRealPath("img/productSingle_small");
				new File(filePath,id+".jpg").delete();
			}
		}
		
		int cid = productService.get(id).getCid();
		// 在删除产品的时候将对应的 11个 ProductImage 数据也删除了
		productImageService.deleteByProductId(id);
		// 删除外键对应的数据
		propertyValueService.deleteByProductId(id);
		productService.delete(id);
		return "redirect:/admin_product_list?cid=" + cid;
	}
	
	@RequestMapping("/admin_product_edit")
	public String edit(Integer id, Model model) {
		Product p = productService.get(id);
		model.addAttribute("product", p);
		Category c = categoryService.get(p.getCid());
		model.addAttribute("c", c);
		return "../admin/editProduct";
	}
	
	@RequestMapping("/admin_product_update")
	public String update(Product product) {
		productService.update(product);
		return "redirect:/admin_product_list?cid=" + product.getCid();
	}
}
