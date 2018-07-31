package hu.wirthandras.shifts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.Shift;
import hu.wirthandras.shifts.repository.ShiftRepository;

@Service
public class ShiftService {

	private ShiftRepository shiftRepository;

	@Autowired
	public void setShiftRepository(ShiftRepository shiftRepository) {
		this.shiftRepository = shiftRepository;
	}
	
	public List<Shift> getAll() {
		return shiftRepository.findAll();
	}
	
	
}
