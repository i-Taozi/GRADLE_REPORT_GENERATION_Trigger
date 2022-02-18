/*
 * HPPC
 *
 * Copyright (C) 2010-2022 Carrot Search s.c.
 * All rights reserved.
 *
 * Refer to the full license file "LICENSE.txt":
 * https://github.com/carrotsearch/hppc/blob/master/LICENSE.txt
 */
package com.carrotsearch.hppc.generator.intrinsics;

import com.carrotsearch.hppc.generator.TemplateOptions;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class Cast extends AbstractIntrinsicMethod {
  @Override
  public void invoke(
      Matcher m,
      StringBuilder sb,
      TemplateOptions templateOptions,
      String genericCast,
      ArrayList<String> params) {
    expectArgumentCount(m, params, 1);

    String cast = inferTemplateCastName(m, templateOptions, genericCast);
    switch (genericCast) {
      case "KType[]":
      case "KType":
        if (!templateOptions.hasKType()) {
          throw new RuntimeException(
              "KType is not available for casting in this template?: " + m.group());
        }
        if (templateOptions.isKTypeGeneric()) {
          cast = "(" + genericCast + ")";
        } else {
          cast = ""; // dropped
        }
        break;

      case "VType[]":
      case "VType":
        if (!templateOptions.hasVType()) {
          throw new RuntimeException(
              "VType is not available for casting in this template?: " + m.group());
        }
        if (templateOptions.isVTypeGeneric()) {
          cast = "(" + genericCast + ")";
        } else {
          cast = ""; // dropped
        }
        break;

      default:
        throw unreachable();
    }

    sb.append(cast + " " + params.get(0));
  }
}
