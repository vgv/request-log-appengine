package me.vgv.requestlog.database.entity;

import java.util.List;
import java.util.Map;

/**
 * @author Vasily Vasilkov (vasily.vasilkov@gmail.com)
 */
public final class Request {

	private final String key;
	private final String method;
	private final String schema;
	private final Map<String, List<String>> parameters;

	public Request(String key, String method, String schema, Map<String, List<String>> parameters) {
		this.key = key;
		this.method = method;
		this.schema = schema;
		this.parameters = parameters;
	}

	public String getKey() {
		return key;
	}

	public String getMethod() {
		return method;
	}

	public String getSchema() {
		return schema;
	}

	public Map<String, List<String>> getParameters() {
		return parameters;
	}
}



