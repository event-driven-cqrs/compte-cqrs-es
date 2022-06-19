package org.enset.comptecqrses.commands.controllers;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.enset.comptecqrses.commonapi.commands.CreateAccountCommand;
import org.enset.comptecqrses.commonapi.commands.CreditAccountCommand;
import org.enset.comptecqrses.commonapi.commands.DebitAccountCommand;
import org.enset.comptecqrses.commonapi.dtos.CreateAccountRequestDto;
import org.enset.comptecqrses.commonapi.dtos.CreditAccountRequestDto;
import org.enset.comptecqrses.commonapi.dtos.DebitAccountRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/commands/account")
public class AccountCommandController {

    private final CommandGateway commandGateway;
    private final EventStore eventStore;

    public AccountCommandController(CommandGateway commandGateway, EventStore eventStore) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
    }


    @PostMapping(path = "/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountRequestDto request) {

        CompletableFuture<String> commandResponse = commandGateway.send(new CreateAccountCommand(UUID.randomUUID().toString(),
                request.getInitialBalance(),
                request.getCurrency()
        ));
        return commandResponse;

    }

    @PutMapping(path = "/credit")
    public CompletableFuture<String> creditAccount(@RequestBody CreditAccountRequestDto request) {

        CompletableFuture<String> commandResponse = commandGateway.send(new CreditAccountCommand(request.getAccountId(),
                request.getAmount(),
                request.getCurrency()
        ));
        return commandResponse;

    }

    @PutMapping(path = "/debit")
    public CompletableFuture<String> debitAccount(@RequestBody DebitAccountRequestDto request) {

        CompletableFuture<String> commandResponse = commandGateway.send(new DebitAccountCommand(request.getAccountId(),
                request.getAmount(),
                request.getCurrency()
        ));
        return commandResponse;

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception ex) {
        ResponseEntity<String> responseEntity = new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;

    }


    @GetMapping(path = "/eventStore/{accountId}")
    public Stream createAccount(@PathVariable String accountId) {


        return eventStore.readEvents(accountId).asStream();

    }


}
