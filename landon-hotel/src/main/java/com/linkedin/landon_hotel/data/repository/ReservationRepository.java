package com.linkedin.landon_hotel.data.repository;

import com.linkedin.landon_hotel.data.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

  List<Reservation> findAllByResDate(Date resDate);
  
  //-------------------------------------------------------------------------------------------------------------------

}
