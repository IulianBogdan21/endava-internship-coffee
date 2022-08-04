package com.coffeeshop.rest.restService;

import com.coffeeshop.domain.Pay;
import com.coffeeshop.repository.IPayRepository;
import com.coffeeshop.repository.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@org.springframework.web.bind.annotation.RestController
@RequestMapping({"orders/pay"})
public class RestController {

   private final IPayRepository payRepository;

   @Autowired
   public RestController(PayRepository payRepository){
      this.payRepository = payRepository;
   }

   @RequestMapping(method = RequestMethod.POST)
   public Pay create(@RequestBody Pay payToCreate){
      return payRepository.save(payToCreate);
   }

   @RequestMapping(method = RequestMethod.GET)
   public ResponseEntity<?> getPays(){
      List<Pay> allPays = payRepository.getAll();
      return new ResponseEntity<List<Pay>>(allPays, HttpStatus.OK);
   }

   @ExceptionHandler({Exception.class})
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public String entityError(Exception ex) {
      return ex.getMessage();
   }
}
