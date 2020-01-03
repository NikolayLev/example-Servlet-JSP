package ru.levchenko.servletsApp.servlets;

import ru.levchenko.servletsApp.repositories.UserWithRolesRepo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    UserWithRolesRepo userWithRolesRepo = UserWithRolesRepo.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userWithRolesRepo.deleteUser(Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect("home");
    }
}