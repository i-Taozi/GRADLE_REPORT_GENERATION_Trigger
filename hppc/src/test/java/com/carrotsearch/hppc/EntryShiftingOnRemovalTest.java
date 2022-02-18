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

import static org.junit.Assert.*;

import com.carrotsearch.randomizedtesting.RandomizedTest;
import com.carrotsearch.randomizedtesting.annotations.Repeat;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class EntryShiftingOnRemovalTest extends RandomizedTest {
  @Test
  @Repeat(iterations = 10)
  public void testRemoveSanity() {
    @SuppressWarnings("deprecation")
    IntHashSet v =
        new IntHashSet(8, 0.5d) {
          @Override
          protected int hashKey(int key) {
            return key & 0xff;
          }
        };

    Set<Integer> ref = new HashSet<Integer>();
    for (int i = 0; i < 4; i++) {
      int r = randomInt() & 0xffff;
      ref.add(r);
      v.add(r);
    }

    Integer[] array = ref.toArray(new Integer[ref.size()]);
    int remove = randomFrom(array);
    ref.remove(remove);
    v.remove(remove);

    int[] actual = v.toArray();
    assertTrue(actual.length == ref.size());
    for (Integer ri : ref) {
      assertTrue(v.contains(ri));
    }
  }
}
