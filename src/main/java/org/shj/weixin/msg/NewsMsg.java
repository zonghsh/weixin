package org.shj.weixin.msg;

import java.util.List;

public class NewsMsg extends BaseMsg{
		
	private int articleCount;
	
	private List<Article> articles;
	
	
	protected String buildSelfPart(){
		StringBuffer sb = new StringBuffer();
		sb.append("<ArticleCount>").append(articleCount).append("</ArticleCount>")
		  .append("<Articles>");
		
		if(articles != null){
			for(Article art : articles){
				sb.append("<item>")
				  .append("<Title><![CDATA[").append(art.getTitle()).append("]]></Title> ")
				  .append("<Description><![CDATA[").append(art.getDescription()).append("]]></Description>")
				  .append("<PicUrl><![CDATA[").append(art.getPicUrl()).append("]]></PicUrl>")
				  .append("<Url><![CDATA[").append(art.getUrl()).append("]]></Url>")
				  .append("</item>");
			}
		}
		
		sb.append("</Articles>");
		return sb.toString();
	}
	
	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}



	public class Article{
		/** 图文消息标题 */
		private String title;
		
		/** 图文消息描述 */
		private String description;
		
		/** 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200 */
		private String picUrl;
		
		/** 点击图文消息跳转链接 */
		private String url;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getPicUrl() {
			return picUrl;
		}

		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
		
		
	}

}
