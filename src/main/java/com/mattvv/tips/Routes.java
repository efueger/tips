package com.mattvv.tips;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.common.annotations.VisibleForTesting;
import com.google.inject.Provides;
import com.google.inject.servlet.ServletModule;
import java.util.Arrays;
import java.util.Collection;
import javax.servlet.ServletContext;
import com.mattvv.tips.auth.Login;
import com.mattvv.tips.auth.Logout;
import com.mattvv.tips.auth.OAuth;
import org.javalite.activejdbc.Base;

class Routes extends ServletModule {
  @Override
  protected void configureServlets() {
    serve("/").with(Index.class);
    serve("/login").with(Login.class);
    serve("/logout").with(Logout.class);
    serve("/oauth").with(OAuth.class);
    serve("/teams").with(Teams.class);
  }

  @Provides
  HttpTransport providesTransport() {
    return new NetHttpTransport();
  }

  @Provides
  GoogleAuthorizationCodeFlow providesFlow() {
    final Collection<String> scopes = Arrays.asList("email", "profile");
    final JsonFactory jacksonFactory = new JacksonFactory();

    return new GoogleAuthorizationCodeFlow.Builder(
        providesTransport(),
        jacksonFactory,
        servletContext().getInitParameter("tips.clientID"),
        servletContext().getInitParameter("tips.clientSecret"),
        scopes).build();
  }

  @VisibleForTesting
  ServletContext servletContext() {
    return getServletContext();
  }
}
