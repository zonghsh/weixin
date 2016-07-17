package org.shj.weixin.menu;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;


public class JsonTest {
	
	public static void main(String[] args){
		String xml = "<xml><ToUserName><![CDATA[gh_c21dc119c81d]]></ToUserName>"
				+"<FromUserName><![CDATA[ouLn5wa_Vb0gRjERYdApqUOweeDU]]></FromUserName>"
				+"<CreateTime>1468755445</CreateTime>"
				+"<MsgType><![CDATA[event]]></MsgType>"
				+"<Event><![CDATA[scancode_push]]></Event>"
				+"<EventKey><![CDATA[m1s3]]></EventKey>"
				+"<ScanCodeInfo><ScanType><![CDATA[qrcode]]></ScanType>"
				+"<ScanResult><![CDATA[http://cnbingapp.chinacloudsites.cn/qrcode?tag=BIASE]]></ScanResult>"
				+"</ScanCodeInfo>"
				+"</xml>";
		
		XMLSerializer xmlSerializer = new XMLSerializer();
		JSONObject jsonObject = (JSONObject) xmlSerializer.read(xml);
		System.out.println(jsonObject.get("ScanCodeInfo"));
	}

	
}
