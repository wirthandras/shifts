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
	private EventEmployeeRepository repositoryEventEmployee;

	@Autowired
	private CarEmployeeRepository repositoryEventCar;

	@Autowired
	private EmployeeService serviceEmployee;

	@Autowired
	private CarService serviceCar;

	public List<CarEvent> getCarEvents(String dayId, String car) {
		LocalDate dayDate = resolveDateFromDayId(dayId);
		Car c = serviceCar.getCar(car);
		return repositoryEventCar.findByCarAndDate(c, dayDate);
	}

	public List<EmployeeEvent> getEmployeeEvents(String dayId, String employee) {
		LocalDate dayDate = resolveDateFromDayId(dayId);
		Employee emp = serviceEmployee.getEmployee(employee);
		return repositoryEventEmployee.findByEmployeeAndDate(emp, dayDate);
	}

	public Set<Integer> getCarEventsDays(String carId) {
		Car c = serviceCar.getCar(carId);
		List<CarEvent> events = repositoryEventCar.findByCar(c);
		return events.stream()
				.map(o -> o.getDate().getDayOfMonth())
				.collect(Collectors.toSet());
	}

	public Set<Integer> getEmployeeEventsDays(String employeeId) {
		Employee e = serviceEmployee.getEmployee(employeeId);
		List<EmployeeEvent> events = repositoryEventEmployee.findByEmployee(e);
		return events.stream()
				.map(o -> o.getDate().getDayOfMonth())
				.collect(Collectors.toSet());
	}

	public void addEmployeEvent(EmployeeEventInput input) {
		LocalDate dayDate = resolveDateFromDayId(input.getDayId());
		Employee e = serviceEmployee.getEmployee(input.getEmployeeId());
		EmployeeEvent event = new EmployeeEvent(e, dayDate, input.getEventType());
		repositoryEventEmployee.save(event);
	}

	public void addCarEvent(CarEventInput input) {
		LocalDate dayDate = resolveDateFromDayId(input.getDayId());
		Car c = serviceCar.getCar(input.getCarId());
		CarEvent event = new CarEvent(c, dayDate, input.getEventType());
		repositoryEventCar.save(event);
	}

	LocalDate resolveDateFromDayId(String dayId) {
		int dayNumber = Integer.parseInt(dayId);
		return LocalDate.now().withDayOfMonth(dayNumber);
	}

	public void removeEmployeEvent(EmployeeEventInput input) {
		Employee e = serviceEmployee.getEmployee(input.getEmployeeId());
		LocalDate dayDate = resolveDateFromDayId(input.getDayId());
		List<EmployeeEvent> list = repositoryEventEmployee.findByEmployeeAndDate(e, dayDate);
		for (EmployeeEvent ee : list) {
			repositoryEventEmployee.delete(ee);
		}
	}

	public void removeCarEvent(CarEventInput input) {
		Car c = serviceCar.getCar(input.getCarId());
		LocalDate dayDate = resolveDateFromDayId(input.getDayId());
		List<CarEvent> list = repositoryEventCar.findByCarAndDate(c, dayDate);
		for (CarEvent ce : list) {
			repositoryEventCar.delete(ce);
		}
	}

}
