package hu.wirthandras.shifts.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.car.Car;
import hu.wirthandras.shifts.domain.car.CarEvent;
import hu.wirthandras.shifts.domain.car.CarEventInput;
import hu.wirthandras.shifts.domain.employee.Employee;
import hu.wirthandras.shifts.domain.employee.EmployeeEvent;
import hu.wirthandras.shifts.domain.employee.EmployeeEventInput;
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

	public void addEmployeEvent(EmployeeEventInput input) {
		LocalDate dayDate = resolveDateFromDayId(input.getDayId());
		Employee e = employeeService.getEmployee(input.getEmployeeId());
		EmployeeEvent event = new EmployeeEvent(e, dayDate, input.getEventType());
		eventEmployeeRepository.save(event);
	}

	public void addCarEvent(CarEventInput input) {
		LocalDate dayDate = resolveDateFromDayId(input.getDayId());
		Car c = carService.getCar(input.getCarId());
		CarEvent event = new CarEvent(c, dayDate, input.getEventType());
		eventCarRepository.save(event);
	}

	private LocalDate resolveDateFromDayId(String dayId) {
		int dayNumber = Integer.parseInt(dayId);
		return LocalDate.now().withDayOfMonth(dayNumber);
	}

	public void removeEmployeEvent(EmployeeEventInput input) {
		Employee e = employeeService.getEmployee(input.getEmployeeId());
		LocalDate dayDate = resolveDateFromDayId(input.getDayId());
		List<EmployeeEvent> list = eventEmployeeRepository.findByEmployeeAndDate(e, dayDate);
		for (EmployeeEvent ee : list) {
			eventEmployeeRepository.delete(ee);
		}
	}

	public void removeCarEvent(CarEventInput input) {
		Car c = carService.getCar(input.getCarId());
		LocalDate dayDate = resolveDateFromDayId(input.getDayId());
		List<CarEvent> list = eventCarRepository.findByCarAndDate(c, dayDate);
		for (CarEvent ce : list) {
			eventCarRepository.delete(ce);
		}
	}

}
