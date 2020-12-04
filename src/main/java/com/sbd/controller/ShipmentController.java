package com.sbd.controller;

import com.sbd.bookstore.repository.ShipmentRepository;
import com.sbd.model.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/shipments")
public class ShipmentController {
    @Autowired
    private ShipmentRepository shipmentRepository;

    @GetMapping
    ResponseEntity<List<Shipment>> getShipments() {
        return new ResponseEntity<>(shipmentRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Shipment> addShipment(@RequestBody Shipment shipment) {
        return new ResponseEntity<>(shipmentRepository.save(shipment), HttpStatus.CREATED);
    }
}