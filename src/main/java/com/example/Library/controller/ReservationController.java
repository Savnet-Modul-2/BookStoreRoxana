package com.example.Library.controller;

import com.example.Library.dto.PaginatedReservationPeriodDto;
import com.example.Library.dto.PaginatedReservationStatusDto;
import com.example.Library.dto.ReservationDto;
import com.example.Library.dto.ReservationStatusDto;
import com.example.Library.dto.validation.ValidationOrder;
import com.example.Library.entities.Reservation;
import com.example.Library.mapper.ReservationMapper;
import com.example.Library.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/{userId}/{bookId}")
    public ResponseEntity<?> create(@PathVariable Long userId,
                                    @PathVariable Long bookId,
                                    @Validated(ValidationOrder.class)
                                    @RequestBody ReservationDto reservationDTO) {
        Reservation reservationToCreate = ReservationMapper.toEntity(reservationDTO);
        Reservation createdReservation = reservationService.create(reservationToCreate, userId, bookId);
        return ResponseEntity.ok(ReservationMapper.toDto(createdReservation));
    }

    @PostMapping("/reservationsByPeriod")
    public ResponseEntity<?> getReservationsByPeriod(@RequestBody PaginatedReservationPeriodDto reservation) {
        List<Reservation> reservationPageable = reservationService.getReservationsByPeriod(reservation);
        return ResponseEntity.ok(ReservationMapper.toDtoList(reservationPageable));
    }

    @PostMapping("/reservationsByStatus")
    public ResponseEntity<?> getReservationsByStatus(@RequestBody PaginatedReservationStatusDto reservation) {
        List<Reservation> reservationPageable = reservationService.getReservationsByStatus(reservation);
        return ResponseEntity.ok(ReservationMapper.toDtoList(reservationPageable));
    }

    @PutMapping("/{reservationId}/status/{librarianId}")
    public ResponseEntity<?> updateReservationStatus(@PathVariable Long reservationId,
                                                     @PathVariable Long librarianId,
                                                     @RequestBody ReservationStatusDto reservationStatusDto) {
        Reservation reservationUpdated = reservationService.updateReservationStatus(reservationId, librarianId, reservationStatusDto.getStatusReservation());
        return ResponseEntity.ok(ReservationMapper.toDto(reservationUpdated));
    }
}