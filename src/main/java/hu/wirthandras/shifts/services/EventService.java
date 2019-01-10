package hu.wirthandras.shifts.services;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.firstDayOfNextMonth;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.EventDuplicatedException;
import hu.wirthandras.shifts.domain.employee.Employee;
import hu.wirthandras.shifts.domain.employee.EmployeeEvent;
import hu.wirthandras.shifts.domain.employee.EmployeeEventInput;
import hu.wirthandras.shifts.domain.employee.EmployeeEventType;
import hu.wirthandras.shifts.repository.EventEmployeeRepository;

@Service
public class EventService {

	private static Logger LOGGER = Logger.getLogger(EventService.class.getName());

	@Autowired
	private EventEmployeeRepository repositoryEventEmployee;

	@Autowired
	private EmployeeService serviceEmployee;

	public List<EmployeeEvent> getEmployeeEvents(String dayId, String employee) {
		LocalDate dayDate = ServiceUtil.resolveDateFromDayId(dayId);
		Employee emp = serviceEmployee.getEmployee(employee);
		return repositoryEventEmployee.findByEmployeeAndDate(emp, dayDate);
	}

	public Set<Integer> getEmployeeEventsDays(String employeeId, LocalDate currentMonth) {
		Employee e = serviceEmployee.getEmployee(employeeId);
		List<EmployeeEvent> events = repositoryEventEmployee.findByEmployee(e);
		return events.stream()
				.filter(o -> o.getDate().isAfter(currentMonth.with(firstDayOfMonth())))
				.filter(o -> o.getDate().isBefore(currentMonth.with(firstDayOfNextMonth())))
				.map(o -> o.getDate().getDayOfMonth())
				.collect(Collectors.toSet());
	}

	@Transactional
	public void addEmployeEvent(EmployeeEventInput input) {
		LocalDate dayDate = ServiceUtil.resolveDateFromDayId(input.getDayId());
		Employee e = serviceEmployee.getEmployee(input.getEmployeeId());
		EmployeeEvent event = new EmployeeEvent(e, dayDate, input.getEventType());
		try {
			repositoryEventEmployee.save(event);
		} catch (DataIntegrityViolationException ex) {
			LOGGER.log(Level.OFF, ex.getMessage(), ex);
			throw new EventDuplicatedException();
		}
	}

	public void removeEmployeEvent(EmployeeEventInput input) {
		Employee e = serviceEmployee.getEmployee(input.getEmployeeId());
		LocalDate dayDate = ServiceUtil.resolveDateFromDayId(input.getDayId());
		List<EmployeeEvent> list = repositoryEventEmployee.findByEmployeeAndDate(e, dayDate);
		for (EmployeeEvent ee : list) {
			repositoryEventEmployee.delete(ee);
		}
	}

	public long getNumberOfHolidays(Employee emp) {
		return repositoryEventEmployee.findByEmployee(emp).stream()
				.filter(x -> x.getType().equals(EmployeeEventType.HOLIDAY)).count();
	}

	public boolean isSick(Employee emp, int dayId) {
		return isEventOnDay(emp, dayId, EmployeeEventType.SICK);
	}

	public boolean isOnHoliday(Employee emp, int dayId) {
		return isEventOnDay(emp, dayId, EmployeeEventType.HOLIDAY);
	}

	private boolean isEventOnDay(Employee emp, int dayId, EmployeeEventType type) {
		LocalDate dayDate = ServiceUtil.resolveDateFromDayId(dayId);
		List<EmployeeEvent> events = repositoryEventEmployee.findByEmployeeAndDate(emp, dayDate);
		return events.stream().anyMatch(x -> x.getType().equals(type));
	}

}
