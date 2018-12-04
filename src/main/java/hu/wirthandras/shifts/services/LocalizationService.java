package hu.wirthandras.shifts.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.Event;
import hu.wirthandras.shifts.domain.car.CarEvent;
import hu.wirthandras.shifts.domain.day.EventLocalized;
import hu.wirthandras.shifts.domain.employee.EmployeeEvent;

@Service
//TODO try to melt together the localizeEmployeeEvents and localizeCarEvents
public class LocalizationService {

	@Autowired
	private MessageSource ms;

	private Locale getLocale() {
		return LocaleContextHolder.getLocale();
	}

	public List<EventLocalized> localizeEmployeeEvents(List<EmployeeEvent> list) {
		List<EventLocalized> result = new ArrayList<>();
		for (Event e : list) {
			result.add(new EventLocalized(ms.getMessage(e.getTypeString(), null, getLocale())));
		}
		return result;
	}

	public List<EventLocalized> localizeCarEvents(List<CarEvent> input) {
		List<EventLocalized> result = new ArrayList<>();
		for (Event e : input) {
			result.add(new EventLocalized(ms.getMessage(e.getTypeString(), null, getLocale())));
		}
		return result;
	}

}
