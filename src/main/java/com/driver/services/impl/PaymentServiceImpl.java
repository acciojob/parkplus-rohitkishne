package com.driver.services.impl;

import com.driver.controllers.ReservationController;
import com.driver.model.Payment;
import com.driver.model.PaymentMode;
import com.driver.model.Reservation;
import com.driver.repository.PaymentRepository;
import com.driver.repository.ReservationRepository;
import com.driver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ReservationRepository reservationRepository2;
    @Autowired
    PaymentRepository paymentRepository2;

    @Override
    public Payment pay(Integer reservationId, int amountSent, String mode) throws Exception {

        Reservation reservation = reservationRepository2.findById(reservationId).get();

        int totalAmount = reservation.getNumberOfHours() * reservation.getSpot().getPricePerHour();

        if(amountSent<totalAmount)
        {
            throw new Exception("Insufficient Amount");
        }
        Payment payment;
        if(mode.equalsIgnoreCase(String.valueOf(PaymentMode.CASH)))
        {
            payment = new Payment();
            payment.setPaymentCompleted(true);
            payment.setPaymentMode(PaymentMode.valueOf(mode));
            payment.setReservation(reservation);
            reservation.setPayment(payment);
            reservationRepository2.save(reservation);

        } else if (mode.equalsIgnoreCase(String.valueOf(PaymentMode.CARD))) {

            payment = new Payment();
            payment.setPaymentCompleted(true);
            payment.setPaymentMode(PaymentMode.valueOf(mode));
            payment.setReservation(reservation);
            reservation.setPayment(payment);
            reservationRepository2.save(reservation);
        } else if (mode.equalsIgnoreCase(String.valueOf(PaymentMode.UPI))) {

            payment = new Payment();
            payment.setPaymentCompleted(true);
            payment.setPaymentMode(PaymentMode.valueOf(mode));
            payment.setReservation(reservation);
            reservation.setPayment(payment);
            reservationRepository2.save(reservation);
        }
        else {
            payment = new Payment();
            payment.setPaymentCompleted(false);
            payment.setPaymentMode(PaymentMode.valueOf(mode));
            reservation.setPayment(payment);
            payment.setReservation(reservation);
            reservationRepository2.save(reservation);
            throw new Exception("Payment mode not detected");
        }

        return payment;
    }
}
