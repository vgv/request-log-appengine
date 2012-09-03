package me.vgv.requestlog.database.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Vasily Vasilkov (vasily.vasilkov@gmail.com)
 */
public final class Response {

    private final long id;
    private final String key;
    private final Date date;
    private final String method;
    private final String schema;
    private final Map<String, List<String>> parameters;

	public Response(long id, String key, Date date, String method, String schema, Map<String, List<String>> parameters) {
		this.id = id;
		this.key = key;
		this.date = date;
		this.method = method;
		this.schema = schema;
		this.parameters = parameters;
	}

	public long getId() {
		return id;
	}

	public String getKey() {
		return key;
	}

	public Date getDate() {
		return date;
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
