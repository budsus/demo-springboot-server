package id.ac.ukdw.aop;

import javax.persistence.EntityManager;
import javax.websocket.Session;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy
public class ConnectionInterceptor {
	private Logger logger;
	private static final String JPA_PREFIX = "find";
	private static final String CUSTOM_PREFIX = "delete";
	private static final String UPDATE_PREFIX = "update";
	
	public ConnectionInterceptor() {
		logger = LoggerFactory.getLogger(getClass());
		logger.info("ConnectionInterceptor is started");
	}
	
	@Autowired
	private EntityManager entityManager;
	
	@Pointcut("this(org.springframework.data.repository.Repository)")
	public void inRepositoryLayer() {}
	
	@Around("inRepositoryLayer()")
	public Object proceed(ProceedingJoinPoint pjp) throws Throwable {
		String methodName = pjp.getSignature().getName();
		if (StringUtils.startsWith(methodName, JPA_PREFIX) || 
			StringUtils.startsWith(methodName, CUSTOM_PREFIX) || 
			StringUtils.startsWith(methodName, UPDATE_PREFIX)) {
			logger.info("method: {} ", methodName);
			
		}
		return pjp.proceed();
	}
	
}
