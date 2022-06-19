package org.enset.comptecqrses.commonapi.events;

import lombok.Getter;
import org.enset.comptecqrses.commonapi.enums.AccountStatus;

public class AccountActivatedEvent extends BaseEvent<String> {

    @Getter
    private AccountStatus accountStatus;

    public AccountActivatedEvent(String id, AccountStatus accountStatus) {
        super(id);
        this.accountStatus = accountStatus;
    }


}
