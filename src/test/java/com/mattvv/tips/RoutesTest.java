package com.mattvv.tips;

import static com.google.common.truth.Truth.assertThat;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.inject.Injector;

import javax.servlet.ServletContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RoutesTest {
  @Mock
  ServletContext context;

  @Test
  public void shouldDefineBaseIndexPath() {
    GuiceListener listener = new GuiceListener();
    Injector injector = listener.getInjector();
    assertThat(injector).isInstanceOf(Injector.class);
  }

  @Test
  public void shouldProvideATransport() {
    Routes routes = new Routes();
    assertThat(routes.providesTransport()).isInstanceOf(NetHttpTransport.class);
  }

  @Test
  public void shouldProvideAFlow() {
    Routes routes = spy(new Routes());
    when(context.getInitParameter("tips.clientID")).thenReturn("clientid");
    when(context.getInitParameter("tips.clientSecret")).thenReturn("secret");
    when(routes.servletContext()).thenReturn(context);

    assertThat(routes.providesFlow()).isInstanceOf(GoogleAuthorizationCodeFlow.class);
  }
}