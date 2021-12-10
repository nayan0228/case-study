package com.nayan.payment.repo;

import org.springframework.data.repository.CrudRepository;

import com.nayan.payment.model.payment;

public interface Paymentrepository extends CrudRepository<payment, String> {

}
