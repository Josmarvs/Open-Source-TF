package com.appreservas.Controller;

import com.appreservas.Entity.Beginner;
import com.appreservas.Service.impl.BeginnerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = BeginnerController.class)
@ActiveProfiles("test")
public class BeginnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BeginnerServiceImpl customerService;

    private List<Beginner> beginnerList;

    @BeforeEach
    void setUp(){
        beginnerList = new ArrayList<>();
        beginnerList.add(new Beginner(1L, "Juan", "Perez", "12345678",
  "Av. No se 123", "987654321", "juan.perez@upc.edu.pe"));
        beginnerList.add(new Beginner(2L, "Juan", "Perez", "12345678",
                "Av. No se 123", "987654321", "juan.perez@upc.edu.pe"));
        beginnerList.add(new Beginner(3L, "Juan", "Perez", "12345678",
                "Av. No se 123", "987654321", "juan.perez@upc.edu.pe"));
        beginnerList.add(new Beginner(4L, "Juan", "Perez", "12345678",
                "Av. No se 123", "987654321", "juan.perez@upc.edu.pe"));
    }

    @Test
    void findAllCustomers() throws Exception {
        given(customerService.getAll()).willReturn(beginnerList);
        mockMvc.perform(get("/api/customers")).andExpect(status().isOk());
    }

    @Test
    void findCustomerById() throws Exception {
        Long CustomerId = 1L;
        Beginner beginner = new Beginner(1L, "Juan", "Perez", "12345678",
                "Av. No se 123", "987654321", "juan.perez@upc.edu.pe");

        given(customerService.getById(CustomerId)).willReturn(Optional.of(beginner));

        mockMvc.perform(get("/api/customers/{id}", beginner.getId()))
                .andExpect(status().isOk());

    }

}
