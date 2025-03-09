package com.example.Library.config;

import com.example.Library.entities.Reservation;
import com.example.Library.entities.StatusReservation;
import com.example.Library.repository.ReservationRepository;
import com.example.Library.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;

@Configuration
@EnableScheduling
public class ReservationScheduler {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "* * 10 * * *")
    public void cancelExpiredPendingReservations() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        List<Reservation> expiredReservations = reservationRepository
                .findByStatusReservationAndStartDate(StatusReservation.PENDING, yesterday)
                .stream()
                .map(reservation -> {
                    reservation.setStatusReservation(StatusReservation.CANCELED);
                    return reservation;
                })//.peek(reservation -> reservation.setStatusReservation(StatusReservation.CANCELED))
                .toList();
        reservationRepository.saveAll(expiredReservations);
    }

    @Scheduled(cron = "* * 10 * * *")
    public void markDelayedReservations() {
        LocalDate yesterday = LocalDate.now().minusDays(1);

        List<Reservation> expiredReservations = reservationRepository
                .findByStatusReservationAndEndDate(StatusReservation.IN_PROGRESS, yesterday)
                .stream()
                .peek(reservation -> reservation.setStatusReservation(StatusReservation.DELAYED))
                .toList();

        reservationRepository.saveAll(expiredReservations);

        expiredReservations.forEach(reservation -> {
            String userEmail = reservation.getUser().getEmail();
            String librarianEmail = reservation.getExemplary().getBook().getLibrary().getLibrarian().getEmail();
            String userPhone = reservation.getUser().getPhoneNumber();
            String bookTitle = reservation.getExemplary().getBook().getTitle();

            String userSubject = "Book return";
            String userText = " You need to return the book '" + bookTitle +
                    "'. Please bring it back as soon as possible.";
            emailService.sendEmail(userEmail, userSubject, userText);

            String librarianSubject = "Unreturned Book!";
            String librarianText = "The user " + reservation.getUser().getFirstName() + " "
                    + reservation.getUser().getLastName() +
                    " (Phone number: " + userPhone + ") has not returned the  '" + bookTitle +
                    "' on. Please contact them.";
            emailService.sendEmail(librarianEmail, librarianSubject, librarianText);
        });
    }
}
