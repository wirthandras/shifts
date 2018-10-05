package hu.wirthandras.shifts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.car.Car;
import hu.wirthandras.shifts.repository.CarRepository;

@Service
public class CarService {
	
	@Autowired
	private CarRepository repository;

	public Car getCar(String idAsString) {
		long id = Long.parseLong(idAsString);
		return repository.findById(id).get();
	}

	public List<Car> getCars() {
		return repository.findAll();
	}

	public void save(Car car) {
		repository.save(car);
	}

}
