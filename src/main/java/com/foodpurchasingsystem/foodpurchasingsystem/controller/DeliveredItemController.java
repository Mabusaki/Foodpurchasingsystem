package com.foodpurchasingsystem.foodpurchasingsystem.controller;

import com.foodpurchasingsystem.foodpurchasingsystem.entity.DeliveredItem;
import com.foodpurchasingsystem.foodpurchasingsystem.service.DeliveredItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/delivereditem")
public class DeliveredItemController {
    private final DeliveredItemService deliveredItemService;

    public DeliveredItemController(DeliveredItemService deliveredItemService) {
        this.deliveredItemService = deliveredItemService;
    }

    @GetMapping
    @RolesAllowed({"ROLE_ADMIN", "ROLE_MODERATOR"})
    public ResponseEntity<List<DeliveredItem>> viewAllDeliveredItems(){
        return new ResponseEntity<List<DeliveredItem>>(deliveredItemService.viewAllDeliveredItems(), HttpStatus.OK);
    }
}
