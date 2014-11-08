package com.reactiveviewmodel.core;

import junit.framework.TestCase;

import static com.reactiveviewmodel.core.Binding.oneWayBind;
import static com.reactiveviewmodel.core.Binding.twoWayBind;

public class BindingTest extends TestCase {
  public void testTwoWayBinding() {
    ReactiveProperty<String> name = new ReactiveProperty<>("A");
    ReactiveProperty<String> text = new ReactiveProperty<>();

    twoWayBind(name, text);
    assertEquals("A", text.get());

    name.set("B");
    assertEquals("B", text.get());

    text.set("C");
    assertEquals("C", name.get());
  }

  public void testOneWayBinding() {
    ReactiveProperty<String> name = new ReactiveProperty<>("A");
    ReactiveProperty<String> text = new ReactiveProperty<>();

    oneWayBind(name, text);
    assertEquals("A", text.get());

    name.set("B");
    assertEquals("B", text.get());

    name.set("C");
    assertEquals("C", text.get());
  }
}
