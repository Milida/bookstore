package com.sbd.controller;

import com.sbd.bookstore.repository.RoleRepository;
import com.sbd.bookstore.repository.ShipmentRepository;
import com.sbd.model.Role;
import com.sbd.model.Shipment;
import com.sbd.model.User;
import com.sbd.payroll.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/{id}")
    ResponseEntity<Role> getRole(@PathVariable Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Role with ID = %d was not found", id)));
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}