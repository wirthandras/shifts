package hu.wirthandras.shifts.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.car.Car;
import hu.wirthandras.shifts.repository.CarRepository;

@Service
public class CarService {
	
	private CarRepository repository;

	@Autowired
	public void setRepository(CarRepository repository) {
		this.repository = repository;
	}	

	public Car getCar(String idAsString) {
		long id = Long.parseLong(idAsString);
		return repository.findById(id).get();
	}

	public List<Car> getCars() {
		return repository.findAll();
	}

	public void save(Car car) throws PlateNumberAlreadyExist {
		Optional<Car> o = repository.findByPlateNumber(car.getPlateNumber());
		if(!o.isPresent()) {
			repository.save(car);
		} else {
			throw new PlateNumberAlreadyExist();
		}
	}
	
	public List<String> getEvents(String day) {
		List<String> events = new ArrayList<>();
		events.add("cara" + day);
		events.add("carb" + day);
		events.add("carc" + day);
		return events;
	}

}
