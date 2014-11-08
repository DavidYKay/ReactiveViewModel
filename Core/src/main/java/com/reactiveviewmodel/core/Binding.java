package com.reactiveviewmodel.core;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

public class Binding<S, T> implements Subscription {
  private ReactiveProperty<S> source;
  private ReactiveProperty<T> target;
  private Func1<S, T> sourceToTargetConverter;
  private Func1<T, S> targetToSourceConverter;
  private boolean shouldChangeSource = true;
  private boolean shouldChangeTarget = true;
  private CompositeSubscription subscription;

  public Binding(ReactiveProperty<S> source,
                 ReactiveProperty<T> target,
                 Func1<S, T> sourceToTargetConverter,
                 Func1<T, S> targetToSourceConverter) {
    this.source = source;
    this.target = target;
    this.sourceToTargetConverter = sourceToTargetConverter;
    this.targetToSourceConverter = targetToSourceConverter;

    init();
  }

  public static <T> Subscription twoWayBind(ReactiveProperty<T> source, ReactiveProperty<T> target) {
    SameTypeConverter<T> converter = new SameTypeConverter<>();
    return new Binding<>(source, target, converter, converter);
  }

  public static <T> Subscription oneWayBind(ReactiveProperty<T> source, ReactiveProperty<T> target) {
    target.set(source.get());
    return source.whenChanged().subscribe(target);
  }

  @Override
  public void unsubscribe() {
    subscription.unsubscribe();
  }

  @Override
  public boolean isUnsubscribed() {
    return subscription.isUnsubscribed();
  }

  private void init() {
    target.set(sourceToTargetConverter.call(source.get()));

    subscription = new CompositeSubscription();
    subscription.add(
        source
            .whenChanged()
            .map(sourceToTargetConverter)
            .subscribe(changeTarget())
    );
    subscription.add(
        target
            .whenChanged()
            .map(targetToSourceConverter)
            .subscribe(changeSource())
    );
  }

  private Action1<S> changeSource() {
    return new Action1<S>() {
      @Override
      public void call(S value) {
        if (shouldChangeSource) {
          shouldChangeTarget = false;
          source.set(value);
          shouldChangeTarget = true;
        }
      }
    };
  }

  private Action1<T> changeTarget() {
    return new Action1<T>() {
      @Override
      public void call(T value) {
        if (shouldChangeTarget) {
          shouldChangeSource = false;
          target.set(value);
          shouldChangeSource = true;
        }
      }
    };
  }

  public static class SameTypeConverter<T> implements Func1<T, T> {
    @Override
    public T call(T t) {
      return t;
    }
  }
}
