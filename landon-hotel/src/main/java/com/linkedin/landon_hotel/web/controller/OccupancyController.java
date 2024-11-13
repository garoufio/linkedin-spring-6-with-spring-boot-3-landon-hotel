package com.linkedin.landon_hotel.web.controller;


import com.linkedin.landon_hotel.service.RoomReservationService;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/occupancy")
public class OccupancyController {

  private final RoomReservationService roomReservationService;
  
  //-------------------------------------------------------------------------------------------------------------------
  
  public OccupancyController(RoomReservationService roomReservationService) {
    this.roomReservationService = roomReservationService;
  }
  
  //-------------------------------------------------------------------------------------------------------------------
  
  @GetMapping
  public String getRoomReservations(Model model, @RequestParam(value = "date", required = false) String dateString) {
    Date date = new Date();
    if (StringUtils.isNotBlank(dateString)) {
      try {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        date = dateFormatter.parse(dateString);
      }
      catch (Exception e) {
        System.err.println("Date should be formatted as yyyy-MM-dd");
        System.out.println(e.getMessage());
      }
    }
    model.addAttribute("date", date);
    model.addAttribute("reservations", roomReservationService.getRoomReservationsForDate(dateString));
    return "occupancy";
  }
  
  //-------------------------------------------------------------------------------------------------------------------

}
