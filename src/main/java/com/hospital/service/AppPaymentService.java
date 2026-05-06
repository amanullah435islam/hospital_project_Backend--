package com.hospital.service;

import java.util.List;
import com.hospital.model.AppPayment;

public interface AppPaymentService {

	
	AppPayment createAppPayment(AppPayment p);
	
	List<AppPayment> getAllAppPayment();
	
	AppPayment updateAppPayment(Long id, AppPayment p);
	
	void deleteAppPayment(Long id);
}
