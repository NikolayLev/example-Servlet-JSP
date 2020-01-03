package ru.levchenko.servletsApp.servlets;

import ru.levchenko.servletsApp.dao.models.User;
import ru.levchenko.servletsApp.dao.models.UserRole;
import ru.levchenko.servletsApp.dao.models.UserRolesENUM;
import ru.levchenko.servletsApp.repositories.UserWithRolesRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    UserWithRolesRepo userWithRolesRepo = UserWithRolesRepo.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = userWithRolesRepo.userList();
        req.setAttribute("userList", userList);
        req.getServletContext().getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName = req.getParameter("userName");
        String password = req.getParameter("password");

        boolean isUser = req.getParameter("user") != null;
        boolean isModerator = req.getParameter("moderator") != null;
        boolean isAdmin = req.getParameter("admin") != null;


        User user = new User(userName, password);
        if (req.getParameter("id") != null) {
            user.setId(Integer.parseInt(req.getParameter("id")));
        }

        if (isUser) {
            UserRole userRole = userWithRolesRepo.userRole(UserRolesENUM.USER);
            user.getRoles().add(userRole);
        } else {
            user.setRoles(
                    user.getRoles()
                            .stream()
                            .filter(a -> a.getRole() != UserRolesENUM.USER)
                            .collect(Collectors.toList()));
        }
        if (isAdmin) {
            UserRole userRole = userWithRolesRepo.userRole(UserRolesENUM.ADMIN);
            user.getRoles().add(userRole);
        } else {
            user.setRoles(
                    user.getRoles()
                            .stream()
                            .filter(a -> a.getRole() != UserRolesENUM.ADMIN)
                            .collect(Collectors.toList()));
        }
        if (isModerator) {
            UserRole userRole = userWithRolesRepo.userRole(UserRolesENUM.MODERATOR);
            user.getRoles().add(userRole);
        } else {
            user.setRoles(
                    user.getRoles()
                            .stream()
                            .filter(a -> a.getRole() != UserRolesENUM.MODERATOR)
                            .collect(Collectors.toList()));
        }
        userWithRolesRepo.save(user);

        resp.sendRedirect("home");

    }


}



