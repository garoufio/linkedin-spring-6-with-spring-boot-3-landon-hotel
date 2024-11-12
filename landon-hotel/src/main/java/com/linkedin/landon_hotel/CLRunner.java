package com.linkedin.landon_hotel;

import com.linkedin.landon_hotel.data.entity.Guest;
import com.linkedin.landon_hotel.data.entity.Reservation;
import com.linkedin.landon_hotel.data.entity.Room;
import com.linkedin.landon_hotel.data.repository.GuestRepository;
import com.linkedin.landon_hotel.data.repository.ReservationRepository;
import com.linkedin.landon_hotel.data.repository.RoomRepository;
import com.linkedin.landon_hotel.service.RoomReservationService;
import com.linkedin.landon_hotel.service.model.RoomReservation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Component
public class CLRunner implements CommandLineRunner {
  
  private final RoomRepository roomRepository;
  private final GuestRepository guestRepository;
  private final ReservationRepository reservationRepository;
  private final RoomReservationService roomReservationService;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public CLRunner(
      RoomRepository roomRepository,
      GuestRepository guestRepository,
      ReservationRepository reservationRepository,
      RoomReservationService roomReservationService
  ) {
    this.roomRepository = roomRepository;
    this.guestRepository = guestRepository;
    this.reservationRepository = reservationRepository;
    this.roomReservationService = roomReservationService;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void run(String... args) throws Exception {
//    System.out.println("*** GUESTS ***");
//    List<Guest> guests = this.guestRepository.findAll();
//    guests.forEach(System.out::println);
//    System.out.println("*** ROOMS ***");
//    List<Room> rooms = this.roomRepository.findAll();
//    rooms.forEach(System.out::println);
//    System.out.println("*** RESERVATIONS ***");
//    List<Reservation> reservations = this.reservationRepository.findAll();
//    reservations.forEach(System.out::println);
    
    System.out.println("*** ROOMS ***");
    //List<Room> rooms = roomRepository.findAll();
//    rooms.forEach(System.out::println);
    Optional<Room> room = roomRepository.findByRoomNumberIgnoreCase("p1");
    if (room.isPresent()) System.out.println(room);
    
    System.out.println("*** GUESTS ***");
//    List<Guest> guests = guestRepository.findAll();
//    guests.forEach(System.out::println);
    
    System.out.println("*** RESERVATIONS ***");
//    List<Reservation> reservations = reservationRepository.findAll();
//    reservations.forEach(System.out::println);
    List<Reservation> reservationsAtDate = reservationRepository
        .findAllByResDate(Date.valueOf(LocalDate.of(2023, Month.AUGUST, 28)));
    reservationsAtDate.forEach(System.out::println);
    
    System.out.println("*** Room Reservation Service ***");
    List<RoomReservation> roomReservations = roomReservationService.getRoomReservationsForDate("2023-08-28");
    roomReservations.forEach(System.out::println);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
