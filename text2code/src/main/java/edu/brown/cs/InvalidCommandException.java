package edu.brown.cs;

public class InvalidCommandException extends IllegalArgumentException {

  public InvalidCommandException(String errorMessage) {
    super(errorMessage);
  }

}
