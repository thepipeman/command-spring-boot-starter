package io.pipecrafts.command.executor;

import io.pipecrafts.command.model.Command;
import io.pipecrafts.command.model.CommandInput;

public interface CommandExecutor {

  <T extends CommandInput, R> R execute(Command<T, R> command, T input);
}
