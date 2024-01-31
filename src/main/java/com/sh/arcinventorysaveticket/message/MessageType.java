package com.sh.arcinventorysaveticket.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageType {

    NORMAL("normal"),
    ERROR("error"),
    MAIN("main");

    private final String type;
}
