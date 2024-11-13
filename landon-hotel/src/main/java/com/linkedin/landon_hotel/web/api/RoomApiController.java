package com.linkedin.landon_hotel.web.api;

import com.linkedin.landon_hotel.data.entity.Room;
import com.linkedin.landon_hotel.data.repository.RoomRepository;
import com.linkedin.landon_hotel.web.exception.BadRequestException;
import com.linkedin.landon_hotel.web.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
public class RoomApiController {
  
  private final RoomRepository roomRepository;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public RoomApiController(RoomRepository roomRepository) {
    this.roomRepository = roomRepository;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @GetMapping("/all")
  public List<Room> getAllRooms() {
    return roomRepository.findAll();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Room createRoom(@RequestBody Room room) {
    return roomRepository.save(room);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @GetMapping("/{id}")
  public Room getRoom(@PathVariable("id") long id) {
    Optional<Room> room = roomRepository.findById(id);
    if (room.isPresent()) {
      return room.get();
    }
  
    throw new NotFoundException("Room with id " + id + " not found");
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @PutMapping("/{id}")
  public Room updateRoom(@PathVariable("id") long id, @RequestBody Room room) {
    if (id != room.getId()) {
      throw new BadRequestException("id " + id + " on path does not match body");
    }
    return roomRepository.save(room);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.RESET_CONTENT)
  public void deleteRoom(@PathVariable("id") long id) {
    roomRepository.deleteById(id);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
