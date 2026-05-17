package com.project.Project_Boot.Booking.Controller;

import com.project.Project_Boot.Booking.Service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("see-booking")
    public ResponseEntity<?> booking(){
            return new ResponseEntity<>(bookingService.seeBooking(), HttpStatus.OK);
    }

}
