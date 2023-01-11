package uk.com.capgemini.sweettreat.services;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import uk.com.capgemini.sweettreat.models.DeliveryOptions;


public interface SweetTreatsService {

 String getCheapestDelivery(LocalTime deliveryTime, double orderDistance,
     boolean hasRefrigerator);

 DeliveryOptions getDeliveryById(UUID id);
 List<DeliveryOptions> getAllDelivery();

}
