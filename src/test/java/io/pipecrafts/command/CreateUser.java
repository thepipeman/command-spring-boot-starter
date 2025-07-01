package io.pipecrafts.command;

import io.pipecrafts.command.model.Command;
import io.pipecrafts.command.model.CommandInput;

public class CreateUser implements Command<CreateUser.Input, CreateUser.Output> {

  public record Input(String name, String email) implements CommandInput {}

  public record Output(long id) {}


  @Override
  public void validate(Input input) {
    if (input.name().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be empty");
    }
  }

  @Override
  public Output execute(Input input) {

    if (input.email().isEmpty()) {
      throw new IllegalArgumentException("Email cannot be empty");
    }

    return new Output(1L);
  }

}
