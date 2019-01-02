package co.io.pinetree.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import co.io.pinetree.user.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public List<Map<String, Object>> list(Map<String, Object> map) throws Exception {
		return userDao.userList(map);
	}

	@Override
	public Map<String, Object> view(Map<String, Object> map) throws Exception {
		return userDao.userView(map);
	}

	@Override
	public int cnt(Map<String, Object> map) throws Exception {
		return userDao.userCnt(map);
	}

	@Override
	public int insert(Map<String, Object> map) throws Exception {
		return userDao.userInsert(map);
	}

	@Override
	public int update(Map<String, Object> map) throws Exception {
		return userDao.userUpdate(map);
	}

	@Override
	public int delete(Map<String, Object> map) throws Exception {
		return userDao.userDelete(map);
	}

	@Override
	public int join(Map<String, Object> map) throws Exception {
		String password = (String)map.get("password");
		map.put("password", encoder.encode(password));
		
		int result = insert(map);
		
		return result;
	}

	@Override
	public Map<String, Object> login(Map<String, Object> map, HttpSession session) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", map.get("userId"));
		Map<String, Object> user = view(paramMap);
		if(user == null) {
			resultMap.put("msg", "존재하지 않는 ID입니다.");
			resultMap.put("success", false);
			
			return resultMap;
		} else {
			int errorCnt = (Integer)user.get("errCnt");
			if(errorCnt >= 5) {
				resultMap.put("msg", "로그인 시도 5회 초과");
				resultMap.put("success", false);
				
				return resultMap;
			}
			
			String dbPassword = (String)user.get("password");
			String password = (String)map.get("password");
			if(dbPassword.equals(password)) {
				session.setAttribute("userId", map.get("userId"));
				session.setAttribute("name", map.get("name"));
				
				passwordErrorCountInit(paramMap);
				
				resultMap.put("success", true);
			} else {
				paramMap.put("errCnt", errorCnt++);
				passwordErrorCountAdd(paramMap);
				
				resultMap.put("msg", "비밀번호 입력 실패");
				resultMap.put("success", false);
				
				return resultMap;
			}
		}
		
		return resultMap;
	}

	@Override
	public int passwordErrorCountInit(Map<String, Object> map) throws Exception {
		return userDao.passwordErrorCountInit(map);
	}

	@Override
	public int passwordErrorCountAdd(Map<String, Object> map) throws Exception {
		return userDao.passwordErrorCountAdd(map);
	}

}
