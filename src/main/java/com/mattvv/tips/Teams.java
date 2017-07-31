package com.mattvv.tips;

import java.io.IOException;
import com.google.inject.Singleton;
import com.mattvv.tips.models.Team;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Singleton
public class Teams extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {
    getServletContext().setAttribute("teams", Team.getTeams());
    req.setAttribute("page", "teams");
    req.getRequestDispatcher("/base.jsp").forward(req, resp);
  }
}
