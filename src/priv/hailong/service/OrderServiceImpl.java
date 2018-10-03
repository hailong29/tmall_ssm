package priv.hailong.service;

import priv.hailong.dao.OrderMapper;
import priv.hailong.pojo.Order;
import priv.hailong.pojo.OrderExample;
import priv.hailong.pojo.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * OrderService 实现类
 *
 * @author: @我没有三颗心脏
 * @create: 2018-04-29-上午 10:19
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderMapper orderMapper;

	@Autowired
	OrderItemService orderItemService;

	@Override
	public Order get(int id) {
		return orderMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Order> listAll() {
		OrderExample example = new OrderExample();
		return orderMapper.selectByExample(example);
	}

	@Override
	public List<Order> list(Integer user_id, String excludedStatus) {
		OrderExample example = new OrderExample();
		example.or().andUidEqualTo(user_id).andStatusNotEqualTo(excludedStatus);
		example.setOrderByClause("id desc");
		return orderMapper.selectByExample(example);
	}

	@Override
	public List<Order> listByUserId(Integer user_id) {
		OrderExample example = new OrderExample();
		example.or().andUidEqualTo(user_id);
		return orderMapper.selectByExample(example);
	}

	@Override
	public void update(Order order) {
		orderMapper.updateByPrimaryKey(order);
	}

	@Override
	public void add(Order c) {
		orderMapper.insert(c);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
	public float add(Order order, List<OrderItem> orderItems) {
		float total = 0;
		add(order);

		// 用来模拟当增加订单后出现异常，观察事务管理是否预期发生
		if (false)
			throw new RuntimeException();

		for (OrderItem oi : orderItems) {
			oi.setOid(order.getId());
			orderItemService.update(oi);
			total += oi.getProduct().getPromotePrice() * oi.getNumber();
		}
		return total;
	}
}
