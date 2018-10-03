package priv.hailong.test;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import priv.hailong.dao.CategoryMapper;
import priv.hailong.pojo.Category;
import priv.hailong.pojo.Product;
import priv.hailong.pojo.ProductImage;
import priv.hailong.service.CategoryService;
import priv.hailong.service.ProductImageService;
import priv.hailong.service.ProductService;  
  
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:config/spring-mybatis.xml"})  
public class TmallTest {  
	
	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;

	@Autowired
	ProductImageService productImageService;
  
    @Test
    public void test() {  
		List<Category> categories = categoryService.list();
		productService.fill(categories);
		productService.fillByRow(categories);
		

		for(Category category:categories){
			for(Product product:category.getProducts()){
				List<ProductImage> productImages = productImageService.list(product.getId());
				product.setFirstProductImage(productImages.get(0));
			}
		}
		System.out.println(categories.get(0).getProducts().get(0).getFirstProductImage());
    	
//        Category category = categoryService.get(60);  
//        System.out.println(category.get(1).getName());
        // System.out.println(user.getUserName());  
        // logger.info("值："+user.getUserName());  
    }  
}
