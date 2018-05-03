package com.selema.newproject.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by selema on 4/21/18.
 */

public class Login_Response {
    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("received_Transaction_Length")
    @Expose
    private Integer receivedTransactionLength;
    @SerializedName("received_Messages_Length")
    @Expose
    private Integer receivedMessagesLength;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Integer getReceivedTransactionLength() {
        return receivedTransactionLength;
    }

    public void setReceivedTransactionLength(Integer receivedTransactionLength) {
        this.receivedTransactionLength = receivedTransactionLength;
    }

    public Integer getReceivedMessagesLength() {
        return receivedMessagesLength;
    }

    public void setReceivedMessagesLength(Integer receivedMessagesLength) {
        this.receivedMessagesLength = receivedMessagesLength;
    }

}
