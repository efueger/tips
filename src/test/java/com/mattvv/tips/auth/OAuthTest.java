package com.mattvv.tips.auth;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.json.Json;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OAuthTest {
  @Mock
  HttpServletRequest request;
  @Mock
  HttpServletResponse response;
  @Mock
  HttpSession session;
  @Mock
  ServletConfig config;
  @Mock
  ServletContext context;
  @Mock
  GoogleAuthorizationCodeFlow flow;


  @Before
  public void before() {
    when(request.getSession()).thenReturn(session);
    when(config.getServletContext()).thenReturn(context);
  }

  @Test
  public void shouldRedirectToHomeIfNoSession() throws IOException, ServletException {
    when(session.getAttribute("state")).thenReturn(null);

    OAuth oauth = new OAuth();
    oauth.doGet(request, response);

    verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    verify(response).sendRedirect("/");
  }

  @Test
  public void shouldRedirectToHomeIfSessionDoesNotEqualState() throws
      IOException, ServletException {
    when(session.getAttribute("state")).thenReturn("session1");
    when(request.getParameter("state")).thenReturn("session2");

    OAuth oauth = new OAuth();
    oauth.doGet(request, response);

    verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    verify(response).sendRedirect("/");
  }

  @Test
  public void shouldLogin() throws IOException, ServletException {
    when(session.getAttribute("state")).thenReturn("session");
    when(request.getParameter("state")).thenReturn("session");
    when(request.getParameter("code")).thenReturn("code");
    when(context.getInitParameter("tips.callback")).thenReturn("http://localhost:8080/oauth");

    GoogleAuthorizationCodeTokenRequest tokenRequest =
        mock(GoogleAuthorizationCodeTokenRequest.class);
    GoogleTokenResponse tokenResponse = mock(GoogleTokenResponse.class);

    when(flow.newTokenRequest("code")).thenReturn(tokenRequest);
    when(tokenRequest.setRedirectUri("http://localhost:8080/oauth")).thenReturn(tokenRequest);
    when(tokenRequest.execute()).thenReturn(tokenResponse);

    HttpTransport loggedInUser = new MockHttpTransport() {
      @Override
      public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
        return new MockLowLevelHttpRequest() {
          @Override
          public LowLevelHttpResponse execute() throws IOException {
            MockLowLevelHttpResponse response = new MockLowLevelHttpResponse();
            response.setContentType(Json.MEDIA_TYPE);
            response.setContent("{\"email\":\"test@test.com\", \"userId\":\"abc\", \"userImageUrl\":\"http://path.to/image\"}");
            return response;
          }
        };
      }
    };

    OAuth oauth = new OAuth(flow, loggedInUser);
    oauth.init(config);
    oauth.doGet(request, response);
  }

}