package net.savka.controllers;

import net.savka.dao.UserDao;
import net.savka.dao.impl.JdbcUserDao;
import net.savka.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static net.savka.controllers.SessionAttributes.USER;

public class UserLoginController extends HttpServlet {
    public static final String PARAMETER_LOGIN = "login";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String ERROR_MASSAGE = "Invalid login or password";
    public static final String BASE_PAGE = "index.jsp";
    public static final String USER_PAGE = "WEB-INF/user.jsp";
    public static final String ADMIN_PAGE = "WEB-INF/admin.jsp";
    // public static Log LOG = LogFactory.getLog(UserLoginController.class); todo


    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // считываем параметры запроса (логин и пароль введенный юзером)
        final String login = req.getParameter(PARAMETER_LOGIN);
        String password = req.getParameter(PARAMETER_PASSWORD);
        if (login != null && password != null) {
            User user = null;
            //ищем соответствующего юзера в Базе Данных.
            UserDao jdbcUserDao = new JdbcUserDao();
            user = jdbcUserDao.findByLogin(login);
            if (user == null) {

                req.setAttribute("errorMassage", ERROR_MASSAGE);

                req.getRequestDispatcher(BASE_PAGE).forward(req, res);
                return;
            }
            if (user.getPassword().equals(password)) {
                // берем сессию из Web Container, если ее нет, то принудительно создаем
                HttpSession session = req.getSession(true);
                // сохраняем пользователя в сессии
                session.setAttribute(USER, user);
                // если роль пользователя = user, то перекидываем, на страницу для юзера, если админ, то на страницу админа
                if (user.getRole().getName().equals("user")) {
                    req.getRequestDispatcher(USER_PAGE).forward(req, res);
                }
                if (user.getRole().getName().equals("admin")) {
                    req.getRequestDispatcher(ADMIN_PAGE).forward(req, res);
                }
                return;
            } else {
                // если логин/пароль не верный, то показываем сообщение об ошибке
                req.setAttribute("errorMassage", ERROR_MASSAGE);
                req.getRequestDispatcher(BASE_PAGE).forward(req, res);
                return;
            }
        }
        res.sendRedirect(BASE_PAGE);
    }
}