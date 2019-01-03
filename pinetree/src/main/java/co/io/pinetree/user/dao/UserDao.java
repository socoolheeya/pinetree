package co.io.pinetree.user.dao;

import java.util.List;
import java.util.Map;

public interface UserDao {
	 
	public List<Map<String, Object>> userList(Map<String, Object> map) throws Exception;
	
	public int userCnt(Map<String, Object> map) throws Exception;
	
	public Map<String, Object> userView(Map<String, Object> map) throws Exception;
	
	public int userInsert(Map<String, Object> map) throws Exception;
	
	public int userUpdate(Map<String, Object> map) throws Exception;
	
	public int userDelete(Map<String, Object> map) throws Exception;
	
	public int initPasswordErrorCount(Map<String, Object> map) throws Exception;

	public int insertPasswordErrorCount(Map<String, Object> map) throws Exception;
	
}
