package com.linkedin.landon_hotel.data.repository;

import com.linkedin.landon_hotel.data.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {



}
