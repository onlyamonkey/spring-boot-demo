package com.demo.springbootdemo;

import com.demo.springbootdemo.service.TestService;
import com.demo.springbootdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {
@Autowired
private TestService tstService;
	@Test
	public void contextLoads() {
        tstService.testTranction();
		//tstService.testNoTranction();
	}


}
