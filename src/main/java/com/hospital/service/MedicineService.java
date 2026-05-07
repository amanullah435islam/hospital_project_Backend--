package com.hospital.service;

import java.util.List;
import com.hospital.model.Medicine;

public interface MedicineService {

	
	Medicine createMedicine(Medicine p);
	
	List<Medicine> getAllMedicine();
	
	Medicine getMedicineById(Long id);
	
	Medicine updateMedicine(Long id, Medicine p);
	
	void deleteMedicine(Long id);
}
