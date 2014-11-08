package com.thuytrinh.reactiveviewmodel;

import junit.framework.TestCase;

public class BindingTest extends TestCase {
  public void testTwoWayBinding() {
    ReactiveProperty<String> name = ReactiveProperty.create("A");
    ReactiveProperty<String> text = ReactiveProperty.create();

    Binding.twoWayBind(name, text);
    assertEquals("A", text.get());

    name.set("B");
    assertEquals("B", text.get());

    text.set("C");
    assertEquals("C", name.get());
  }

  public void testOneWayBinding() {
    ReactiveProperty<String> name = ReactiveProperty.create("A");
    ReactiveProperty<String> text = ReactiveProperty.create();

    Binding.oneWayBind(name, text);
    assertEquals("A", text.get());

    name.set("B");
    assertEquals("B", text.get());

    name.set("C");
    assertEquals("C", text.get());
  }
}
