package hu.wirthandras.shifts.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import hu.wirthandras.shifts.domain.car.type.CarType;

public interface CarTypeRepository extends CrudRepository<CarType, Long> {

	public List<CarType> findAll();

}
