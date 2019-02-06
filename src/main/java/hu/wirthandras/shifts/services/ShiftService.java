package hu.wirthandras.shifts.services;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.firstDayOfNextMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.wirthandras.shifts.domain.Shift;
import hu.wirthandras.shifts.domain.ShiftForDisplay;
import hu.wirthandras.shifts.domain.car.type.CarType;
import hu.wirthandras.shifts.domain.shift.ShiftInterval;
import hu.wirthandras.shifts.repository.CarTypeRepository;
import hu.wirthandras.shifts.repository.ShiftRepository;
import hu.wirthandras.shifts.services.interval.IntervalService;

@Service
public class ShiftService {

	@Autowired
	private ShiftRepository shiftRepository;

	@Autowired
	private CarTypeRepository carTypeRepository;

	@Autowired
	private IntervalService intervalService;

	@Autowired
	private ShiftWishService shiftWishService;

	public List<ShiftForDisplay> getAll() {
		List<Shift> shifts = getPersistedShifts();
		List<ShiftForDisplay> shiftDisplayed = new ArrayList<>();

		for (Shift s : shifts) {
			boolean wished = shiftWishService.contains(s);
			ShiftForDisplay o = new ShiftForDisplay(s);
			o.setWish(wished);
			shiftDisplayed.add(o);
		}
		return shiftDisplayed;
	}

	public Shift getShift(String idAsString) {
		Long id = Long.parseLong(idAsString);
		return shiftRepository.findById(id).get();
	}

	public void addInterval(ShiftInterval interval) {
		intervalService.addInterval(interval);
	}

	public List<ShiftInterval> getIntervals() {
		return intervalService.getIntervals();
	}

	public void generate() {
		LocalDate now = LocalDate.now();
		LocalDate nextMonth = now.with(firstDayOfNextMonth());
		for (LocalDate d : generateDays(nextMonth)) {
			for (ShiftInterval i : intervalService.getIntervals()) {
				shiftRepository.save(new Shift(Date.valueOf(d), i.getFrom(), i.getTo(), i.getCarType()));
			}
		}
	}

	public void clear() {
		shiftRepository.deleteAll();
	}

	private List<LocalDate> generateDays(LocalDate now) {
		LocalDate start = now.with(firstDayOfMonth());
		LocalDate end = now.with(lastDayOfMonth());
		return getDatesBetween(start, end);
	}

	private List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {

		long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
		return IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(i -> startDate.plusDays(i))
				.collect(Collectors.toList());
	}

	@Transactional
	public void remove(String id) {
		long idValue = Long.parseLong(id);
		Optional<Shift> shift = shiftRepository.findById(idValue);
		if (shift.isPresent()) {
			shiftRepository.delete(shift.get());
			removeWish(id);
		} else {
			throw new NoSuchElementException();
		}
	}

	public void removeInterval(String id) {
		intervalService.remove(id);
	}

	public void addWish(String id) {
		long idValue = Long.parseLong(id);
		Optional<Shift> shift = shiftRepository.findById(idValue);
		if (shift.isPresent()) {
			shiftWishService.add(shift.get());
		}
	}

	public void removeWish(String id) {
		long idValue = Long.parseLong(id);
		Optional<Shift> shift = shiftRepository.findById(idValue);
		if (shift.isPresent()) {
			shiftWishService.remove(shift.get());
		}
	}

	public List<Shift> getPersistedShifts() {
		return shiftRepository.findAll();
	}

	public List<CarType> getCarTypes() {
		return carTypeRepository.findAll();
	}

}
