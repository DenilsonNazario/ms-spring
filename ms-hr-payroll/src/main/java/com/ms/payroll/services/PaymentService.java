package com.ms.payroll.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ms.payroll.entities.Payment;
import com.ms.payroll.entities.Worker;

@Service
public class PaymentService {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${hr-worker.host}")
	private String host;

	public Payment getPayment(long workerID, int days) {
		
		Map<String, String> uriVariables = new HashMap<>();
		
		uriVariables.put("id", String.valueOf(workerID));
		
		Worker worker = restTemplate.getForObject(host + "/workers/{id}",
				Worker.class, uriVariables);
		
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}
}