package hu.wirthandras.shifts.services.interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.shift.ShiftInterval;

@Service
public class IntervalService {

	private List<ShiftInterval> intervals = new ArrayList<>();

	public void addInterval(ShiftInterval interval) {
		intervals.add(interval);
		recalculateDailyIds();
	}
	
	private void recalculateDailyIds() {
		int dailyId = 1;
		for(ShiftInterval i : getIntervals()) {
			i.setDayId(dailyId);
			dailyId++;
		}
	}

	public List<ShiftInterval> getIntervals() {
		List<ShiftInterval> copy = new ArrayList<>(intervals);
		Collections.sort(copy);
		return Collections.unmodifiableList(copy);
	}

	public void remove(String id) {
		int dayId = Integer.parseInt(id);
		ShiftInterval i = find(dayId);
		intervals.remove(i);
		recalculateDailyIds();
	}
	
	private ShiftInterval find(int dayId) {
		Iterator<ShiftInterval> it = intervals.iterator();
		ShiftInterval e = null;
		while(e == null && it.hasNext()) {
			ShiftInterval i = it.next();
			if (i.getDayId() == dayId) {
				e = i;
			}
		}
		return e;
	}

	public int numberOfShifts() {
		return intervals.size();
	}

}
