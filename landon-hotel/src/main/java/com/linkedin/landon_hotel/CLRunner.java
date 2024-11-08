package com.linkedin.landon_hotel;

import com.linkedin.landon_hotel.data.entity.Room;
import com.linkedin.landon_hotel.data.repository.RoomRepository;
import org.springframework.boot.CommandLineRunner;

import java.util.List;
import java.util.Optional;

public class CLRunner implements CommandLineRunner {
  
  private final RoomRepository roomRepository;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public CLRunner(RoomRepository roomRepository) {
    this.roomRepository = roomRepository;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public void run(String... args) throws Exception {
    List<Room> rooms = roomRepository.findAll();
    Optional<Room> room = roomRepository.findByRoomNumberIgnoreCase("p1");
    if (room.isPresent()) System.out.println(room);
    
    rooms.forEach(System.out::println);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
