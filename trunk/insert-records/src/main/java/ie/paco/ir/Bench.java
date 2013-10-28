package ie.paco.ir;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bench {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
	    Slingshot slingshot = (Slingshot)ctx.getBean("slingshot");
	    try {
			slingshot.go(args[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
