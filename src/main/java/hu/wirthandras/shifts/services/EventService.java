package hu.wirthandras.shifts.services;

import java.time.LocalDate;
import java.util.List;

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
	private EmployeeService service;

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
		Employee emp = service.getEmployee(employee);
		return eventEmployeeRepository.findByEmployeeAndDate(emp, dayDate);
	}

}
