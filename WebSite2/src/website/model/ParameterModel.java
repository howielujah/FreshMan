package website.model;

import java.util.List;
import java.util.Map;

public interface ParameterModel {
	
	public List<Map<String, Object>> queryIndustry(int page, int rows) throws Exception;
	
    public long getIndustryCount() throws Exception;

    public void insertIndustry(String indName, String indDesc) throws Exception;

    public void updateIndustry(String pk, String indName, String indDesc) throws Exception;

    public void deleteIndustry(String pk) throws Exception;
}
