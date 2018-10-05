package hu.wirthandras.shifts.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hu.wirthandras.shifts.domain.car.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
	
	public Optional<Car> findById(Long id);
	
	public List<Car> findAll();

}
