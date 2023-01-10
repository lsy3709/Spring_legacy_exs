package com.spring.ex04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class LazyTest {
	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("lazy.xml");
		System.out.println("SecondBean ���");
	
		// 네트워크 관련 을 통해서 해당 데이터 받는 부분.
		// 받아 두는 저장소가 있다. 
		// lazy-init
	
		context.getBean("firstBean");
	}
}


