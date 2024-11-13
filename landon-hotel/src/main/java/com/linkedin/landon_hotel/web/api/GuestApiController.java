package com.linkedin.landon_hotel.web.api;

import com.linkedin.landon_hotel.data.entity.Guest;
import com.linkedin.landon_hotel.data.repository.GuestRepository;
import com.linkedin.landon_hotel.web.exception.NotFoundException;
import com.linkedin.landon_hotel.web.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guests")
public class GuestApiController {
  
  private final GuestRepository guestRepository;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public GuestApiController(GuestRepository guestRepository) {
    this.guestRepository = guestRepository;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @GetMapping("/all")
  public List<Guest> getAllGuests() {
    return guestRepository.findAll();
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Guest createGuest(@RequestBody Guest guest) {
    return guestRepository.save(guest);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @GetMapping("/{id}")
  public Guest getGuest(@PathVariable("id") long id) {
    Optional<Guest> guest = guestRepository.findById(id);
    if (guest.isPresent()) {
      return guest.get();
    }
    throw new NotFoundException("Guest with id " + id + " not found");
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @PutMapping("/{id}")
  public Guest updateGuest(@PathVariable("id") long id, @RequestBody Guest guest) {
    if (id != guest.getId()) {
      throw new BadRequestException("id " + id + " on path does not match body");
    }
    return guestRepository.save(guest);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.RESET_CONTENT)
  public void deleteGuest(@PathVariable("id") long id) {
    guestRepository.deleteById(id);
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
}
