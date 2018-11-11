package hu.wirthandras.shifts.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.Shift;
import hu.wirthandras.shifts.domain.employee.Employee;

@Service
public class PlanService {

	private Map<Shift, Employee> plan = new HashMap<>();

	public void doPlan(List<Employee> employees, List<Shift> shifts) {
		for (Shift s : shifts) {
			Optional<Employee> o = getRandomEmployee(employees);
			if (o.isPresent()) {
				plan.put(s, o.get());
			} else {
				// may the employee set is empty
				break;
			}
		}
	}

	private Optional<Employee> getRandomEmployee(List<Employee> employees) {
		Collections.shuffle(employees);
		return employees.stream().findAny();
	}

	public void clear() {
		plan.clear();
	}
	
	/**
	 *  
	 * @return copy of the plan
	 */
	public Map<Shift, Employee> getPlan() {
		return new HashMap<>(plan);
	}

}
