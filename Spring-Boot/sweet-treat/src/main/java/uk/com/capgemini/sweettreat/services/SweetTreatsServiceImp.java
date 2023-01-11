package uk.com.capgemini.sweettreat.services;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.apache.catalina.Store;
import org.bson.Document;
import org.springframework.stereotype.Service;
import uk.com.capgemini.sweettreat.models.DeliveryOptions;
import uk.com.capgemini.sweettreat.repositories.DeliveryRepository;

@Service
public class SweetTreatsServiceImp implements SweetTreatsService {

  private DeliveryRepository deliveryRepository;

  @Override
  public DeliveryOptions getDeliveryById(UUID id){
    return null;
  }

  @Override
  public List<DeliveryOptions> getAllDelivery() {
    String connectionString = "mongodb+srv://admin:D7ni!_YWMXy@J7R"
        + "@cluster0.madj8mz.mongodb.net/delivery?retryWrites=true&w=majority";
    try (MongoClient mongoClient = MongoClients.create(connectionString)) {
      List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
      MongoDatabase database = mongoClient.getDatabase("sweet_treat");
      MongoCollection<Document> collection = database.getCollection("delivery");
      FindIterable<Document> doc = collection.find();
      return List.of();
    }
  }
  public String getCheapestDelivery(LocalTime deliveryTime, double orderDistance,
      boolean hasRefrigerator) {
//    String connectionString = System.getProperty("mongodb.uri");
    String connectionString = "mongodb+srv://admin:D7ni!_YWMXy@J7R"
        + "@cluster0.madj8mz.mongodb.net/delivery?retryWrites=true&w=majority";
    try (MongoClient mongoClient = MongoClients.create(connectionString)) {
      List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
      MongoDatabase database = mongoClient.getDatabase("sweet_treat");
      MongoCollection<Document> collection = database.getCollection("delivery");
      FindIterable<Document> doc = collection.find();

//    if(orderDistance < 0 || deliveryTime == null){
//      return null;
//    }
//
//    DeliveryOptions cheapDelivery = null;
//    for (Document document : databases){
//      if(has_fridge && !deliveryOptions.isHasRefrigerator()){
//        continue;
//      }
//      if(orderDistance > deliveryOptions.getMaxDeliveryDistance()){
//        continue;
//      }
//      if((deliveryTime.isAfter(deliveryOptions.getStartTime()) || deliveryTime.equals(deliveryOptions.getStartTime())) &&
//          (deliveryTime.isBefore(deliveryOptions.getEndTime())) || deliveryTime.equals(deliveryOptions.getEndTime())){
//        if(cheapDelivery == null || cheapDelivery.totalCostPerMile(orderDistance) > deliveryOptions.totalCostPerMile(orderDistance)){
//          cheapDelivery = deliveryOptions;

    }

    return connectionString;
  }

}
