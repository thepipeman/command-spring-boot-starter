package io.pipecrafts.command.executor;

import io.pipecrafts.command.model.Command;
import io.pipecrafts.command.model.CommandInput;

public class StdCommandExecutor implements CommandExecutor {


  @Override
  public <T extends CommandInput, R> R execute(Command<T, R> command, T input) {
    // add persistence

    // add error handling (standard command issue?)
    command.validate(input);

    // add validation and error handling
    return command.execute(input);
  }
}
