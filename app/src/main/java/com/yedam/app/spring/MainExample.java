package com.yedam.app.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainExample {
	public static void main(String[] args) {
		ApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
													//file:src/main/resources/applicationContext.xml
		
		//id속성이 있을때
		TV tv = (TV)ctx.getBean("tv");
		tv.turnOn();
		
		//id속성이 없을때
		TV subTv = (TV)ctx.getBean(TV.class);
		subTv.turnOn();
		if(tv == subTv) {
			System.out.println("같은 빈입니다");
		} else {			
			System.out.println("다른 빈입니다");
		}
	}
}
