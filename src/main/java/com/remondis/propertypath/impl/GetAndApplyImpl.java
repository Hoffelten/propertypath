package com.remondis.propertypath.impl;

import java.util.Optional;
import java.util.function.Function;

import com.remondis.propertypath.api.Get;
import com.remondis.propertypath.api.GetAndApply;
import com.remondis.propertypath.api.GetWithoutException;

public class GetAndApplyImpl<I, O, T, E extends Exception> implements GetAndApply<I, O, T, E> {

  private Get<I, O, E> getter;
  private Function<O, T> mapping;

  public GetAndApplyImpl(Get<I, O, E> getter, Function<O, T> mapping) {
    this.getter = getter;
    this.mapping = mapping;
  }

  @Override
  public GetWithoutException<I, T> toJdk8() {
    return new GetWithoutExceptionImpl<>(this);
  }

  Get<I, O, E> getGetter() {
    return getter;
  }

  Function<O, T> getMapping() {
    return mapping;
  }

  @Override
  public Optional<T> from(I object) throws E {
    Optional<O> opt = getter.from(object);
    if (opt.isPresent()) {
      return Optional.ofNullable(mapping.apply(opt.get()));
    } else {
      return Optional.empty();
    }
  }

  @Override
  public T fromOrDefault(I object, T defaultValue) throws E {
    Optional<T> opt = from(object);
    if (opt.isPresent()) {
      return opt.get();
    } else {
      return defaultValue;
    }
  }

  @Override
  public <X> GetAndApply<I, T, X, E> andApply(Function<T, X> mapping) {
    return new GetAndApplyImpl<>(this, mapping);
  }

  @Override
  public String toString(boolean detailed) {
    return getter.toString(detailed) + " and applying a mapping function!";
  }

  @Override
  public String toPath() {
    return getter.toPath();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getter == null) ? 0 : getter.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    GetAndApplyImpl other = (GetAndApplyImpl) obj;
    if (getter == null) {
      if (other.getter != null)
        return false;
    } else if (!getter.equals(other.getter))
      return false;
    return true;
  }

}
