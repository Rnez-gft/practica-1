package com.practica.practica.service;

import com.practica.practica.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderService {

    private final List<Order> orders = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    public Optional<Order> getOrderById(Long id) {
        return orders.stream().filter(order -> order.getId().equals(id)).findFirst();
    }

    public Order createOrder(Order order) {
        order.setId(idGenerator.getAndIncrement());
        orders.add(order);
        return order;
    }

    public Optional<Order> updateOrder(Long id, Order updatedOrder) {
        Optional<Order> existing = getOrderById(id);
        if (existing.isPresent()) {
            Order order = existing.get();
            order.setCustomerName(updatedOrder.getCustomerName());
            order.setDescription(updatedOrder.getDescription());
            order.setTotal(updatedOrder.getTotal());
            return Optional.of(order);
        }
        return Optional.empty();
    }

    public boolean deleteOrder(Long id) {
        return orders.removeIf(order -> order.getId().equals(id));
    }
}
