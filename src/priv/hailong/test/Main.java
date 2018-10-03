package priv.hailong.test;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import priv.hailong.pojo.Category;
import priv.hailong.pojo.CategoryExample;
import priv.hailong.service.CategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"config/spring-mybatis.xml"})  
public class Main {

	@Autowired
	CategoryService categoryService;
	
	
	
	static Logger logger = Logger.getLogger(Main.class);	
	public static void main(String[] args) throws IOException{

//        PropertyConfigurator.configure("F:/javaproject/Tmall/src/config/log4j.xml");
		BasicConfigurator.configure();
		logger.setLevel(Level.DEBUG);

		
		
		
//		Reader reader = Resources.getResourceAsReader("config/Configure.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
		
        
        /*
        Integer[] nArrayA = {68,69,70};
        Integer[] nArrayB ={10,11,12};
        
        List<Integer[]> mainList = new ArrayList<Integer[]>();
        mainList.add(nArrayA);
        mainList.add(nArrayB);
        
        Map<String,List<Integer[]>> postMap = new HashMap<>();
        postMap.put("mainListA", mainList);
        postMap.put("mainListB", mainList);
        */
        
		/*
        List<Integer> mainList = new ArrayList<Integer>();
        mainList.add(10);
        mainList.add(11);

        Label labelA = new Label();
        labelA.setId(1);
        labelA.setContent("aaa");
        labelA.setListInt(mainList);
        Label labelB = new Label();
        labelB.setId(2);
        labelB.setContent("bbb");
        labelB.setListInt(mainList);
        
        
        
        List<Label> labelList = new ArrayList<Label>();
        labelList.add(labelA);
        labelList.add(labelB);
        
        CategoryExample category = new CategoryExample();
        
        
        
        Map<String, List<Label>> postMap = new HashMap<String, List<Label> >();
        postMap.put("labelList", labelList);
        postMap.put("labelListB", labelList);
        */
        
        /*
        Map<String, Label> postMap = new HashMap<String, Label>();
        postMap.put("labelA", labelA);
        postMap.put("labelB", labelB);
        */
        /*
        //一种Map的遍历方式
        for(Integer[] intArray:postMap.values()){
        	for(int num:intArray){
        	System.out.println(num);}
        }*/
        /*
        //jdk8新特性
        postMap.forEach((k,v)->
        System.out.println(v)
        );
        */
        
        
        
        
//        try {			
//			List<Category> categories = sqlSession.selectList(
//					"priv.hailong.mapper.CategoryMapper.testSelect", labelA);
        	
        	

        	new Main().test();
        	
//			printCategory(categories);
//		}
//		finally {			sqlSession.close();
//		}
		
	}
	
	public static void printCategory(final List<Category> categories){
		for(Category category:categories){
			String categoryInfo = "id："+category.getId()+", 内容："+category.getName();
			System.out.println(categoryInfo);
			
		}
	}
	
	public void test(){
    	System.out.println(categoryService);
		Category categories = categoryService.get(1);
		
	}
	
}
