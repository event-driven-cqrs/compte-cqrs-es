package org.enset.comptecqrses.query.controllers;

import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.enset.comptecqrses.commonapi.queries.GetAccountByIdQuery;
import org.enset.comptecqrses.commonapi.queries.GetAllAccountsQuery;
import org.enset.comptecqrses.query.entities.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/query/accounts")
public class AccountQueryController {

    private final QueryGateway queryGateway;

    public AccountQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/allAccounts")
    public List<Account> accountList()
    {
        List<Account> accounts = queryGateway.query(new GetAllAccountsQuery(), ResponseTypes.multipleInstancesOf(Account.class)).join();
        return accounts;
    }


    @GetMapping("/{id}")
    public Account getAccount(@PathVariable String id)
    {
         return queryGateway.query(new GetAccountByIdQuery(id), ResponseTypes.instanceOf(Account.class)).join();
    }
}
