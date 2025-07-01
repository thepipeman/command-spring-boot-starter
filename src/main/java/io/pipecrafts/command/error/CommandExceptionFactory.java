package io.pipecrafts.command.error;

import java.util.Optional;

public class CommandExceptionFactory {

  private static final String FALLBACK_MESSAGE = "Command execution failed [name =<%s>].";

  private final Optional<CommandExceptionMessageSupplierPredicate> messageSupplierPredicateOptional;

  public CommandExceptionFactory(Optional<CommandExceptionMessageSupplierPredicate> messageSupplierPredicateOptional) {
    this.messageSupplierPredicateOptional = messageSupplierPredicateOptional;
  }

  public CommandException create(String commandName, Exception e) {
    final var useCauseMessage = useCauseMessage(e);
    final var message = useCauseMessage ? e.getMessage() : String.format(FALLBACK_MESSAGE, commandName);
    return new CommandException(message, e);
  }

  private boolean useCauseMessage(Throwable cause) {
    return messageSupplierPredicateOptional.map(predicate -> predicate.test(cause)).orElse(false);
  }

}
