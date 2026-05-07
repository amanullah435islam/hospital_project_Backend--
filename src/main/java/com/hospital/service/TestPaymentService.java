package com.hospital.service;

import java.util.List;
import com.hospital.model.TestPayment;



public interface TestPaymentService {
	
	
	TestPayment createTestPayment(TestPayment p);
	
	List<TestPayment> getAllTestPayment();
	
	TestPayment getById(Long id);
	 
	TestPayment updateTestPayment(Long id, TestPayment p);
	
	void deleteTestPayment(Long id);
	
	
	List<TestPayment> getByTestCode(String testCode);
}
