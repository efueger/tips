package com.mattvv.tips.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.inject.Singleton;

import java.io.IOException;
import java.util.HashMap;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// [START example]
@WebServlet(name = "oauth", value = "/oauth")
@Singleton
public class OAuth extends HttpServlet {
  private static final String USERINFO_ENDPOINT
      = "https://www.googleapis.com/plus/v1/people/me/openIdConnect";

  private GoogleAuthorizationCodeFlow flow;
  private HttpTransport transport;

  public OAuth() {
    super();
  }

  @Inject
  OAuth(GoogleAuthorizationCodeFlow flow, HttpTransport transport) {
    super();
    this.flow = flow;
    this.transport = transport;
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException, ServletException {

    // Ensure that this is no request forgery going on, and that the user
    // sending us this connect request is the user that was supposed to.
    if (req.getSession().getAttribute("state") == null
        || !req.getParameter("state").equals(req.getSession().getAttribute("state"))) {
      resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      resp.sendRedirect("/");
      return;
    }

    req.getSession().removeAttribute("state");     // Remove one-time use state.

    final TokenResponse tokenResponse =
        flow.newTokenRequest(req.getParameter("code"))
            .setRedirectUri(getServletContext().getInitParameter("tips.callback"))
            .execute();

    req.getSession().setAttribute("token", tokenResponse.toString()); // Keep track of the token.
    final Credential credential = flow.createAndStoreCredential(tokenResponse, null);
    final HttpRequestFactory requestFactory = transport.createRequestFactory(credential);

    final GenericUrl url = new GenericUrl(USERINFO_ENDPOINT);      // Make an authenticated request.
    final HttpRequest request = requestFactory.buildGetRequest(url);
    request.getHeaders().setContentType("application/json");

    final String jsonIdentity = request.execute().parseAsString();
    @SuppressWarnings("unchecked")
    HashMap<String, String> userIdResult =
        new ObjectMapper().readValue(jsonIdentity, HashMap.class);
    // From this map, extract the relevant profile info and store it in the session.
    req.getSession().setAttribute("userEmail", userIdResult.get("email"));
    req.getSession().setAttribute("userId", userIdResult.get("sub"));
    req.getSession().setAttribute("userImageUrl", userIdResult.get("picture"));
    resp.sendRedirect((String) req.getSession().getAttribute("loginDestination"));
  }
}
