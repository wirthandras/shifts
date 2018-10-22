package hu.wirthandras.shifts.domain.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Column(unique=true)
	@PlateNumberConstraint(message="error.platenumber.format")
	private String plateNumber;
	@Enumerated(EnumType.STRING)
	private CarType carType;

	public Car() {

	}

	public Car(String plateNumber, CarType carType) {
		super();
		this.plateNumber = plateNumber;
		this.carType = carType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Car) {
			Car c = (Car) obj;
			return plateNumber.equals(c.getPlateNumber()) && carType.equals(c.getCarType());
		} else {
			return false;
		}
	}

}
