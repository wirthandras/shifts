package hu.wirthandras.shifts.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AmbulanceCar {

	@GeneratedValue
	@Id
	private Long id;
	private String licensePlateNumber;
	@Enumerated(EnumType.STRING)
	private CarType carType;

	public AmbulanceCar() {

	}

	public AmbulanceCar(String licensePlateNumber, CarType carType) {
		super();
		this.licensePlateNumber = licensePlateNumber;
		this.carType = carType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLicensePlateNumber() {
		return licensePlateNumber;
	}

	public void setLicensePlateNumber(String licensePlateNumber) {
		this.licensePlateNumber = licensePlateNumber;
	}

	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}

}
