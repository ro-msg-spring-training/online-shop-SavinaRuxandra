package ro.msg.learning.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.ShopApplication;
import ro.msg.learning.shop.configuration.H2TestProfileConfig;
import ro.msg.learning.shop.dto.CustomerDto;
import ro.msg.learning.shop.dto.OrderDto;

import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        ShopApplication.class,
        H2TestProfileConfig.class})
@Transactional
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void createOrder_successfully() throws Exception {

        Integer orderQuantity = 5;
        Map<Integer, Integer> products = Map.of(1,orderQuantity, 2, orderQuantity);
        CustomerDto customerDto = CustomerDto.builder()
                .firstName("Bob")
                .lastName("Johnson")
                .username("some user")
                .emailAddress("bob@pleasework.com")
                .build();

        mvc.perform(MockMvcRequestBuilders
                .post("/api/createOrder")
                .content(asJsonString(OrderDto.builder()
                        .customerDto(customerDto)
                        .products(products)
                        .build())
                )
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void createOrder_fails() throws Exception {

        Integer orderQuantity = 50;
        Map<Integer, Integer> products = Map.of(1,orderQuantity, 2, orderQuantity);

        mvc.perform(MockMvcRequestBuilders
                .post("/api/createOrder")
                .content(asJsonString(OrderDto.builder()
                        .products(products)
                        .build())
                )
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
                //.andExpect(result -> assertTrue(result.getResolvedException() instanceof ServiceException));
                //.andExpect(result -> assertEquals("No location is good enough", result.getResolvedException().getMessage()));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}