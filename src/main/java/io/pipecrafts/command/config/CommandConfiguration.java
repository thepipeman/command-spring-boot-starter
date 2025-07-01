package io.pipecrafts.command.config;

import io.pipecrafts.command.error.CommandExceptionFactory;
import io.pipecrafts.command.error.CommandExceptionMessageSupplierPredicate;
import io.pipecrafts.command.executor.CommandExecutor;
import io.pipecrafts.command.executor.StdCommandExecutor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@AutoConfiguration
public class CommandConfiguration {

  @Bean
  public CommandExceptionFactory commandExceptionFactory(Optional<CommandExceptionMessageSupplierPredicate> messageSupplierPredicateOptional) {
    return new CommandExceptionFactory(messageSupplierPredicateOptional);
  }

  @Bean
  public CommandExecutor commandExecutor(CommandExceptionFactory commandExceptionFactory) {
    return new StdCommandExecutor(commandExceptionFactory);
  }

}
