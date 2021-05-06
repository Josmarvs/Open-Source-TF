package com.appreservas.Service;

import com.appreservas.Entity.Beginner;
import com.appreservas.Repository.IBeginnerRepository;
import com.appreservas.Service.impl.BeginnerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BeginnerServiceImplTest {

    @Mock
    private IBeginnerRepository customerRepository;
    @InjectMocks
    private BeginnerServiceImpl customerService;

    @Test
    public void saveTest() {
        Beginner beginner = new Beginner(1L, "Juan", "Perez", "12345678", "Av. No se 123", "987654321", "juan.perez@upc.edu.pe");

        given(customerRepository.save(beginner)).willReturn(beginner);

        Beginner savedBeginner = null;

        try {
            savedBeginner = customerService.save(beginner);
        } catch (Exception e) {
        }

        assertThat(savedBeginner).isNotNull();
        verify(customerRepository).save(any(Beginner.class));
    }

    @Test
    void findByIdTest() throws Exception {
        Long id=1L;
        Beginner beginner = new Beginner(1L, "Juan", "Perez", "12345678", "Av. No se 123", "987654321", "juan.perez@upc.edu.pe");

        given(customerRepository.findById(id)).willReturn(Optional.of(beginner));

        Optional<Beginner> expected=customerService.getById(id);
        assertThat(expected).isNotNull();

    }

    @Test
    void finAllTest() throws Exception {
        List<Beginner>list=new ArrayList<>();
        list.add(new Beginner(1L, "Juan", "Perez", "12345678", "Av. No se 123", "987654321", "juan.perez@upc.edu.pe"));
        list.add(new Beginner(2L, "Juan", "Perez", "12345678", "Av. No se 123", "987654321", "juan.perez@upc.edu.pe"));
        list.add(new Beginner(3L, "Juan", "Perez", "12345678", "Av. No se 123", "987654321", "juan.perez@upc.edu.pe"));
        list.add(new Beginner(4L, "Juan", "Perez", "12345678", "Av. No se 123", "987654321", "juan.perez@upc.edu.pe"));

        given(customerRepository.findAll()).willReturn(list);
        List<Beginner> expected= customerService.getAll();
        assertEquals(expected,list);

    }

    @Test
    void deleteTest() throws Exception {
        Long id=1L;
        customerService.delete(id);
        verify(customerRepository,times(1)).deleteById(id);

    }

}
