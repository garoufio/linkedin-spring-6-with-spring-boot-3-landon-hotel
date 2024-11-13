package com.linkedin.landon_hotel.web.api;

import com.linkedin.landon_hotel.data.entity.Reservation;
import com.linkedin.landon_hotel.data.repository.ReservationRepository;
import com.linkedin.landon_hotel.web.exception.BadRequestException;
import com.linkedin.landon_hotel.web.exception.NotFoundException;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationApiController {
  
  private final ReservationRepository reservationRepository;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public ReservationApiController(ReservationRepository reservationRepository) {
    this.reservationRepository = reservationRepository;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @GetMapping("/all")
  public List<Reservation> getAllReservations(@RequestParam(value="date", required=false) String dateString) {
    if (StringUtils.isNotBlank(dateString)) {
      Date date = new Date(new java.util.Date().getTime());
      return reservationRepository.findAllByResDate(date);
    }
    return reservationRepository.findAll();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @GetMapping("/{id}")
  public Reservation getReservationById(@PathVariable("id") long id) {
    Optional<Reservation> reservation = reservationRepository.findById(id);
    if (reservation.isPresent()) {
      return reservation.get();
    }
    throw new NotFoundException("Reservation with id " + id + " not found");
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Reservation createReservation(@RequestBody Reservation reservation) {
    return reservationRepository.save(reservation);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @PutMapping("/{id}")
  public Reservation updateReservation(@PathVariable("id") long id, @RequestBody Reservation reservation) {
    if (id != reservation.getId()) {
      throw new BadRequestException("id " + id + " on path does not match body");
    }
    throw new NotFoundException("Reservation with id " + id + " not found");
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.RESET_CONTENT)
  public void deleteReservation(@PathVariable("id") long id) {
    reservationRepository.deleteById(id);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
