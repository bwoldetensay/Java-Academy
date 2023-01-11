package uk.com.capgemini.sweettreat.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;
import java.util.UUID;
import lombok.Builder;
import org.apache.catalina.Session;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
@Builder
@Document("delivery")
public class DeliveryOptions {
  @Id
  private UUID id;

  private String name;
  @JsonFormat(pattern = "HH:mm")
  @DateTimeFormat(pattern = "HH:mm")
  private LocalTime startTime;

  @JsonFormat(pattern = "HH:mm")
  @DateTimeFormat(pattern = "HH:mm")
  private LocalTime endTime;
  private double maxDeliveryDistance;
  private boolean hasRefrigerator;
  private double pricePerMile;

  public DeliveryOptions() {
  }

  public DeliveryOptions( UUID id, String name, LocalTime startTime, LocalTime endTime,
      double maxDeliveryDistance, boolean hasRefrigerator, double pricePerMile) {
    this.id = id;
    this.name = name;
    this.startTime = startTime;
    this.endTime = endTime;
    this.maxDeliveryDistance = maxDeliveryDistance;
    this.hasRefrigerator = hasRefrigerator;
    this.pricePerMile = pricePerMile;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalTime startTime) {
    this.startTime = startTime;
  }

  public LocalTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalTime endTime) {
    this.endTime = endTime;
  }

  public double getMaxDeliveryDistance() {
    return maxDeliveryDistance;
  }

  public void setMaxDeliveryDistance(double maxDeliveryDistance) {
    this.maxDeliveryDistance = maxDeliveryDistance;
  }

  public boolean isHasRefrigerator() {
    return hasRefrigerator;
  }

  public void setHasRefrigerator(boolean hasRefrigerator) {
    this.hasRefrigerator = hasRefrigerator;
  }

  public double getPricePerMile() {
    return pricePerMile;
  }

  public void setPricePerMile(double pricePerMile) {
    this.pricePerMile = pricePerMile;
  }

  public double totalCostPerMile(double distance){return distance * this.getPricePerMile();}
}