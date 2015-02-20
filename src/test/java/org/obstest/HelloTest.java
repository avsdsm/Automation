package org.obstest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.obsplatform.Hello;


public class HelloTest {
  @Test
  public void testHelloMethod() {
    assertEquals(Hello.hello(), "Hello, World!");
  }
}
