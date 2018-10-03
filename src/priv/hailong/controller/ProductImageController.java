package priv.hailong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import priv.hailong.pojo.Category;
import priv.hailong.pojo.Product;
import priv.hailong.pojo.ProductImage;
import priv.hailong.service.CategoryService;
import priv.hailong.service.ProductImageService;
import priv.hailong.service.ProductService;
import priv.hailong.util.ImageUtil;
import priv.hailong.util.Page;
import priv.hailong.util.UploadedImageFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductImage 的控制器
 *
 * @author: @hailong
 */
@Controller
@RequestMapping("")
public class ProductImageController {

	@Autowired
	ProductImageService productImageService;

	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
    @RequestMapping("/admin_productImage_list")
    public String list(Model model,Integer pid){
        List<ProductImage> pis = productImageService.list(pid);
        List<ProductImage> pisSingle = new ArrayList();
        List<ProductImage> pisDetail = new ArrayList();
        for(ProductImage pi:pis){
        	if (pi.getType().equals("type_single")) {
				pisSingle.add(pi);
			}else{
				pisDetail.add(pi);
			}
        }
        Product product = productService.get(pid);
        product.setCategory(categoryService.get(product.getCid()));
        
        model.addAttribute("p", product);
        model.addAttribute("pisSingle", pisSingle);
        model.addAttribute("pisDetail", pisDetail);
        return "../admin/listProductImage";
    }
	
	@RequestMapping("/admin_productImage_delete")
	public String delete(Integer id,HttpServletRequest request) {
		ProductImage productImage = productImageService.get(id);
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
		productImageService.delete(id);
		return "redirect:/admin_productImage_list?pid=" + productImage.getPid();
	}

    @RequestMapping("/admin_productImage_add")
    public String add(ProductImage productImage, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
		//先增加至数据库以获取图片id
    	productImageService.add(productImage);
    	List<ProductImage> productImages = productImageService.list(productImage.getPid());

    	for(ProductImage pi : productImages){
    		if(pi.getId().intValue() > productImage.getId().intValue())
    			productImage.setId(pi.getId()); 
    	}
		String filePath;
		String fileName = productImage.getId() + ".jpg";
		if(productImage.getType().equals("type_detail")){
			filePath = session.getServletContext().getRealPath("img/productDetail/");
		}else{
			filePath = session.getServletContext().getRealPath("img/productSingle/");
		}
		File file = new File(filePath, fileName);
        uploadedImageFile.getImage().transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
        return "redirect:/admin_productImage_list?pid=" + productImage.getPid();
    }	
}