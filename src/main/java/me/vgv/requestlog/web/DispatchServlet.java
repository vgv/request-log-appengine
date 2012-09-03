package me.vgv.requestlog.web;

import me.vgv.requestlog.database.DBManager;
import me.vgv.requestlog.handler.Handler;
import me.vgv.requestlog.handler.IndexHandler;
import me.vgv.requestlog.handler.LogHandler;
import me.vgv.requestlog.handler.WatchHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Vasily Vasilkov (vgv@vgv.me)
 */
public class DispatchServlet extends HttpServlet {

    private final DBManager dbManager = new DBManager();
    private final Handler indexHandler = new IndexHandler(dbManager);
    private final Handler logHandler = new LogHandler(dbManager);
    private final Handler watchHandler = new WatchHandler(dbManager);

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            internalService(request, response);
        } catch (Exception e) {
            response.getWriter().write("ERROR: " + e.getMessage());
        }
    }

    protected void internalService(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("/".equals(request.getRequestURI())) {
            indexHandler.handle(request, response);
            return;
        }

        if ("watch".equals(request.getQueryString())) {
            watchHandler.handle(request, response);
        } else {
            logHandler.handle(request, response);
        }

    }

}