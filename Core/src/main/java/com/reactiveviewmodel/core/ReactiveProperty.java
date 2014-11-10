package com.reactiveviewmodel.core;

import rx.Observable;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

public class ReactiveProperty<T> implements Action1<T>, ObservableProperty<T> {
  protected final PublishSubject<T> whenAssigned = PublishSubject.create();
  private T value;

  public ReactiveProperty() { }

  public ReactiveProperty(T defaultValue) {
    this.value = defaultValue;
  }

  @Override
  public T get() {
    return value;
  }

  @Override
  public void set(T value) {
    this.value = value;
    whenAssigned.onNext(value);
  }

  @Override
  public Observable<T> whenChanged() {
    // FIXME: distinctUntilChanged() doesn't work as expected.
    return whenAssigned;
  }

  @Override
  public void call(T value) {
    set(value);
  }
}
