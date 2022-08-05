package com.coffeeshop.rest;

import com.coffeeshop.models.customer.Payment;
import com.coffeeshop.repository.interfaces.IPaymentRepository;
import com.coffeeshop.repository.implementations.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@org.springframework.web.bind.annotation.RestController("restController")
@RequestMapping({"orders/pay"})
public class RestController {

   private final IPaymentRepository payRepository;

   @Autowired
   public RestController(PaymentRepository payRepository){
      this.payRepository = payRepository;
   }

   @RequestMapping(method = RequestMethod.POST)
   public Payment create(@RequestBody Payment payToCreate){
      return payRepository.save(payToCreate);
   }

   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<?> getPays(){
      List<Payment> allPays = payRepository.getAll();
      return new ResponseEntity<>(allPays, HttpStatus.OK);
   }

   @ExceptionHandler({Exception.class})
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public String entityError(Exception ex) {
      return ex.getMessage();
   }
}
