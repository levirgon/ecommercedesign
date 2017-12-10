package com.tutexpsoft.ecommercedev.event;

/**
 * Created by noushad on 12/10/17.
 */

public class ErrorEvent {

    private String mMessage;

    public ErrorEvent(String message) {

        mMessage = message;
    }

    public String getMessage() {
        return mMessage;
    }
}
