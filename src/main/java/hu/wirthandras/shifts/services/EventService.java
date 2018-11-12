package hu.wirthandras.shifts.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.car.Car;
import hu.wirthandras.shifts.domain.car.CarEvent;
import hu.wirthandras.shifts.domain.employee.Employee;
import hu.wirthandras.shifts.domain.employee.EmployeeEvent;
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

	public List<CarEvent> getCarEvents(String day, String car) {
		int dayNumber = Integer.parseInt(day);
		LocalDate dayDate = LocalDate.now().withDayOfMonth(dayNumber);
		Car c = carService.getCar(car);
		return eventCarRepository.findByCarAndDate(c, dayDate);
	}

	public List<EmployeeEvent> getEmployeeEvents(String day, String employee) {
		int dayNumber = Integer.parseInt(day);
		LocalDate dayDate = LocalDate.now().withDayOfMonth(dayNumber);
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

}
