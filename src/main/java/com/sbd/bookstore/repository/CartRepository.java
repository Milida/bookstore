package com.sbd.bookstore.repository;

import com.sbd.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    //@EntityGraph(value = "User.Cart", type = EntityGraph.EntityGraphType.FETCH)
    Optional<List<Cart>> findByUserId(@Param("id")Long userId);
}
