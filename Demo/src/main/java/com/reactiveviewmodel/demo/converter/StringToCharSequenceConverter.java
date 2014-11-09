package com.reactiveviewmodel.demo.converter;

import rx.functions.Func1;

public class StringToCharSequenceConverter implements Func1<String, CharSequence> {
  @Override
  public CharSequence call(String s) {
    return s;
  }
}
