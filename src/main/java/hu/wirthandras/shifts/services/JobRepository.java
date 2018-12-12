package hu.wirthandras.shifts.services;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import hu.wirthandras.shifts.domain.job.Job;

public interface JobRepository extends CrudRepository<Job, Long>{
	
	public List<Job> findAll();

}
