package com.mattvv.tips;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Index extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
      req.setAttribute("page", "index");
      req.getRequestDispatcher("/base.jsp").forward(req, resp);
    }
}
