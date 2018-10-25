package hu.wirthandras.shifts.services;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import static java.time.temporal.TemporalAdjusters.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
		// TODO remove assigned cars from list
		return carService.getCars();
	}

	public void generate() {
		LocalDate now = LocalDate.now();
		LocalDate nextMonth = now.with(firstDayOfNextMonth());
		for (LocalDate d : getD(nextMonth)) {
			for (ShiftInterval i : intervalService.getIntervals()) {
				shiftRepository.save(new Shift(Date.valueOf(d), i.getFrom(), i.getTo()));
			}
		}
	}

	public void clear() {
		shiftRepository.deleteAll();
	}

	private List<LocalDate> getD(LocalDate now) {
		LocalDate start = now.with(firstDayOfMonth());
		LocalDate end = now.with(lastDayOfMonth());
		return getDatesBetween(start, end);
	}

	private List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {

		long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
		return IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(i -> startDate.plusDays(i))
				.collect(Collectors.toList());
	}

}
