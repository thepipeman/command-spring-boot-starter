package io.pipecrafts.command.executor;

import io.pipecrafts.command.error.CommandExceptionFactory;
import io.pipecrafts.command.model.Command;
import io.pipecrafts.command.model.CommandInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StdCommandExecutor implements CommandExecutor {

  private static final Logger log = LoggerFactory.getLogger(StdCommandExecutor.class);

  private final CommandExceptionFactory commandExceptionFactory;

  public StdCommandExecutor(CommandExceptionFactory commandExceptionFactory) {
    this.commandExceptionFactory = commandExceptionFactory;
  }

  @Override
  public <T extends CommandInput, R> R execute(Command<T, R> command, T input) {
    // TODO: add persistence

    // no exception wrapping, error must remain exactly the same as what is thrown in validate
    command.validate(input);

    try {
      return command.execute(input);
      // TODO: add command update (success)
      // optionally, use event - maybe?
    } catch (Exception e) {
      // TODO add command update (error)
      log.error("Command execution failed [name = " + command.getClass().getSimpleName() + "]", e);
      throw commandExceptionFactory.create(command.getClass().getSimpleName(), e);
    }
  }
}
