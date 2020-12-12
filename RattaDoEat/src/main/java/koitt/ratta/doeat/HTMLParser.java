package koitt.ratta.doeat;

/**
 * 
 * @author 진민영
 *
 */
public class HTMLParser {
	
	/**
	 * HTML 내에서 제일 처음 img를 찾아서 경로+파일명을 반환
	 */
	public String getFirstImgFilePath(String html) {
		html = html.substring(html.indexOf("<img"));
		html = html.substring(html.indexOf("src=")+4);
		html = html.substring(0, html.indexOf(">"));
		return html;
	}
	
}
