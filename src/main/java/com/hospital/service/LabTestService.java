package com.hospital.service;

import java.util.List;
import com.hospital.model.LabTest;

public interface LabTestService {

	
	LabTest createLabTest(LabTest p);
	
	List<LabTest> getAllLabTest();
	
	LabTest getLabTestById(Long id);
	
	LabTest updateLabTest(Long id, LabTest p);
	
	void deleteLabTest(Long id);
}
