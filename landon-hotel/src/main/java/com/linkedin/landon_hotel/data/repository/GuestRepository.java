package com.linkedin.landon_hotel.data.repository;

import com.linkedin.landon_hotel.data.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {



}
