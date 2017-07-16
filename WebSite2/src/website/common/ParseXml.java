package website.common;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ParseXml {
	
	public static String getSqlByName (String sqlName) throws Exception {
		//讀取XML文件,獲得document對象.
		SAXReader reader = new SAXReader();
		//取得Sql.xml路徑得到Sql.xml檔再用reader去讀
		Document   document = reader.read(ParseXml.class.getResource("/Sql.xml").getFile());
	    //獲取文檔的根節點
	    Element rootElm = document.getRootElement();
	    //取得某節點的單個子節點
	    Element memberElm=rootElm.element(sqlName);
	    String text=memberElm.getText();
		return text;
	}
}
