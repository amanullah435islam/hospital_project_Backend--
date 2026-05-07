package com.hospital.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospital.model.TestPayment;
import com.hospital.repository.ITestPaymentRepo;
import com.hospital.service.TestPaymentService;

@Service
public class TestPaymentServiceImp implements TestPaymentService{

	
	@Autowired
	private ITestPaymentRepo testPaymentRepo;
	@Override
	public TestPayment createTestPayment(TestPayment p) {
		
		return testPaymentRepo.save(p);
	}

	@Override
	public List<TestPayment> getAllTestPayment() {
		
		return testPaymentRepo.findAll();
	}

	@Override
	public TestPayment updateTestPayment(Long id, TestPayment p) {
		
		TestPayment existing = testPaymentRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("TestPayment not found"));
		
		existing.setAmount(p.getAmount());
		existing.setPaidAt(p.getPaidAt());
		existing.setPatientId(p.getPatientId());
		existing.setTestCode(p.getTestCode());
	
		return testPaymentRepo.save(existing);
	}

	@Override
	public void deleteTestPayment(Long id) {
			
		testPaymentRepo.deleteById(id);
		
	}

	@Override
	public TestPayment getById(Long id) {
		// TODO Auto-generated method stub
		return testPaymentRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("TestPayment not found with id: " + id));
	}

	  @Override
	    public List<TestPayment> getByTestCode(String testCode) {
	        return testPaymentRepo.findByTestCode(testCode);
	    }

}
