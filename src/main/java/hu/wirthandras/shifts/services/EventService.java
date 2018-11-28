package hu.wirthandras.shifts.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.car.Car;
import hu.wirthandras.shifts.domain.car.CarEvent;
import hu.wirthandras.shifts.domain.car.CarEventType;
import hu.wirthandras.shifts.domain.employee.Employee;
import hu.wirthandras.shifts.domain.employee.EmployeeEvent;
import hu.wirthandras.shifts.domain.employee.EmployeeEventType;
import hu.wirthandras.shifts.repository.CarEmployeeRepository;
import hu.wirthandras.shifts.repository.EventEmployeeRepository;

@Service
public class EventService {

	@Autowired
	private EventEmployeeRepository eventEmployeeRepository;

	@Autowired
	private CarEmployeeRepository eventCarRepository;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CarService carService;

	public List<CarEvent> getCarEvents(String dayId, String car) {
		LocalDate dayDate = resolveDateFromDayId(dayId);
		Car c = carService.getCar(car);
		return eventCarRepository.findByCarAndDate(c, dayDate);
	}

	public List<EmployeeEvent> getEmployeeEvents(String dayId, String employee) {
		LocalDate dayDate = resolveDateFromDayId(dayId);
		Employee emp = employeeService.getEmployee(employee);
		return eventEmployeeRepository.findByEmployeeAndDate(emp, dayDate);
	}

	public Set<Integer> getCarEventsDays(String carId) {
		Car c = carService.getCar(carId);
		List<CarEvent> events = eventCarRepository.findByCar(c);
		return events.stream()
				.map(o -> o.getDate().getDayOfMonth())
				.collect(Collectors.toSet());
	}

	public Set<Integer> getEmployeeEventsDays(String employeeId) {
		Employee e = employeeService.getEmployee(employeeId);
		List<EmployeeEvent> events = eventEmployeeRepository.findByEmployee(e);
		return events.stream()
				.map(o -> o.getDate().getDayOfMonth())
				.collect(Collectors.toSet());
	}

	public void addEmployeEvent(String employeeId, String dayId, EmployeeEventType type) {
		LocalDate dayDate = resolveDateFromDayId(dayId);
		Employee e = employeeService.getEmployee(employeeId);
		EmployeeEvent event = new EmployeeEvent(e, dayDate, type);
		eventEmployeeRepository.save(event);
	}

	public void addCarEvent(String carId, String dayId, CarEventType type) {
		LocalDate dayDate = resolveDateFromDayId(dayId);
		Car c = carService.getCar(carId);
		CarEvent event = new CarEvent(c, dayDate, type);
		eventCarRepository.save(event);
	}

	private LocalDate resolveDateFromDayId(String dayId) {
		int dayNumber = Integer.parseInt(dayId);
		return LocalDate.now().withDayOfMonth(dayNumber);
	}

}
