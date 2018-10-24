package hu.wirthandras.shifts.services.interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.shift.ShiftInterval;

@Service
public class IntervalService {

	private Set<ShiftInterval> intervals = new HashSet<>();

	public void addInterval(ShiftInterval interval) throws ShiftIntervalAlreadyExistException, CarLockedException{
		
		if(carAlreadyLocked(interval)) {
			throw new CarLockedException();
		}
		if(!intervals.add(interval)) {
			throw new ShiftIntervalAlreadyExistException();
		};
	}
	
	private boolean carAlreadyLocked(ShiftInterval interval) {
		for(ShiftInterval i : intervals) {
			if (i.isCarLocked(interval)) {
				return true;
			}
		}
		return false;
	}

	public List<ShiftInterval> getIntervals() {
		List<ShiftInterval> copy = new ArrayList<>(intervals);
		Collections.sort(copy);
		return copy;
	}

}
