package com.reactiveviewmodel.demo.converter;

import rx.functions.Func1;

public class CharSequenceToStringConverter implements Func1<CharSequence, String> {
  @Override
  public String call(CharSequence charSequence) {
    return charSequence != null
        ? charSequence.toString()
        : null;
  }
}
