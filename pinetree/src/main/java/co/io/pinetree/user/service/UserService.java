package co.io.pinetree.user.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface UserService {
	
	public List<Map<String, Object>> list(Map<String, Object> map) throws Exception;
	
	public Map<String, Object> view(Map<String, Object> map) throws Exception;
	
	public int cnt(Map<String, Object> map) throws Exception;
	
	public int insert(Map<String, Object> map) throws Exception;
	
	public int update(Map<String, Object> map) throws Exception;
	
	public int delete(Map<String, Object> map) throws Exception;
	
	public int join(Map<String, Object> map) throws Exception;
	
	public Map<String, Object> login(Map<String, Object> map, HttpSession session) throws Exception;
	
	public int initPasswordErrorCount(Map<String, Object> map) throws Exception;
	
	public int insertPasswordErrorCount(Map<String, Object> map) throws Exception;
	
	public boolean isValidUser(Map<String, Object> map) throws Exception;
	
}
