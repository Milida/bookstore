package com.sbd.bookstore.repository;

import com.sbd.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUserId(@Param("id")Long userId);
    Cart findByUserIdAndBookId(Long userId, Long bookId);

}
