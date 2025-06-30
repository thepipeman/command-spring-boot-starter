package io.pipecrafts.command.model;

public interface Command<T extends CommandInput, R> {

  void validate(T input);

  R execute(T input);
}
