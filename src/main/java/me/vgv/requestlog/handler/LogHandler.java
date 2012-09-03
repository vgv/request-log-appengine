package me.vgv.requestlog.handler;

import me.vgv.requestlog.database.DBManager;
import me.vgv.requestlog.database.entity.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author Vasily Vasilkov (vgv@vgv.me)
 */
public class LogHandler implements Handler {

    private final DBManager dbManager;

    public LogHandler(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // parse request
        String key = request.getRequestURI();
        if (key.startsWith("/")) {
            key = key.substring(1);
        }

		Map<String, List<String>> parameters = new LinkedHashMap<String, List<String>>();

        for (Enumeration<String> parameterNames = request.getParameterNames(); parameterNames.hasMoreElements(); ) {
            String parameterName = parameterNames.nextElement();

			List<String> valueList = parameters.get(parameterName);
			if (valueList == null) {
				valueList = new LinkedList<String>();
				parameters.put(parameterName, valueList);
			}

			Collections.addAll(valueList, request.getParameterValues(parameterName));
        }

        String method = request.getMethod().toUpperCase();
        String schema = request.isSecure() ? "HTTPS" : "HTTP";

        // write request
        dbManager.save(new Request(key, method, schema, parameters));

        // response
        response.getWriter().write("ok");
    }

}
