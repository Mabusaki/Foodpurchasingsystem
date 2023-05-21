package com.foodpurchasingsystem.foodpurchasingsystem.implementation;

import com.foodpurchasingsystem.foodpurchasingsystem.repository.DeliveredItemRepo;
import com.foodpurchasingsystem.foodpurchasingsystem.entity.DeliveredItem;
import com.foodpurchasingsystem.foodpurchasingsystem.service.DeliveredItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveredItemImpl implements DeliveredItemService {
    private final DeliveredItemRepo deliveredItemRepo;

    public DeliveredItemImpl(DeliveredItemRepo deliveredItemRepo) {
        this.deliveredItemRepo = deliveredItemRepo;
    }

    @Override
    public List<DeliveredItem> viewAllDeliveredItems() {
        List<DeliveredItem> deliveredItems = deliveredItemRepo.findAll();
        return deliveredItems;
    }
}
