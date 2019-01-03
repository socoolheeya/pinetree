package co.io.pinetree.user.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SqlSessionTemplate dao;
	
	@Override
	public List<Map<String, Object>> userList(Map<String, Object> map) throws Exception {
		return dao.selectList("user.userList", map);
	}

	@Override
	public int userCnt(Map<String, Object> map) throws Exception {
		return dao.selectOne("user.userCnt", map);
	}

	@Override
	public Map<String, Object> userView(Map<String, Object> map) throws Exception {
		return dao.selectOne("user.userView", map);
	}

	@Override
	public int userInsert(Map<String, Object> map) throws Exception {
		return dao.insert("user.userInsert", map);
	}

	@Override
	public int userUpdate(Map<String, Object> map) throws Exception {
		return dao.update("user.userUpdate", map);
	}

	@Override
	public int userDelete(Map<String, Object> map) throws Exception {
		return dao.update("user.userDelete", map);
	}

	@Override
	public int initPasswordErrorCount(Map<String, Object> map) throws Exception {
		return dao.update("user.initPasswordErrorCount", map);
	}

	@Override
	public int insertPasswordErrorCount(Map<String, Object> map) throws Exception {
		return dao.update("user.insertPasswordErrorCount", map);
	}
	
	
}
