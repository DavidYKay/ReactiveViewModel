package com.reactiveviewmodel.core.view;

import android.text.Editable;
import android.widget.EditText;

import com.reactiveviewmodel.core.ReactiveProperty;

public class EditableTextProperty extends ReactiveProperty<CharSequence> {
  public final EditText editText;

  public EditableTextProperty(EditText editText) {
    this.editText = editText;
    init();
  }

  @Override
  public void set(CharSequence value) {
    editText.setText(value);
  }

  @Override
  public CharSequence get() {
    return editText.getText();
  }

  private void init() {
    editText.addTextChangedListener(new SimpleTextWatcher() {
      @Override
      public void afterTextChanged(Editable s) {
        whenAssigned.onNext(s);
      }
    });
  }
}
