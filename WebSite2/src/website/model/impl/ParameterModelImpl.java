package website.model.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import website.dao.IndustryDao;
import website.model.ParameterModel;

@Service("parameterModel")
public class ParameterModelImpl implements ParameterModel{
	
	private IndustryDao industryDao;
	
	@Resource(name = "industryDao")
	public void setIndustryDao (IndustryDao industryDao) throws Exception {
		this.industryDao = industryDao;
	}
	
	@Override
	public List<Map<String, Object>> queryIndustry(int page, int rows) throws Exception {
		return industryDao.queryIndustry(page, rows);
	}
	
	@Override
    public long getIndustryCount() throws Exception {
		return industryDao.getIndustryCount();
    }

    @Override
    public void insertIndustry(String indName, String indDesc) throws Exception {
    	industryDao.insertIndustry(indName, indDesc);
    }

    @Override
    public void updateIndustry(String pk, String indName, String indDesc) throws Exception {
    	industryDao.updateIndustry(pk, indName, indDesc);
    }

    @Override
    public void deleteIndustry(String pk) throws Exception {
    	industryDao.deleteIndustry(pk);
    }
}
