package org.raistlic.common.postcondition;

/**
 * The exception thrown when the post condition fails validation. Post condition checks can be used,
 * for example, unit tests.
 */
public class PostconditionException extends RuntimeException {

  public PostconditionException(String message) {

    super(message);
  }

  public PostconditionException(String message, Throwable cause) {

    super(message, cause);
  }

  public PostconditionException(Throwable cause) {

    super(cause);
  }
}
