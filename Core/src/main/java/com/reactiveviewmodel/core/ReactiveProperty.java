package com.reactiveviewmodel.core;

import rx.Observable;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

public class ReactiveProperty<T> implements Action1<T> {
  private T value;
  private PublishSubject<T> whenAssigned = PublishSubject.create();

  public ReactiveProperty() { }

  public ReactiveProperty(T defaultValue) {
    this.value = defaultValue;
  }

  public T get() {
    return value;
  }

  public void set(T value) {
    this.value = value;
    whenAssigned.onNext(value);
  }

  public Observable<T> whenChanged() {
    // FIXME: distinctUntilChanged() doesn't work as expected.
    return whenAssigned;
  }

  @Override
  public void call(T value) {
    set(value);
  }
}
