package com.nayan.booking.repo;

import org.springframework.data.repository.CrudRepository;

import com.nayan.booking.model.SeatAvaliability;

public interface seatAvalibilityRepo extends CrudRepository<SeatAvaliability, String>  {

}
