package hu.wirthandras.shifts.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hu.wirthandras.shifts.domain.Shift;

@Repository
public interface ShiftRepository extends CrudRepository<Shift, Long> {

	List<Shift> findAll();
	
	Shift save(Shift shift);
	
}
