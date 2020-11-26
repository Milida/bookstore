package com.sbd.controller;

import com.sbd.bookstore.repository.ShipmentRepository;
import com.sbd.model.Shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping()
public class ShipmentController {
    @Autowired
    private ShipmentRepository shipmentRepository;

    @RequestMapping(path="/shipment/add/{name}/{price}")
    public @ResponseBody ResponseEntity<List<Shipment>>addNewAuthor (@PathVariable String name,
                                                                     @PathVariable BigDecimal price) {
        Shipment n = new Shipment();
        n.setName(name);
        n.setPrice(price);
        shipmentRepository.save(n);
        return new ResponseEntity<>(shipmentRepository.findAll(), HttpStatus.CREATED);
    }

    @GetMapping(path="/shipments")
    public @ResponseBody
    ResponseEntity<List<Shipment>> getAllAuthors() {
        return new ResponseEntity<>(shipmentRepository.findAll(), HttpStatus.OK);
    }
}