package com.linkedin.landon_hotel.service;

import ch.qos.logback.core.util.StringUtil;
import com.linkedin.landon_hotel.data.entity.Guest;
import com.linkedin.landon_hotel.data.entity.Reservation;
import com.linkedin.landon_hotel.data.entity.Room;
import com.linkedin.landon_hotel.data.repository.GuestRepository;
import com.linkedin.landon_hotel.data.repository.ReservationRepository;
import com.linkedin.landon_hotel.data.repository.RoomRepository;
import com.linkedin.landon_hotel.service.model.RoomReservation;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RoomReservationService {
  
  private final RoomRepository roomRepository;
  private final GuestRepository guestRepository;
  private final ReservationRepository reservationRepository;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public RoomReservationService(
      RoomRepository roomRepository,
      GuestRepository guestRepository,
      ReservationRepository reservationRepository
  ) {
    this.roomRepository = roomRepository;
    this.guestRepository = guestRepository;
    this.reservationRepository = reservationRepository;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public List<RoomReservation> getRoomReservationsForDate(String reservationDate) {
    Date date = null;
    if (StringUtil.isNullOrEmpty(reservationDate)) {
      date  = new Date(new java.util.Date().getTime());
    }
    else {
      date = Date.valueOf(reservationDate);
    }
    
    Map<Long, RoomReservation> roomReservations = new HashMap<>();
    // get rooms
    List<Room> rooms = roomRepository.findAll();
    rooms.forEach(room -> {
      RoomReservation roomReservation = new RoomReservation();
      roomReservation.setRoomId(room.getId());
      roomReservation.setRoomName(room.getName());
      roomReservation.setRoomNumber(room.getRoomNumber());
      roomReservations.put(roomReservation.getRoomId(), roomReservation);
    });
    // get reservations
    List<Reservation> reservations = reservationRepository.findAllByResDate(date);
    reservations.forEach(reservation -> {
      RoomReservation roomReservation = roomReservations.get(reservation.getRoomId());
      roomReservation.setReservationId(reservation.getId());
      roomReservation.setReservationDate(reservation.getResDate().toString());
      Optional<Guest> guest = guestRepository.findById(reservation.getGuestId());
      if (guest.isPresent()) {
        roomReservation.setGuestId(guest.get().getId());
        roomReservation.setFirstName(guest.get().getFirstName());
        roomReservation.setLastName(guest.get().getLastName());
      }
    });
    return roomReservations.values().stream().toList();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
