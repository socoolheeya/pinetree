package co.io.pinetree.util.model;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class CommandMap {

	private Map<String, Object> map = new LinkedHashMap<String, Object>();
	private HttpServletRequest request;
	private HttpSession session;

	public CommandMap() {
	}

	public CommandMap(HttpServletRequest req) {

		this.request = req;
		this.session = req.getSession();

		Enumeration<?> enumeration = req.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			String[] values = req.getParameterValues(key);

			if (key.indexOf("[]") > -1) {
				key = key.substring(0, key.length() - 2);
				this.map.put(key, values);
			} else if (values != null) {
				this.map.put(key, (values.length > 1) ? values : values[0]);
			}
		}
	}

	public Object get(String key) {
		return map.get(key) == null ? "" : map.get(key);
	}

	public void put(String key, Object value) {
		map.put(key, value == null ? "" : value);
	}

	public Object remove(String key) {
		return map.remove(key);
	}

	public boolean containsKey(String key) {
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	public void clear() {
		map.clear();
	}

	public Set<Entry<String, Object>> entrySet() {
		return map.entrySet();
	}

	public Set<String> keySet() {
		return map.keySet();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public void putAll(Map<? extends String, ? extends Object> m) {
		if (m != null) {
			map.putAll(m);
		}
	}

	public Map<String, Object> getMap() {
		return new LinkedHashMap<String, Object>(this.map);
	}

	@SuppressWarnings("unchecked")
	public <T> T getSessionValue(String key, T defaultVal) {
		Object val = this.session.getAttribute(key);
		if (val == null) {
			return defaultVal;
		} else {
			return (T) val;
		}
	}

	public <T> T getSessionValue(String key) {
		return this.getSessionValue(key, null);
	}

	public void setSessionValue(String key, Object val) {
		this.session.setAttribute(key, val);
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpSession getSession() {
		return session;
	}

	public String toString() {
		return this.toString(false);
	}

	public String toString(boolean isPretty) {
		Set<Entry<String, Object>> entry = this.entrySet();

		ToStringStyle tss = isPretty ? ToStringStyle.MULTI_LINE_STYLE : ToStringStyle.SHORT_PREFIX_STYLE;
		ToStringBuilder tsb = new ToStringBuilder(this, tss);
		for (Entry<String, Object> e : entry) {
			tsb.append(e.getKey(), e.getValue());
			if (e.getValue() == null) {
				e.setValue("");
			}
		}

		return tsb.toString();
	}
}
