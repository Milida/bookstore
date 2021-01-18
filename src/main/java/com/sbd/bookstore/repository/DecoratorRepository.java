package com.sbd.bookstore.repository;

import com.sbd.model.bookDecorators.Decorator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DecoratorRepository extends JpaRepository<Decorator, Long> {

}
