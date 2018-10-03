package priv.hailong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import priv.hailong.pojo.Category;
import priv.hailong.pojo.Order;
import priv.hailong.pojo.OrderItem;
import priv.hailong.pojo.Product;
import priv.hailong.pojo.User;
import priv.hailong.service.OrderItemService;
import priv.hailong.service.OrderService;
import priv.hailong.service.ProductImageService;
import priv.hailong.service.ProductService;
import priv.hailong.service.UserService;
import priv.hailong.util.Page;

import java.util.Date;
import java.util.List;

/**
 * Order 控制器
 *
 * @author: @hailong
 */
@Controller
@RequestMapping("")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderItemService orderItemService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductImageService productImageService;
	
	@Autowired
	UserService userService;

	@RequestMapping("/admin_order_list")
	public String list(Model model,Page page) {  
        PageHelper.offsetPage(page.getStart(),page.getCount());
		List<Order> orders = orderService.listAll();
        int total = (int) new PageInfo<>(orders).getTotal();
        page.setTotal(total);
        
        //设置总价及数量
        int totalPrice;
        int totalNumber;
        for(Order o : orders){
        	totalPrice = 0;
        	totalNumber = 0;
        	User user = userService.get(o.getUid());
        	o.setUser(user);
    		List<OrderItem> ois = orderItemService.getByOrderId(o.getId());        	
    		for (OrderItem orderItem : ois) {
    			Product product = productService.get(orderItem.getPid());
    			orderItem.setProduct(product);
    			totalPrice += orderItem.getProduct().getPromotePrice()*orderItem.getNumber();
    			totalNumber += orderItem.getNumber();
    		}	
    		o.setTotal(totalPrice);
    		o.setTotalNumber(totalNumber);
        }        
        //订单详情
        for(Order order:orders){
			List<OrderItem> orderItems = orderItemService.getByOrderId(order.getId());
			order.setOrderItems(orderItems);			
			for(OrderItem orderItem : orderItems){
				Product product = productService.get(orderItem.getPid());
				productImageService.fill(product);
				orderItem.setProduct(product);
			}
		}
        model.addAttribute("os", orders);
        model.addAttribute("page", page);
		return "../admin/listOrder";
	} 
	
	@RequestMapping("/admin_order_delivery")
	public String delivery(Integer id) {
		Order order = orderService.get(id);
		order.setDeliveryDate(new Date());
		order.setStatus(OrderService.waitConfirm);
		orderService.update(order);
		return "redirect:/admin_order_list";
	}
}