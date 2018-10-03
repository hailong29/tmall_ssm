package priv.hailong.service;

import priv.hailong.dao.ProductImageMapper;
import priv.hailong.pojo.Product;
import priv.hailong.pojo.ProductImage;
import priv.hailong.pojo.ProductImageExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProductImageService 实现类
 *
 * @author: @我没有三颗心脏
 * @create: 2018-04-28-上午 11:04
 */
@Service
public class ProductImageServiceImpl implements ProductImageService {

	@Autowired
	ProductImageMapper productImageMapper;

	public void add(ProductImage image) {
		productImageMapper.insert(image);
	}

	@Override
	public void delete(Integer id) {
		productImageMapper.deleteByPrimaryKey(id);
	}
	
	public void deleteByProductId(Integer product_id) {

		// 按条件查询出需要删除的列表
		ProductImageExample example = new ProductImageExample();
		example.or().andPidEqualTo(product_id);
		List<ProductImage> productImages = list(product_id);

		// 循环删除
		for (int i = 0; i < productImages.size(); i++) {
			productImageMapper.deleteByPrimaryKey(productImages.get(i).getId());
		}
	}

	public void update(ProductImage image) {
		productImageMapper.updateByPrimaryKey(image);
	}

	public ProductImage get(Integer id) {
		return productImageMapper.selectByPrimaryKey(id);
	}

	public List<ProductImage> list(Integer product_id) {
		ProductImageExample example = new ProductImageExample();
		example.or().andPidEqualTo(product_id);
		List<ProductImage> productImages = productImageMapper.selectByExample(example);
		return productImages;
	}

	@Override
	public ProductImage getFirst(Integer product_id) {
		ProductImageExample example = new ProductImageExample();
		example.or().andPidEqualTo(product_id);
		List<ProductImage> productImages = productImageMapper.selectByExample(example);
		return productImages.get(0);

	}


	@Override
	public void fill(Product product) {
		
		ProductImageExample example = new ProductImageExample();
		example.or().andPidEqualTo(product.getId());
		List<ProductImage> productImages = productImageMapper.selectByExample(example);
		if(!productImages.isEmpty()){
			product.setFirstProductImage(productImages.get(0));
		}
		
	}

	//最好可以写出多少到多少之间,以一次性查出所有图片集合
	@Override
	public void fill(List<Product> products) {
		for(Product product:products){
			fill(product);
		}

		
	}

}
