package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import model.exceptions.DomainException;

public class Reservation {

	private Integer roomNumber;
	private LocalDate checkIn;
	private LocalDate checkOut;

	private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	
	public Reservation(Integer roomNumber, LocalDate checkIn, LocalDate checkOut) throws DomainException{
		
		if (!checkOut.isAfter(checkIn)) {
			throw new DomainException("Check-out date must "
					+ "be after check-in date");
		}
		
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}

	public long duration() {
		return ChronoUnit.DAYS.between(checkIn, checkOut);
	}

	public void updateDates(LocalDate checkIn, LocalDate checkOut) throws DomainException{
		
		LocalDate dateNow = LocalDate.now();
		if (checkIn.isBefore(dateNow) || checkOut.isBefore(dateNow)) {
			throw new DomainException("Reservation dates "
					+ "for updates must be futures dates");
		} 
		if (!checkOut.isAfter(checkIn)) {
			throw new DomainException("Check-out date must "
					+ "be after check-in date");
		}
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	@Override
	public String toString() {
		return String.format("Room %d, check-in: %s, " + 
				"check-out: %s, %d nights", roomNumber, 
				checkIn.format(FORMATTER), checkOut.format(FORMATTER),
				duration());
	}
}
