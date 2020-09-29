package com.hhm.bussplat.agent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hhm.bussplat.entity.AgentOperator;
import com.hhm.bussplat.mapper.AgentOperatorMapper;






@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AgentOperatorTest {
	
	@Autowired
	private  AgentOperatorMapper agentOperatorMapper;
	
	@Test
    public void test(){
		AgentOperator findOperator = agentOperatorMapper.findOperator("admin", "TRUE");
		System.err.println(findOperator);
	}
	
	
}
