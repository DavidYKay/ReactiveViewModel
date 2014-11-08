package com.thuytrinh.reactiveviewmodel;

import rx.Observable;

public interface ObservableList {
  Observable<Integer> whenSizeChanged();
}
