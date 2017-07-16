package website.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import website.model.ParameterModel;

@Controller
public class IndustryController {
	
	private ParameterModel parameterModel;
	
	@Resource(name = "parameterModel")
	public void setParameterModel(ParameterModel parameterModel) {
		this.parameterModel = parameterModel;
	}

	@RequestMapping("/fetchList.do")
    public void doQuery(HttpServletRequest req,
            HttpServletResponse res) throws Exception {
        
        //取得第幾頁資料
        String page = req.getParameter("page");
        
        //每頁顯示的筆數
        String pageRows = req.getParameter("rows");
        
        // 暫存
        int queryPage = Integer.parseInt(page);
        int queryRows = Integer.parseInt(pageRows);
        
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        
        while(true) {
        	
	        List<Map<String, Object>> resultMap = parameterModel.queryIndustry(queryPage, queryRows);
	        
	        jsonArray = JSONArray.fromObject(resultMap);
	        json.put("rows", jsonArray);
	        
	        if ("1".equals(page) || resultMap.size() > 0) {
	        	break;
	        } else {
	        	queryPage--;
	        }
        }
        
        json.put("page", queryPage);
        json.put("records", jsonArray.size());
        
        // 取得總頁數
        long total = parameterModel.getIndustryCount();
        json.put("total", Math.ceil((double)total / Double.parseDouble(pageRows)));

        res.setContentType("application/json; charset=utf-8");
        res.getWriter().print(json);
    }
	
	@RequestMapping("/edit.do")
    public void doEdit(HttpServletRequest req,
            HttpServletResponse res) throws Exception {
//        Map<String, String[]> map = req.getParameterMap();
//
//        for (String s : map.keySet()) {
//            System.out.println(s + " : " + map.get(s)[0]);
//        }

        String op = req.getParameter("oper");
        String pk = req.getParameter("PK");
        String indName = req.getParameter("IND_NAME");
        String indDesc = req.getParameter("IND_DESC");

        if ("add".equals(op)) {
        	parameterModel.insertIndustry(indName, indDesc);
        } else if ("edit".equals(op)) {
        	parameterModel.updateIndustry(pk, indName, indDesc);
        } else if ("del".equals(op)) {
        	parameterModel.deleteIndustry(pk);
        }
    }
}
