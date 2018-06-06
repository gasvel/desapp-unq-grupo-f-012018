package aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class HibernateAspect {
	
	@Before("within(model..*)")
	public void testMapping(JoinPoint joinPoint) {
		System.out.println(joinPoint.getArgs());
	}

}
