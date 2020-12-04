package com.sbd.model.embedded;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
@SuppressWarnings("serial")
public class OrderBookId implements Serializable {

    private Long orderId;
    private Long bookId;

    public OrderBookId() {
    }

    public Long getBookId() {
        return bookId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setBookId(Long id) {this.bookId = id;}

    public void setOrderId(Long id) {this.orderId = id;}

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        OrderBookId that = (OrderBookId) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, bookId);
    }
}
