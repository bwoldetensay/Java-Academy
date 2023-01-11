package uk.com.capgemini.sweettreat.controllers;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.com.capgemini.sweettreat.models.DeliveryOptions;
import uk.com.capgemini.sweettreat.repositories.DeliveryRepository;
import uk.com.capgemini.sweettreat.services.SweetTreatsService;
import uk.com.capgemini.sweettreat.services.SweetTreatsServiceImp;

@RestController
public class DeliveryOptionsApiController {

  @Autowired
  SweetTreatsService deliveryService;
  private DeliveryRepository deliveryRepository;

  @RequestMapping("/delivery/")
  public List<DeliveryOptions> getAllDelivery(){

    return deliveryService.getAllDelivery();
  }
  @RequestMapping("/delivery/{id}")
  public DeliveryOptions findDelivery(@PathVariable final UUID id){
    return deliveryRepository.findById(id).orElseGet(DeliveryOptions::new);
  }



//  public void getCheapestDelivery(@DateTimeFormat(pattern = "HH"
//      + ":mm") LocalTime deliveryTime, Double orderDistance,
//      @RequestParam boolean hasRefrigerator){
//
//     LOGGER.log(Level.INFO, deliveryService.getCheapestDelivery(deliveryTime, orderDistance,
//        hasRefrigerator));
//  }

}
