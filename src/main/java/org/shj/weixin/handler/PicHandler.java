package org.shj.weixin.handler;

import net.sf.json.JSONObject;

import org.shj.weixin.enums.RespMsgType;
import org.shj.weixin.msg.TextMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 处理所有的 event为 pic_sysphoto, pic_photo_or_album 和 pic_weixin的菜单点击事件, 
 * 如果有多个此类菜单, 可根据EventKey的值（菜单的key值）来确定具体是哪个菜单.
 * 但是此Handler好像会被ImgHandler覆盖（测试几次均如此，所以并不完全确定）.
 * 建议此Handler直接返回null
 * 
 * @author Shen Huang Jian
 *
 */
public class PicHandler extends Handler{
	private Logger log = LoggerFactory.getLogger(PicHandler.class);

	/**
	 * <xml><ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>
		<FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>
		<CreateTime>1408090651</CreateTime>
		<MsgType><![CDATA[event]]></MsgType>
		<Event><![CDATA[pic_sysphoto]]></Event>
		<EventKey><![CDATA[6]]></EventKey>
		<SendPicsInfo><Count>1</Count>
		<PicList><item><PicMd5Sum><![CDATA[1b5f7c23b5bf75682a53e7b6d163e185]]></PicMd5Sum>
		</item>
		</PicList>
		</SendPicsInfo>
		</xml>
	 */
	public String handlerRequest(JSONObject jsonObj) {
		//扫码推事件的事件推送
		log.info("处理图片.。。。。");
		/*String picinfo = jsonObj.getString("SendPicsInfo");
		
		com.alibaba.fastjson.JSONObject picInfoObj = JSON.parseObject(picinfo);
		int count = picInfoObj.getIntValue("Count");
		log.info("用户发送了" + count + "张图片");
		
		ImgMsg img = new ImgMsg();
		this.setCommonValuesInMsg(jsonObj, img);
		img.setMsgType(RespMsgType.image.name());
		img.setMediaId("eW2V9YbWl7OLV64y05muxZAvmLRAg1mwsYA_DML3zQMBQcGZSwA3p-BBoeL6UymN");
		
		return img.toXml();
		TextMsg text = new TextMsg();
		setCommonValuesInMsg(jsonObj, text);
		text.setMsgType(RespMsgType.text.name());
		text.setContent("你发送了" + count + "张图片");
		
		return text.toXml();*/
		return null;
	}
}
