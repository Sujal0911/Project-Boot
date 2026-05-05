package com.project.Project_Boot.Booking.Repository;

import com.project.Project_Boot.Booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
