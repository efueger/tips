package com.mattvv.tips;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class IndexTest {
  @Mock
  HttpServletRequest request;
  @Mock
  HttpServletResponse response;
  @Mock
  PrintWriter writer;

  @Test
  public void itShouldPrintOutHelloWorld() throws IOException, ServletException {

//    when(response.getWriter()).thenReturn(writer);
//    Index index = new Index();
//    index.doGet(request, response);
//    verify(writer).println("Hello, world - Flex Servlet");
  }
}