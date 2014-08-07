package net.savka.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static net.savka.controllers.SessionAttributes.USER;

/**
 * Created with IntelliJ IDEA.
 * User: Vlad
 * Date: 25.05.14
 * Time: 19:20
 * To change this template use File | Settings | File Templates.
 */
public class UserLogoutController extends HttpServlet {
    public static final String REDIRECT_PAGE = "index.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            //удаляем атрибут сессии "user"
            session.removeAttribute(USER);
            //редиректим на страницу логина
            resp.sendRedirect(REDIRECT_PAGE);
        }
    }
}
