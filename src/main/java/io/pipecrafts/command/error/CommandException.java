package io.pipecrafts.command.error;

public class CommandException extends RuntimeException {

  public CommandException(String message, Exception cause) {
    super(message, cause);
  }

}
