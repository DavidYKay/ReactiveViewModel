package com.reactiveviewmodel.core;

import rx.Observable;

public interface ObservableProperty<T> {
  T get();
  void set(T value);
  Observable<T> whenChanged();
}
