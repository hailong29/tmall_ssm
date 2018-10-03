package priv.hailong.service;

import priv.hailong.dao.OrderItemMapper;
import priv.hailong.pojo.Order;
import priv.hailong.pojo.OrderItem;
import priv.hailong.pojo.OrderItemExample;
import priv.hailong.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * OrderItemService 实现类
 *
 * @author: @我没有三颗心脏
 * @create: 2018-04-29-上午 10:15
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	OrderItemMapper orderItemMapper;

	@Autowired
	ProductService productService;

	@Override
	public void add(OrderItem orderItem) {
		orderItemMapper.insert(orderItem);
	}

	@Override
	public OrderItem getById(Integer id) {
		OrderItem orderItem = orderItemMapper.selectByPrimaryKey(id);
		setProduct(orderItem);
		return orderItem;
	}

	@Override
	public List<OrderItem> getByOrderId(Integer order_id) {
		OrderItemExample example = new OrderItemExample();
		example.or().andOidEqualTo(order_id);
		List<OrderItem> result = orderItemMapper.selectByExample(example);
		setProduct(result);
		return result;
	}

	@Override
	public void update(OrderItem orderItem) {
		orderItemMapper.updateByPrimaryKey(orderItem);
	}

	@Override
	public List<OrderItem> listByUserId(Integer user_id) {
		OrderItemExample example = new OrderItemExample();
		example.or().andUidEqualTo(user_id);
		List<OrderItem> result = orderItemMapper.selectByExample(example);
		setProduct(result);
		return result;
	}

	@Override
	public List<OrderItem> listForCart(Integer user_id) {
		OrderItemExample example = new OrderItemExample();
		example.or().andUidEqualTo(user_id).andOidIsNull();
		List<OrderItem> result = orderItemMapper.selectByExample(example);
		setProduct(result);
		return result;
	}
	
	@Override
	public List<OrderItem> listByProductId(Integer pid) {
		OrderItemExample example = new OrderItemExample();
		example.or().andPidEqualTo(pid).andOidIsNotNull();
		List<OrderItem> result = orderItemMapper.selectByExample(example);
		return result;
	}

	public void setProduct(List<OrderItem> ois) {
		for (OrderItem oi : ois) {
			setProduct(oi);
		}
	}

	private void setProduct(OrderItem oi) {
		Product p = productService.get(oi.getPid());
		oi.setProduct(p);
	}

	@Override
	public void delete(Integer id) {
		orderItemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void fill(List<Order> orders) {
		for (Order o : orders) {
			fill(o);
		}
	}

	@Override
	public void fill(Order order) {
		OrderItemExample example = new OrderItemExample();
		example.or().andOidEqualTo(order.getId());
		example.setOrderByClause("id desc");
		List<OrderItem> orderItems = orderItemMapper.selectByExample(example);
		setProduct(orderItems);

		float total = 0;
		int totalNumber = 0;
		for (OrderItem oi : orderItems) {
			total += oi.getNumber() * oi.getProduct().getPromotePrice();
			totalNumber += oi.getNumber();
		}
		order.setTotal(total);
		order.setTotalNumber(totalNumber);
		order.setOrderItems(orderItems);
	}


}
