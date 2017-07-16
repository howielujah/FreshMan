package website.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import website.common.ParseXml;
import website.dao.IndustryDao;

@Service("industryDao")
public class IndustryDaoImpl implements IndustryDao {
    private JdbcTemplate jdbcTemplate;
    
    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Map<String, Object>> queryIndustry(int page, int rows) throws Exception {
    	
    	String sql = ParseXml.getSqlByName("Industry.queryIndustry");
    	
    	List<Object> condition = new ArrayList<Object>();
    	condition.add((page - 1) * rows);
    	condition.add(page * rows);
    	
        return jdbcTemplate.queryForList(sql, condition.toArray());
    }
    
    @Override
    public long getIndustryCount() throws Exception {
    	
    	String sql = ParseXml.getSqlByName("Industry.getIndustryCount");
    	
    	List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
    	
    	return (Long)result.get(0).get("COUNT");
    }

    @Override
    public void insertIndustry(String indName, String indDesc) throws Exception {
    	
    	String sql = ParseXml.getSqlByName("Industry.insertIndustry");

        List<Object> condition = new ArrayList<Object>();
        condition.add(indName);
        condition.add(indDesc);

        jdbcTemplate.update(sql, condition.toArray());
    }

    @Override
    public void updateIndustry(String pk, String indName, String indDesc) throws Exception {
    	
    	String sql = ParseXml.getSqlByName("Industry.updateIndustry");
    	
    	List<Object> condition = new ArrayList<Object>();
    	condition.add(indName);
    	condition.add(indDesc);
    	condition.add(pk);

        jdbcTemplate.update(sql, condition.toArray());
    }

    @Override
    public void deleteIndustry(String pk) throws Exception {
    	
    	String sql = ParseXml.getSqlByName("Industry.deleteIndustry");

        List<Object> condition = new ArrayList<Object>();
        condition.add(pk);

        jdbcTemplate.update(sql, condition.toArray());
    }
}
