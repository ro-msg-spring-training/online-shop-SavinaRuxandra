package ro.msg.learning.shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.model.Order;
import ro.msg.learning.shop.service.MailHandlerService;
import ro.msg.learning.shop.service.OrderService;

@RequestMapping(value = "/api/createOrder")
@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MailHandlerService mailHandlerService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Object> createOrder(@RequestBody OrderDto orderdto) {
        Order order = orderService.createOrder(orderdto);
        mailHandlerService.sendOrderConfirmationMail(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
