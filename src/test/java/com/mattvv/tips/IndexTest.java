package com.mattvv.tips;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RunWith(MockitoJUnitRunner.class)
public class IndexTest {
  @Mock
  HttpServletRequest request;
  @Mock
  HttpServletResponse response;
  @Mock
  RequestDispatcher dispatcher;

  @Test
  public void shouldUseIndexPartial() throws IOException, ServletException {
    when(request.getRequestDispatcher("/base.jsp")).thenReturn(dispatcher);
    Index index = new Index();
    index.doGet(request, response);
    verify(request).setAttribute("page", "index");
    verify(request).getRequestDispatcher("/base.jsp");
    verify(dispatcher).forward(request, response);
  }
}
