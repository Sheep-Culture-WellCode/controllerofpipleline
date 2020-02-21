package com.example.controllerofpipleline;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerofpiplelineApplicationTests {
	@Autowired
	DataSource dataSource;

	@Test
	public  void contextLoads() throws Exception{
        System.out.println("1111111111111111111111111");
		System.out.println(dataSource.getConnection("root","ywj990623"));
	}

}
