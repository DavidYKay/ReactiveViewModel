package com.reactiveviewmodel.demo.view;

import android.widget.TextView;

import rx.functions.Action1;

public class ViewActions {
  public static Action1<String> setText(final TextView view) {
    return new Action1<String>() {
      @Override
      public void call(String text) {
        view.setText(text);
      }
    };
  }
}
