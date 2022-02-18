/*
 * HPPC
 *
 * Copyright (C) 2010-2022 Carrot Search s.c.
 * All rights reserved.
 *
 * Refer to the full license file "LICENSE.txt":
 * https://github.com/carrotsearch/hppc/blob/master/LICENSE.txt
 */
package com.carrotsearch.hppc;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/** A JUnit rule that forces assertions to be enabled. */
public class RequireAssertionsRule implements MethodRule {
  public Statement apply(Statement base, FrameworkMethod paramFrameworkMethod, Object paramObject) {
    checkAssertionsEnabled();
    return base;
  }

  /* */
  @Before
  public void checkAssertionsEnabled() {
    boolean enabled = true;
    try {
      assert false;
      enabled = false;
    } catch (AssertionError e) {
      // Expected, fall through.
    }

    assertTrue("Enable JVM assertions for testing.", enabled);
  }
}
