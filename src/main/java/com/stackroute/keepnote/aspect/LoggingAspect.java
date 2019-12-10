package com.stackroute.keepnote.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/* Annotate this class with @Aspect and @Component */

@Aspect
@Component
public class LoggingAspect {

	/*
	 * Write loggers for each of the methods of controller, any particular method
	 * will have all the four aspectJ annotation
	 * (@Before, @After, @AfterReturning, @AfterThrowing).
	 */
	@Before(value = "execution(* com.stackroute.keepnote.controller.*)")
	public void before() {
		
		
	}
	
	@After(value = "execution(* com.stackroute.keepnote.controller.*)")
	public void after() {
		
		
	}
	
	@AfterThrowing(pointcut = "execution(* com.stackroute.keepnote.controller.*)", throwing = "ex")
	public void afterReturning(Exception ex) {
		
		
	}
	
	@AfterReturning(pointcut="execution(* com.stackroute.keepnote.controller.*)", returning="retVal") 
	public void afterThrowing(Object retVal) {
		
		
	}
}
