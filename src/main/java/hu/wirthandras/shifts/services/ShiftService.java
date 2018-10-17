package hu.wirthandras.shifts.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.Shift;
import hu.wirthandras.shifts.domain.shift.ShiftInterval;
import hu.wirthandras.shifts.repository.ShiftRepository;

@Service
public class ShiftService {

	private ShiftRepository shiftRepository;

	private List<ShiftInterval> intervals = new ArrayList<>();

	@Autowired
	public void setShiftRepository(ShiftRepository shiftRepository) {
		this.shiftRepository = shiftRepository;
	}

	public List<Shift> getAll() {
		return shiftRepository.findAll();
	}

	public Shift getShift(String idAsString) {
		Long id = Long.parseLong(idAsString);
		return shiftRepository.findById(id).get();
	}

	public void addInterval(ShiftInterval interval) {
		intervals.add(interval);
	}

	public List<ShiftInterval> getIntervals() {
		return intervals;
	}

}
