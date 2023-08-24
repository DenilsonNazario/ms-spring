package com.ms.payroll.services;

import org.springframework.stereotype.Service;

import com.ms.payroll.entities.Payment;

@Service
public class PaymentService {
	
	public Payment getPayment (long workerID, int days) {		
		return new Payment("Bob",200.0,days);
	}
}
