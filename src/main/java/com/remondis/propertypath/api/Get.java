package com.remondis.propertypath.api;

import java.util.Optional;

public interface Get<I, O, E extends Exception> {

  public Optional<O> from(I object) throws E;

}
