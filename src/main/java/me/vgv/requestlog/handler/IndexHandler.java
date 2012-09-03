package me.vgv.requestlog.handler;


import me.vgv.requestlog.database.DBManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vasily Vasilkov (vgv@vgv.me)
 */
public class IndexHandler implements Handler {

    private final DBManager dbManager;

    public IndexHandler(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getWriter().write("Index");
    }
}
