package hu.wirthandras.shifts.services;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.Shift;

@Service
public class ShiftWishService {
	
	private static Logger LOGGER = Logger.getLogger(ShiftWishService.class.getName());
	
	private Set<Shift> wish = new HashSet<>();
	
	public void add(Shift shift) {
		LOGGER.info("Shift " + shift.getId() + " is added to Wish List");
		wish.add(shift);
	}
	
	public void remove(Shift shift) {
		LOGGER.info("Shift " + shift.getId() + " is removed from Wish List");
		wish.remove(shift);
	}

	public boolean contains(Shift s) {
		return wish.contains(s);
	}
	
}
