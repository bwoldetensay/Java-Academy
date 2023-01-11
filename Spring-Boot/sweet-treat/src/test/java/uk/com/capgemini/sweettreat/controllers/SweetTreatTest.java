package uk.com.capgemini.sweettreat.controllers;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import uk.com.capgemini.sweettreat.SweetTreatApplication;
import uk.com.capgemini.sweettreat.models.DeliveryOptions;
import uk.com.capgemini.sweettreat.services.SweetTreatsService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SweetTreatApplication.class)
@WebAppConfiguration
public class SweetTreatTest {

  @Autowired
  private WebApplicationContext webApplicationContext;
  private MockMvc mockMvc;
  @MockBean
  private SweetTreatsService mockSweetTreatsService;
  private static final UUID TEST_DELIVERY_ID = UUID.randomUUID();

  // to mock http request and send it to Mvc controller
  @Nested
  @DisplayName("Getting Delivery by Id")
  class GetDeliveryById {

    @Nested
    @DisplayName("If delivery with Id exists")
    class DeliveryExists {

      private static final String TEST_URL = "/delivery/" + TEST_DELIVERY_ID;

      @BeforeEach
      public void setup() {
        when(mockSweetTreatsService.getDeliveryById(TEST_DELIVERY_ID)).thenReturn(
            DeliveryOptions.builder()
                .id(TEST_DELIVERY_ID)
                .name("Bobby")
                .startTime(LocalTime.of(9, 00))
                .endTime(LocalTime.of(13, 00))
                .maxDeliveryDistance(5.0)
                .hasRefrigerator(true)
                .pricePerMile(1.75)
                .build());
      }

      @Test
      @DisplayName("Delivery returned")
      public void returnDeliveryIfExists() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(TEST_URL)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is("Bobby")))
            .andExpect(jsonPath("$.startTime", is("09:00")))
            .andExpect(jsonPath("$.endTime", is("13:00")))
            .andExpect(jsonPath("$.hasRefrigerator", is(true)))
            .andExpect(jsonPath("$.maxDeliveryDistance", is(5.0)))
            .andExpect(jsonPath("$.pricePerMile", is(1.75)))
            .andExpect(jsonPath("$.id", is(TEST_DELIVERY_ID.toString())));

      }

    }

    @Nested
    @DisplayName("When an employee by the given ID does not exist")
    class DeliveryDoesNotExist {

      private static final String TEST_URL = "/delivery/" + UUID.randomUUID();

      @BeforeEach
      public void setUp() {

        when(mockSweetTreatsService.getDeliveryById(any())).thenThrow(new NotFoundException());
      }

      @Test
      @DisplayName("Then a 404 error is returned")
      public void notFoundErrorReturnedIfNotExists() throws Exception {

        // given, when
        mockMvc.perform(MockMvcRequestBuilders.get(TEST_URL)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message", is("Delivery with the requested ID was not found.")));
      }

    }

    @Nested
    @DisplayName("Getting all Delivery")
    class GetAllDelivery {

      private static final String TEST_URL = "/delivery/";


      @Nested
      @DisplayName("If delivery exists")
      class DeliveryExists {

        private static final UUID TEST_DELIVERY_ID_BOBBY = UUID.randomUUID();


        @BeforeEach
        public void setup() {

          DeliveryOptions deliveryOptions1 = DeliveryOptions.builder()
              .id(TEST_DELIVERY_ID)
              .name("Bobby")
              .startTime(LocalTime.of(9, 00))
              .endTime(LocalTime.of(13, 00))
              .maxDeliveryDistance(5.0)
              .hasRefrigerator(true)
              .pricePerMile(1.75)
              .build();

          DeliveryOptions deliveryOptions2 = DeliveryOptions.builder()
              .id(TEST_DELIVERY_ID)
              .name("Martin")
              .startTime(LocalTime.of(10, 00))
              .endTime(LocalTime.of(17, 00))
              .maxDeliveryDistance(3.0)
              .hasRefrigerator(false)
              .pricePerMile(1.50)
              .build();

          when(mockSweetTreatsService.getAllDelivery()).thenReturn(List.of(deliveryOptions1,
              deliveryOptions2));
        }

        @Test
        @DisplayName("Then a list of employees is returned")
        public void deliveryListIsReturned() throws Exception {

          // given, when
          mockMvc.perform(MockMvcRequestBuilders.get(TEST_URL)
                  .contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$[*].name", containsInAnyOrder("Bobby", "Martin")))
              .andExpect(jsonPath("$[*].startTime", containsInAnyOrder("09:00", "09:00")))
              .andExpect(jsonPath("$[*].endTime", containsInAnyOrder("13:00", "17:00")))
              .andExpect(jsonPath("$[*].hasRefrigerator", containsInAnyOrder(true, false)))
              .andExpect(jsonPath("$[*].maxDeliveryDistance", containsInAnyOrder(5.0, 3.0)))
              .andExpect(jsonPath("$[*].pricePerMile", containsInAnyOrder(1.75, 1.50)))
              .andExpect(jsonPath("$[*].id", containsInAnyOrder(TEST_DELIVERY_ID.toString(),
                  TEST_DELIVERY_ID_BOBBY.toString())));

        }
      }

      @Nested
      @DisplayName("When there are no delivery")
      class NoDelivery {

        @BeforeEach
        public void setUp() {

          when(mockSweetTreatsService.getAllDelivery()).thenReturn(List.of());
        }

        @Test
        @DisplayName("Then an empty list is returned")
        public void emptyListIsReturned() throws Exception {

          mockMvc.perform(MockMvcRequestBuilders.get(TEST_URL)
                  .contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk())
              .andExpect(content().string("[]"));
        }
      }
    }
  }
}


