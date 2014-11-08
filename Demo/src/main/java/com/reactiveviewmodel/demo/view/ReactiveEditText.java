package com.reactiveviewmodel.demo.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.util.AttributeSet;
import android.widget.EditText;

import com.reactiveviewmodel.core.ReactiveProperty;

public class ReactiveEditText extends EditText {
  public final ReactiveProperty<CharSequence> text = new ReactiveProperty<>();

  public ReactiveEditText(Context context) {
    super(context);
    init();
  }

  public ReactiveEditText(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public ReactiveEditText(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public ReactiveEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init();
  }

  private void init() {
    addTextChangedListener(new SimpleTextWatcher() {
      @Override
      public void afterTextChanged(Editable s) {
        text.set(s);
      }
    });
  }
}
