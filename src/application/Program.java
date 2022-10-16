package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.print("Room number: ");
		int roomNumber = sc.nextInt();

		System.out.print("Check-in date (dd/MM/yyyy): ");
		LocalDate checkIn = LocalDate.parse(sc.next(), formatter);

		System.out.print("Check-out date (dd/MM/yyyy): ");
		LocalDate checkOut = LocalDate.parse(sc.next(), formatter);

		if (!checkOut.isAfter(checkIn)) {
			System.out.println("Error in reservation: Check-out" + "date must be after check-in date");
		} else {
			Reservation reservation = new Reservation(roomNumber, checkIn, checkOut);
			System.out.println(reservation);

			System.out.println();
			System.out.println("Enter data to update the reservation: ");

			System.out.print("Check-in date (dd/MM/yyyy): ");
			checkIn = LocalDate.parse(sc.next(), formatter);

			System.out.print("Check-out date (dd/MM/yyyy): ");
			checkOut = LocalDate.parse(sc.next(), formatter);

			LocalDate dateNow = LocalDate.now();
			if (checkIn.isBefore(dateNow) || checkOut.isBefore(dateNow)) {
				System.out.println("Error in reservation: Reservation " + "dates for updates must be futures dates");
			} else if (!checkOut.isAfter(checkIn)) {
				System.out.println("Error in reservation: Check-out" + "date must be after check-in date");
			}
			else {
				reservation.updateDates(checkIn, checkOut);
				System.out.println(reservation);
			}
		}

		sc.close();
	}

}
