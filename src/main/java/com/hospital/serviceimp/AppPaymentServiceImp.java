package com.hospital.serviceimp;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;
import com.hospital.model.AppPayment;
import com.hospital.repository.IAppPaymentRepo;
import com.hospital.service.AppPaymentService;
@Service
//@RequiredArgsConstructor
public class AppPaymentServiceImp implements AppPaymentService{

	@Autowired
	private IAppPaymentRepo appPaymentRepo;
	
	@Override
	public AppPayment createAppPayment(AppPayment p) {
		
		return appPaymentRepo.save(p);
	}

	@Override
	public List<AppPayment> getAllAppPayment() {
		
		return appPaymentRepo.findAll();
	}

	@Override
	public AppPayment updateAppPayment(Long id, AppPayment p) {

		AppPayment existing = appPaymentRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("AppPayment not found"));
		
		
		existing.setPaymentCode(p.getPaymentCode());
		existing.setAmount(p.getAmount());
		existing.setCardExpiry(p.getCardExpiry());
		existing.setCardNumber(p.getCardNumber());
		existing.setMobileNumber(p.getMobileNumber());
		existing.setPatientId(p.getPatientId());
		existing.setPatientName(p.getPatientName());
		existing.setPaymentDate(p.getPaymentDate());
		existing.setPaymentMethod(p.getPaymentMethod());
		existing.setTransactionId(p.getTransactionId());
		
		return appPaymentRepo.save(existing);
	}

	@Override
	public void deleteAppPayment(Long id) {
		
		appPaymentRepo.deleteById(id);
		
	}

	@Override
	public AppPayment getAppPaymentById(Long id) {
		// TODO Auto-generated method stub
		return appPaymentRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("AppPayment not found with id: " + id));
	}

}
