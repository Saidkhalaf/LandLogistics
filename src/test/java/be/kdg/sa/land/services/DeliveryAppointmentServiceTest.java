package be.kdg.sa.land.services;

import be.kdg.sa.land.domain.DeliveryAppointment;
import be.kdg.sa.land.domain.Truck;
import be.kdg.sa.land.repositories.DeliveryAppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DeliveryAppointmentServiceTest {

    @MockBean
    private DeliveryAppointmentRepository deliveryAppointmentRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void fetchingAnExistingDeliveryAppointmentShouldReturnOk() throws Exception {
        // Arrange
        Truck truck = new Truck("ABC999", null, null, null);
        DeliveryAppointment appointment = new DeliveryAppointment(
                LocalDateTime.of(2024, 10, 7, 12, 30, 0), truck);
        when(deliveryAppointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));

        // Act & Assert
        mockMvc.perform(get("/api/appointments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.scheduledArrivalTime").value("2024-10-07T12:30:00"))
                .andExpect(jsonPath("$.truckId").value(truck.getId()));

        verify(deliveryAppointmentRepository, times(1)).findById(1L);
    }

    @Test
    void fetchingNonExistingDeliveryAppointmentShouldReturnNotFound() throws Exception {
        // Arrange
        when(deliveryAppointmentRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/api/appointments/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(deliveryAppointmentRepository, times(1)).findById(99L);
    }
}

