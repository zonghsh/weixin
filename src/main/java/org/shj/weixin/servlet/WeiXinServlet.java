package org.shj.weixin.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.shj.weixin.handler.Handler;
import org.shj.weixin.handler.HandlerFactory;
import org.shj.weixin.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WeiXinServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7644907932703908226L;

	private Logger log = LoggerFactory.getLogger(WeiXinServlet.class);
	
	private WeiXinSupport support;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		connect(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		message(request, response);
	}

	/**
	 * @author haibing.xiao
	 * @return
	 * @exception
	 * @param
	 * 
	 *        <p>
	 *        接入连接生效验证
	 *        </p>
	 */
	private void connect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("RemoteAddr: " + request.getRemoteAddr());
		log.info("QueryString: " + request.getQueryString());
		
		if (!support.isValidServerConfig(request)) {
			log.info("服务器接入失败.......");
			return;
		}
		
		log.info("服务器接入生效..........");
		response.getWriter().print(request.getParameter("echostr"));// 完成相互认证
	}


	/**
	 * @author haibing.xiao
	 * @return
	 * @exception ServletException
	 *                , IOException
	 * @param
	 * 
	 *        <p>
	 *        XML组装组件
	 *        </p>
	 */
	private void message(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		InputStream is = request.getInputStream();
		// 取HTTP请求流长度
		int size = request.getContentLength();
		// 用于缓存每次读取的数据
		byte[] buffer = new byte[size];
		// 用于存放结果的数组
		byte[] xmldataByte = new byte[size];
		int count = 0;
		int rbyte = 0;
		// 循环读取
		while (count < size) {
			// 每次实际读取长度存于rbyte中
			rbyte = is.read(buffer);
			for (int i = 0; i < rbyte; i++) {
				xmldataByte[count + i] = buffer[i];
			}
			count += rbyte;
		}
		is.close();
		String requestStr = new String(xmldataByte, "UTF-8");

		try {
			manageMessage(requestStr, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author haibing.xiao
	 * @return
	 * @exception ServletException
	 *                , IOException
	 * @param
	 * 
	 *        <p>
	 *        业务转发组件
	 *        </p>
	 * 
	 */
	private void manageMessage(String requestStr, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.info(requestStr);
		
		try{
			requestStr = support.decryptRequest(requestStr, request);
			XMLSerializer xmlSerializer = new XMLSerializer();
			JSONObject jsonObject = (JSONObject) xmlSerializer.read(requestStr);
			
			Handler handler = HandlerFactory.factory.createHandler(jsonObject);
			String replyMsg = handler.handlerRequest(jsonObject);
			
			if(!StringUtil.isEmpty(replyMsg)){
				log.info("responseStr:" + replyMsg);
				replyMsg = support.encryptMsg(replyMsg);
				log.info("responseStr:" + replyMsg);
				OutputStream os = response.getOutputStream();
				os.write(replyMsg.getBytes("UTF-8"));
				os.flush();
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	@Override
	public void init() throws ServletException {
		support =  new WeiXinSupport();
	}

	

}
