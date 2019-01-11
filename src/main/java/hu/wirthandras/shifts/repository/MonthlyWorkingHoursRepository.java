package hu.wirthandras.shifts.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hu.wirthandras.shifts.domain.MonthlyWorkingHours;

@Repository
public interface MonthlyWorkingHoursRepository extends CrudRepository<MonthlyWorkingHours, Long> {

	public List<MonthlyWorkingHours> findAll();

}
