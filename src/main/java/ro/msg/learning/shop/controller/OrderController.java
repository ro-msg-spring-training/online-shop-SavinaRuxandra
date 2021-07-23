package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.service.OrderService;

@RequestMapping(value = "/api/createOrder")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Object> createOrder(@RequestBody OrderDto orderdto) {
        Order order = orderService.createOrder(orderdto);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
