package test;

import java.util.Calendar;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.Person;
import beans.Student;

public class TestDemo {
	//多例与单例
	@Test
	public void test01(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Student student = (Student) context.getBean("student");
		Student student1 = (Student) context.getBean("student");
		System.out.println(student);
		System.out.println(student1);
	}
	
	@Test
	public void test02(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Student student = (Student) context.getBean("student");
		System.out.println(student);
	}
	@Test
	public void test03(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Person person = (Person) context.getBean("person");
		person.eat();
		context.close();
	}
}
