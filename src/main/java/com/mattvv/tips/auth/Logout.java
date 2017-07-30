package com.mattvv.tips.auth;

import com.google.inject.Singleton;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Singleton
public class Logout extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {
    HttpSession session = req.getSession(false);
    if (session != null) {
      session.invalidate();
    }
    req.getSession();
    resp.sendRedirect("/");
  }
}
