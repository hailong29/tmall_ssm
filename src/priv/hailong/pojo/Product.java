package priv.hailong.pojo;

import java.util.Date;
import java.util.List;

public class Product {
    private Integer id;

    private String name;

    private String subTitle;

    private Float originalPrice;

    private Float promotePrice;

    private Integer stock;

    private Integer cid;

    private Date createDate;
    
    //×Ô´´
    private Integer saleCount;
    
    private ProductImage firstProductImage;
    public ProductImage getFirstProductImage(){
    	return firstProductImage;
    }
    public void setFirstProductImage(ProductImage firstProductImage){
    	this.firstProductImage = firstProductImage;
    }
    
    private Category category;
    public Category getCategory(){
    	return category;
    }
    public void setCategory(Category category){
    	this.category = category;
    }
    
    private List<ProductImage> productSingleImages;
    public List<ProductImage> getProductSingleImages(){
    	return productSingleImages;
    }
    public void setProductSingleImages(List<ProductImage> productSingleImages){
    	this.productSingleImages = productSingleImages;
    }
    
    private List<ProductImage> productDetailImages;
    public List<ProductImage> getProductDetailImages(){
    	return productDetailImages;
    }
    public void setProductDetailImages(List<ProductImage> productDetailImages){
    	this.productDetailImages = productDetailImages;
    }
    

    public Integer getSaleCount() {
        return saleCount;
    }
    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }
    
    
    private Integer reviewCount;
    public Integer getReviewCount() {
        return reviewCount;
    }
    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle == null ? null : subTitle.trim();
    }

    public Float getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Float getPromotePrice() {
        return promotePrice;
    }

    public void setPromotePrice(Float promotePrice) {
        this.promotePrice = promotePrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}