package com.sbd.model.embedded;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class CartId implements Serializable{
   private Long userId;
   private Long bookId;
   
   private CartId() {}
   public Long getUserId() {
       return userId;
   }
   public Long getBookId() {
       return bookId;
   }

   @Override
   public boolean equals(Object o) {
       if (this == o) return true;

       if (o == null || getClass() != o.getClass())
           return false;

       CartId that = (CartId) o;
       return Objects.equals(userId, that.userId) &&
              Objects.equals(bookId, that.bookId);
   }

   @Override
   public int hashCode() {
       return Objects.hash(userId, bookId);
   }
}
