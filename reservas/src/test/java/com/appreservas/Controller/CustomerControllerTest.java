package com.appreservas.Controller;

import com.appreservas.Entity.Customer;
import com.appreservas.Service.impl.CustomerServiceImpl;
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


@WebMvcTest(controllers = CustomerController.class)
@ActiveProfiles("test")
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerServiceImpl customerService;

    private List<Customer> customerList;

    @BeforeEach
    void setUp(){
        customerList = new ArrayList<>();
        customerList.add(new Customer(1L, "Juan", "Perez", "12345678",
                "Av. No se 123", "987654321", "juan.perez@upc.edu.pe"));
        customerList.add(new Customer(2L, "Juan", "Perez", "12345678",
                "Av. No se 123", "987654321", "juan.perez@upc.edu.pe"));
        customerList.add(new Customer(3L, "Juan", "Perez", "12345678",
                "Av. No se 123", "987654321", "juan.perez@upc.edu.pe"));
        customerList.add(new Customer(4L, "Juan", "Perez", "12345678",
                "Av. No se 123", "987654321", "juan.perez@upc.edu.pe"));
    }

    @Test
    void findAllCustomers() throws Exception {
        given(customerService.getAll()).willReturn(customerList);
        mockMvc.perform(get("/api/customers")).andExpect(status().isOk());
    }

    @Test
    void findCustomerById() throws Exception {
        Long CustomerId = 1L;
        Customer customer= new Customer(1L, "Juan", "Perez", "12345678",
                "Av. No se 123", "987654321", "juan.perez@upc.edu.pe");

        given(customerService.getById(CustomerId)).willReturn(Optional.of(customer));

        mockMvc.perform(get("/api/customers/{id}",customer.getId()))
                .andExpect(status().isOk());

    }

}
