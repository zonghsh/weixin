package org.shj.weixin.listener;



import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.shj.weixin.executor.AccessTokenExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccessTokenListener implements ServletContextListener{
	
	private Logger log = LoggerFactory.getLogger(AccessTokenListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
		service.scheduleAtFixedRate(new AccessTokenExecutor(), 1, 7000, TimeUnit.SECONDS);
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
