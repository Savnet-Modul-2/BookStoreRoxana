package com.example.Library.service;

import com.example.Library.dto.PaginatedReservationPeriodDto;
import com.example.Library.dto.PaginatedReservationStatusDto;
import com.example.Library.entities.*;
import com.example.Library.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ExemplaryRepository exemplaryRepository;
    @Autowired
    private LibrarianRepository librarianRepository;

    public Reservation create(Reservation reservation, Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        Exemplary exemplary = exemplaryRepository
                .findFirstAvailableExemplary(bookId, reservation.getStartDate(), reservation.getEndDate())
                .orElseThrow(() -> new IllegalStateException("No available exemplaries in the period."));

        reservation.setStatusReservation(StatusReservation.PENDING);
        reservation.setUser(user);
        reservation.setExemplary(exemplary);

        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationsByPeriod(PaginatedReservationPeriodDto paginatedReservationPeriodDto) {
        Pageable pageable = PageRequest.of(paginatedReservationPeriodDto.getPage(), paginatedReservationPeriodDto.getSize(), Sort.by(Sort.Direction.ASC, "startDate"));
        Page<Reservation> reservations = reservationRepository.findByStartDateBetween(paginatedReservationPeriodDto.getStartDate(), paginatedReservationPeriodDto.getEndDate(), pageable);
        return reservations.getContent();
    }

    public List<Reservation> getReservationsByStatus(PaginatedReservationStatusDto paginatedReservationStatusDto) {
        Pageable pageable = PageRequest.of(paginatedReservationStatusDto.getPage(), paginatedReservationStatusDto.getSize(), Sort.by(Sort.Direction.ASC, "statusReservation"));
        Page<Reservation> reservations = reservationRepository.findByStatusReservation(paginatedReservationStatusDto.getStatusReservation(), pageable);
        return reservations.getContent();
    }

    public Reservation updateReservationStatus(Long reservationId, Long librarianId, StatusReservation newStatus) {
        Librarian librarian = librarianRepository.findById(librarianId)
                .orElseThrow(() -> new EntityNotFoundException("Librarian not found"));

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));

        if (!librarian.getLibrary().equals(reservation.getExemplary().getBook().getLibrary())) {
            throw new IllegalStateException("Librarian does not have permission to update this reservation");
        }

        if (!reservation.getStatusReservation().isNextStatePossible(newStatus)) {
            throw new IllegalStateException("Invalid status transition from "
                    + reservation.getStatusReservation() + " to " + newStatus);
        }

        reservation.setStatusReservation(newStatus);
        return reservationRepository.save(reservation);
    }
}