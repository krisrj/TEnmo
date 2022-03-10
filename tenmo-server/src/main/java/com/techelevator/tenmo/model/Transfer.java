package com.techelevator.tenmo.model;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Transfer {

    private Long senderId;
    private Long receiverId;
    @NotNull
    private BigDecimal amount;

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
