package model.entities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Reservation {

	private Integer roomNumber;
	private LocalDate checkIn;
	private LocalDate checkOut;

	private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	
	public Reservation(Integer roomNumber, LocalDate checkIn, LocalDate checkOut) {
		super();
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

	public String updateDates(LocalDate checkIn, LocalDate checkOut) {
		
		LocalDate dateNow = LocalDate.now();
		if (checkIn.isBefore(dateNow) || checkOut.isBefore(dateNow)) {
			return "Reservation dates for updates must be futures dates";
		} 
		if (!checkOut.isAfter(checkIn)) {
			return "Check-out date must be after check-in date";
		}
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		return null;
	}

	@Override
	public String toString() {
		return String.format("Room %d, check-in: %s, " + 
				"check-out: %s, %d nights", roomNumber, 
				checkIn.format(FORMATTER), checkOut.format(FORMATTER),
				duration());
	}
}
