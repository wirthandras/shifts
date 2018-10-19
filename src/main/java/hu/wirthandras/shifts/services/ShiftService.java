package hu.wirthandras.shifts.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.Shift;
import hu.wirthandras.shifts.domain.car.Car;
import hu.wirthandras.shifts.domain.shift.ShiftInterval;
import hu.wirthandras.shifts.repository.ShiftRepository;

@Service
public class ShiftService {

	private ShiftRepository shiftRepository;
	
	@Autowired
	private CarService carService;

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
		List<ShiftInterval> copy = new ArrayList<>(intervals);
		Collections.sort(copy);
		return copy;
	}
	
	public List<Car> getFreeCars() {
		//TODO remove assigned cars from list
		return carService.getCars();
	}

}
