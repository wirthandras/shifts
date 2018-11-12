package hu.wirthandras.shifts.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import hu.wirthandras.shifts.domain.car.Car;
import hu.wirthandras.shifts.domain.car.CarEvent;

public interface CarEmployeeRepository extends CrudRepository<CarEvent, Long> {

	public List<CarEvent> findAll();

	public List<CarEvent> findByCarAndDate(Car car, LocalDate date);

	public List<CarEvent> findByCar(Car car);

}
