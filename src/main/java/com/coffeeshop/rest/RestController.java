package com.coffeeshop.rest;

import com.coffeeshop.models.customer.Payment;
import com.coffeeshop.repository.interfaces.IPaymentRepository;
import com.coffeeshop.repository.implementations.PaymentRepository;
import com.coffeeshop.service.implementations.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@org.springframework.web.bind.annotation.RestController("restController")
@RequestMapping({"orders/pay"})
public class RestController {

   private final PaymentService paymentService;

   @Autowired
   public RestController(PaymentService paymentService){
      this.paymentService = paymentService;
   }

   @RequestMapping(method = RequestMethod.POST)
   public Payment create(@RequestBody Payment paymentToCreate){
      return paymentService.savePayment(paymentToCreate);
   }

   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<?> getPays(){
      List<Payment> allPayments = paymentService.getAllPayments();
      return new ResponseEntity<>(allPayments, HttpStatus.OK);
   }

   @ExceptionHandler({Exception.class})
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public String entityError(Exception ex) {
      return ex.getMessage();
   }
}
