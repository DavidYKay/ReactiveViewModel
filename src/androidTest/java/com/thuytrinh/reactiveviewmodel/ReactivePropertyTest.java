package com.thuytrinh.reactiveviewmodel;

import junit.framework.TestCase;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

public class ReactivePropertyTest extends TestCase {
  public void testSomething() {
    ReactiveProperty<String> name = ReactiveProperty.create("A");
    assertEquals("A", name.get());

    name.whenChanged().subscribe(new Action1<String>() {
      @Override
      public void call(String value) {
        assertEquals("B", value);
      }
    });
    name.set("B");
    assertEquals("B", name.get());
  }

  public void testBehaviorSubject() {
    BehaviorSubject<String> subject = BehaviorSubject.create();

    final AtomicInteger counter = new AtomicInteger();
    subject.subscribe(new Action1<String>() {
      @Override
      public void call(String s) {
        counter.incrementAndGet();
      }
    });
    assertEquals(0, counter.get());
  }

  public void testBehaviorSubject2() {
    BehaviorSubject<String> subject = BehaviorSubject.create("A");

    final AtomicReference<String> valueHolder = new AtomicReference<>();
    subject.subscribe(new Action1<String>() {
      @Override
      public void call(String s) {
        valueHolder.set(s);
      }
    });
    assertEquals("A", valueHolder.get());
  }

  public void testBehaviorSubject3() {
    BehaviorSubject<String> subject = BehaviorSubject.create();
    subject.onNext("A");

    final AtomicReference<String> valueHolder = new AtomicReference<>();
    subject.subscribe(new Action1<String>() {
      @Override
      public void call(String s) {
        valueHolder.set(s);
      }
    });
    assertEquals("A", valueHolder.get());
  }
}
