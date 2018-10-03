package priv.hailong.service;

import priv.hailong.dao.PropertyMapper;
import priv.hailong.pojo.Property;
import priv.hailong.pojo.PropertyExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PropertyService 实现类
 *
 * @author: @我没有三颗心脏
 * @create: 2018-04-27-下午 19:07
 */
@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	PropertyMapper propertyMapper;

	public void add(Property property) {
		propertyMapper.insert(property);
	}

	public void delete(Integer id) {
		propertyMapper.deleteByPrimaryKey(id);
	}

	public void update(Property property) {
		propertyMapper.updateByPrimaryKey(property);
	}

	public List<Property> list(Integer category_id) {
		PropertyExample example = new PropertyExample();
		example.or().andCidEqualTo(category_id);
		List<Property> properties = propertyMapper.selectByExample(example);
		return properties;
	}

	public Property get(Integer id) {
		return propertyMapper.selectByPrimaryKey(id);
	}
}
