package hu.wirthandras.shifts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.Shift;
import hu.wirthandras.shifts.domain.car.Car;
import hu.wirthandras.shifts.domain.shift.ShiftInterval;
import hu.wirthandras.shifts.repository.ShiftRepository;
import hu.wirthandras.shifts.services.interval.CarLockedException;
import hu.wirthandras.shifts.services.interval.IntervalService;
import hu.wirthandras.shifts.services.interval.ShiftIntervalAlreadyExistException;

@Service
public class ShiftService {

	private ShiftRepository shiftRepository;
	
	@Autowired
	private CarService carService;
	@Autowired
	private IntervalService intervalService;

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

	public void addInterval(ShiftInterval interval) throws ShiftIntervalAlreadyExistException, CarLockedException {
		intervalService.addInterval(interval);
	}

	public List<ShiftInterval> getIntervals() {
		return intervalService.getIntervals();
	}
	
	public List<Car> getFreeCars() {
		//TODO remove assigned cars from list
		return carService.getCars();
	}

}
