package priv.hailong.service;

import priv.hailong.pojo.Product;
import priv.hailong.pojo.ProductImage;

import java.util.List;

public interface ProductImageService {

	void add(ProductImage image);
	
	void delete(Integer id);

	void deleteByProductId(Integer product_id);

	void update(ProductImage image);

	ProductImage get(Integer id);

	/**
	 * 通过pid返回图片集合
	 */
	List<ProductImage> list(Integer product_id);
	
	/**
	 * 通过pid返回第一张产品图片
	 */
	ProductImage getFirst(Integer product_id);
	
	/**
	 * 为单个产品填充第一张图片
	 */
	void fill(Product product);
	
	/**
	 * 为多个产品填充其第一张图片
	 */
	void fill(List<Product> products);
	
}
