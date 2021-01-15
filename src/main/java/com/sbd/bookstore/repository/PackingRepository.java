package com.sbd.bookstore.repository;

import com.sbd.model.Packing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackingRepository extends JpaRepository<Packing, Long> {
}
