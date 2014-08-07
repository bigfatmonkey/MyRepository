package net.savka.controllers;

import net.savka.dao.impl.JdbcUserDao;
import net.savka.entities.Role;
import net.savka.entities.User;
import net.savka.validator.UserValidatorImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class RegisterNewUserController extends HttpServlet {
    public static final String PARAM_LOGIN = "login";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_ROLE = "role";
    public static final String PARAM_EMAIL = "email";
    public static final String PARAM_FIRST_NAME = "FirstName";
    public static final String PARAM_LAST_NAME = "LastName";
    // public static final String PARAM_BIRTH_DATE = "BirthDate";
    public static final String PAGE_REGISTERED = "WEB-INF/registered.jsp";
    public static final String PAGE_MORE_INFO = "register.jsp";

    @Override
    //если пришел HTTP запрос с Post, то прокидываем его в метод doGet
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String login = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);
        final String email = request.getParameter(PARAM_EMAIL);
        final String role = request.getParameter(PARAM_ROLE);
        final String firstName = request.getParameter(PARAM_FIRST_NAME);
        final String lastName = request.getParameter(PARAM_LAST_NAME);
        //  final Date birthDate = request.getParameter(PARAM_BIRTH_DATE); //  todo

        if (login == null && password == null && email == null) {
            request.getRequestDispatcher(PAGE_MORE_INFO).forward(request, response);
            return;
        }
        //Создаем юзера, инициализируем его поля данными с формы регистрации
        final User tmpUser = new User();
        if (role.equals("user")) {
            Role tmpRole = new Role((long) 1);
            tmpRole.setName("user");
            tmpUser.setRole(tmpRole);
        }
        if (role.equals("admin")) {
            Role tmpRole = new Role((long) 2);
            tmpRole.setName("admin");
            tmpUser.setRole(tmpRole);
        }
        tmpUser.setLogin(login);
        tmpUser.setPassword(password);
        tmpUser.setEmail(email);
        tmpUser.setFirstName(firstName);
        tmpUser.setLastName(lastName);
        //tmpUser.setBirthDate(birthDate);  // todo
        // проверяем, коректность полей юзера, при помощи класса UserValidatorImpl
        UserValidatorImpl userValidator = new UserValidatorImpl();
        final Map<String, String> errorMap = userValidator.validate(tmpUser);
        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        // проверяем на уникальность нового юзера
        if (errorMap.isEmpty()) {
            //если в базе данных пользователь с таким логином или емаейлом уже существует, то добавляем в еррорМапу ошибку
            if (jdbcUserDao.findByLogin(login) != null) {
                errorMap.put("login", "Such login exists!");
            }
            if (jdbcUserDao.findByEmail(email) != null) {
                errorMap.put("email", "Such email exists!");
            }
        }
        if (errorMap.isEmpty()) {
            // если ошибок нет, то создаем в базе данных нового пользователя
            jdbcUserDao.create(tmpUser);
            // добавляем запросу - єто данные для отображение на странице: registered.jsp
            request.setAttribute("user", tmpUser);
            // создаем сессию с атрибутом юзер- это означает статус "залогинен"
            request.getSession(true).setAttribute("user", tmpUser);
            // переход на страцу registered.jsp
            request.getRequestDispatcher(PAGE_REGISTERED).forward(request, response);
            return;
        }
        //если были ошибки при регистрации, то перерендериваем страницу регистрации уже с ошибками.
        request.setAttribute("login", login);
        request.setAttribute("password", password);
        request.setAttribute("email", email);
        request.setAttribute("errorMap", errorMap);
        request.getRequestDispatcher(PAGE_MORE_INFO).forward(request, response);
    }
}


