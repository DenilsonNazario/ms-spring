package com.ms.payroll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ms.payroll.entities.Payment;
import com.ms.payroll.entities.Worker;
import com.ms.payroll.feignclients.WorkerFeignClients;

@Service
public class PaymentService {

	@Autowired
	private WorkerFeignClients workerFeignClients;

	public Payment getPayment(long workerID, int days) {

		Worker worker = workerFeignClients.findById(workerID).getBody();
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}
}