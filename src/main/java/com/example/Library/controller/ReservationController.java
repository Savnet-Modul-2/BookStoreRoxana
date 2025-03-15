package com.example.Library.controller;

import com.example.Library.dto.*;
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
                                    @RequestBody @Validated(ValidationOrder.class) ReservationDto reservationDTO) {
        Reservation reservationToCreate = ReservationMapper.toEntity(reservationDTO);
        Reservation createdReservation = reservationService.create(reservationToCreate, userId, bookId);
        return ResponseEntity.ok(ReservationMapper.toDto(createdReservation));
    }

    @PostMapping("/reservationsByPeriod/{libraryId}")
    public ResponseEntity<?> getReservationsByPeriod(@PathVariable Long libraryId,
                                                     @RequestBody ReservationSearchDto reservation) {
        List<Reservation> reservationPageable = reservationService.getReservationsByPeriod(reservation, libraryId);
        return ResponseEntity.ok(ReservationMapper.toDtoList(reservationPageable));
    }

    @PostMapping("/reservationsByStatus/{userId}")
    public ResponseEntity<?> getReservationsByStatus(@PathVariable Long userId,
                                                     @RequestBody ReservationSearchDto reservation) {
        List<Reservation> reservationPageable = reservationService.getReservationsByStatus(reservation, userId);
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