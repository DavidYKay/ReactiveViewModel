package com.reactiveviewmodel.core;

import rx.Observable;

public interface ObservableList {
  Observable<Integer> whenSizeChanged();
}
