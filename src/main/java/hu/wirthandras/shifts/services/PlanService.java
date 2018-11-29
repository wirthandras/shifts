package hu.wirthandras.shifts.services;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.wirthandras.shifts.domain.Shift;
import hu.wirthandras.shifts.domain.employee.Employee;
import hu.wirthandras.shifts.repository.converter.LocalDateAttributeConverter;

@Service
public class PlanService {

	private static int WORK_DAY_HOURS = 8;

	private Map<Shift, Set<Employee>> plan = new HashMap<>();

	@Autowired
	private EventService serviceEvent;

	@Autowired
	private ShiftService serviceShift;

	@Autowired
	private EmployeeService serviceEmployee;

	public void doPlan() {
		for (Shift s : serviceShift.getPersistedShifts()) {
			Optional<Employee> o = getRandomEmployee(serviceEmployee.getEmployees());
			if (o.isPresent()) {
				Set<Employee> set = plan.get(s);
				if (set == null) {
					set = new HashSet<>();
					set.add(o.get());
					plan.put(s, set);
				} else {
					set.add(o.get());
				}
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
	public Map<Shift, Set<Employee>> getPlan() {
		return new HashMap<>(plan);
	}

	public int sumWorkingHours(Employee emp) {
		int sum = 0;
		for (Shift s : plan.keySet()) {
			if (plan.get(s).contains(emp)) {
				sum += s.duration();
			}
		}
		return sum;
	}

	public long getHolidayHours(Employee emp) {
		return serviceEvent.getNumberOfHolidays(emp) * WORK_DAY_HOURS;
	}

	public boolean isNormalShiftDay(Employee emp, int dayId) {
		// TODO Auto-generated method stub
		return true;
	}

	public String getTime(Employee emp, int actDay) {
		LocalDate date = ServiceUtil.resolveNextMonthDateFromDayId(actDay);

		LocalDateAttributeConverter converter = new LocalDateAttributeConverter();

		Set<Shift> shifts = plan.keySet().stream()
				.filter(x -> converter.convertToEntityAttribute(x.getDate()).isEqual(date)).collect(Collectors.toSet());

		for (Shift shift : shifts) {
			if (plan.get(shift).contains(emp)) {
				return shift.getStart() + "-" + shift.getEnd();
			}
		}
		return null;
	}

}
