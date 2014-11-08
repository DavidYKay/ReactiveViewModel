package com.reactiveviewmodel.demo.viewmodel;

import com.reactiveviewmodel.core.ReactiveProperty;

public class MainViewModel {
  public final ReactiveProperty<String> query = new ReactiveProperty<>();

  public MainViewModel() {
  }
}
