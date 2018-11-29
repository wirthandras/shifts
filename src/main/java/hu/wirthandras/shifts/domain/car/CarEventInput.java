package hu.wirthandras.shifts.domain.car;

public class CarEventInput {

	private String carId;
	private String dayId;
	private CarEventType eventType;

	public CarEventInput(String carId, String dayId, CarEventType eventType) {
		super();
		this.carId = carId;
		this.dayId = dayId;
		this.eventType = eventType;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getDayId() {
		return dayId;
	}

	public void setDayId(String dayId) {
		this.dayId = dayId;
	}

	public CarEventType getEventType() {
		return eventType;
	}

	public void setEventType(CarEventType eventType) {
		this.eventType = eventType;
	}

}
