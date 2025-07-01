package io.pipecrafts.command;

import io.pipecrafts.command.config.CommandConfiguration;
import io.pipecrafts.command.error.CommandException;
import io.pipecrafts.command.executor.CommandExecutor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateUserCommandExecutionTest {

  private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
    .withConfiguration(AutoConfigurations.of(CommandConfiguration.class));

  @Test
  void shouldReturnSuccessfulOutput() {
    contextRunner.run(context -> {
      CommandExecutor executor = context.getBean(CommandExecutor.class);
      var output = executor.execute(new CreateUser(), new CreateUser.Input("John", "<EMAIL>"));
      assert output.id() == 1L;
    });
  }

  @Test
  void shouldNotWrapExceptionsOnValidate() {
    contextRunner.run(context -> {
      CommandExecutor executor = context.getBean(CommandExecutor.class);
      assertThrows(
        IllegalArgumentException.class, () ->
          executor.execute(new CreateUser(), new CreateUser.Input("", "<EMAIL>"))
      );
    });
  }

  @Test
  void shouldWrapExceptionsOnExecute() {
    contextRunner.run(context -> {
      CommandExecutor executor = context.getBean(CommandExecutor.class);
      assertThrows(
        CommandException.class, () ->
          executor.execute(new CreateUser(), new CreateUser.Input("John", ""))
      );
    });
  }
}
