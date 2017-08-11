package com.base.annotation.log;

import java.net.InetAddress;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.base.entity.LogInfo;
import com.base.service.LogInfoService;
import com.base.util.UserSessionUtil;

/**
 * 系统Log 切面类
 * 
 * @author wille
 *
 */

@Aspect
@Component
public class SystemLogAspect {

	// 注入Service用于把日志保存数据库
	@Resource
	private LogInfoService logInfoService;
	
	private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

	private final String LOG_KEY = "logInfo";

	/**
	 * Controller层切点
	 */
	@Pointcut("@annotation(com.base.annotation.log.SystemControllerLog)")
	public void controllerAspect() {
	}

	/**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * 
	 * @param joinPoint
	 *            切点
	 */
	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
		logger.info("Log aop ===== doBefore =====");
		try {
			HttpServletRequest request = getRequest();

			String username = UserSessionUtil.getCurrentUsername(request);
			// 请求的IP
			String ip = request.getRemoteAddr();
			// 方法路径
			String className = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			methodName = className + "." + methodName + "()";

			String serverIP = InetAddress.getLocalHost().getHostAddress();
			// *========控制台输出=========*//
			logger.info("=====前置通知开始=====");
			logger.info("请求方法:" + methodName);
			logger.info("请求人:" + username);
			logger.info("请求IP:" + ip);
			// *========数据库日志=========*//
			LogInfo log = new LogInfo();
			log.setMethod(methodName);
			log.setLogType("系统日志");
			log.setDescription("系统日志");
			log.setRequestIP(ip);
			log.setServerIP(serverIP);
			log.setExceptionCode(null);
			log.setExceptionDetail(null);
			log.setParams(null);
			log.setCreateBy(username);
			log.setCreateDate(Calendar.getInstance().getTime());
			// 把log对象放到request里面
			request.setAttribute(LOG_KEY, log);
			logger.info("=====前置通知结束=====");
		} catch (Exception e) {
			// 记录本地异常日志
			logger.error("==前置通知异常==");
			logger.error("异常信息:", e);
		}
	}

	/**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * 
	 * @param joinPoint
	 *            切点
	 */
	@After("controllerAspect()")
	public void doAfter(JoinPoint joinPoint) {
		logger.info("Log aop ===== doAfter =====");
		try {
			HttpServletRequest request = getRequest();
			// 获取用户请求方法的参数并序列化为JSON格式字符串
			String params = "";
			if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
				Object[] args = joinPoint.getArgs();
				for (int i = 0; i < args.length; i++) {
					params += args[i] + ";";
				}
			}

			LogInfo log = (LogInfo) request.getAttribute(LOG_KEY);
			log.setParams(params);
			Long nowTime = Calendar.getInstance().getTimeInMillis();
			Long startTime = log.getCreateDate().getTime();
			log.setRunTime(nowTime - startTime);
			// 保存数据库
			logInfoService.add(log);
			logger.info("=====后置通知结束=====");
		} catch (Exception e) {
			// 记录本地异常日志
			logger.error("==后置通知异常==");
			logger.error("异常信息:", e);
		}
	}

	@AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		logger.info("Log aop ===== doAfterThrowing =====");
		HttpServletRequest request = getRequest();
		// 获取用户请求方法的参数并序列化为JSON格式字符串
		String params = "";
		if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
			Object[] args = joinPoint.getArgs();
			for (int i = 0; i < args.length; i++) {
				params += args[i] + ";";
			}
		}

		LogInfo log = (LogInfo) request.getAttribute(LOG_KEY);
		log.setParams(params);
		log.setExceptionCode(e.getMessage());
		log.setExceptionDetail(e.getMessage());
		Long nowTime = Calendar.getInstance().getTimeInMillis();
		Long startTime = log.getCreateDate().getTime();
		log.setRunTime(nowTime - startTime);
		// 保存数据库
		logInfoService.add(log);

	}

	private HttpServletRequest getRequest(){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
	}
}
