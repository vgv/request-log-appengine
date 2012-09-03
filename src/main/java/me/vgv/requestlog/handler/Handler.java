package me.vgv.requestlog.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vasily Vasilkov (vgv@vgv.me)
 */
public interface Handler {

    public void handle(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
