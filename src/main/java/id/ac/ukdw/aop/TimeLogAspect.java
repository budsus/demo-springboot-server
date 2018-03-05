package id.ac.ukdw.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import id.ac.ukdw.demo.service.PersonService;

@Aspect
@Component
public class TimeLogAspect {
	private static final Logger LOGGER =
			LoggerFactory.getLogger(PersonService.class);
	
	@Around("@annotation(id.ac.ukdw.aop.Timed) && execution(public * * (..))")
	public Object time(final ProceedingJoinPoint pjp) throws Throwable {
		LOGGER.info("time is calculated!");
		long start = System.currentTimeMillis();
		
		Object value;
		
		try {
			value = pjp.proceed();
		} catch(Throwable throwable) {
			throw throwable;
		} finally {
			long duration = System.currentTimeMillis() - start;
			LOGGER.info("{}.{} took {} ms",
					pjp.getSignature().getDeclaringType().getSimpleName(),
					pjp.getSignature().getName(),
					duration);
		}
		
		return value;
	}
}
