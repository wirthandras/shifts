package hu.wirthandras.shifts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.car.Car;
import hu.wirthandras.shifts.domain.car.type.CarType;
import hu.wirthandras.shifts.repository.CarRepository;
import hu.wirthandras.shifts.repository.CarTypeRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository repository;

	@Autowired
	private CarTypeRepository repositoryCarType;

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

	public List<CarType> getCarTypes() {
		return repositoryCarType.findAll();
	}

}
