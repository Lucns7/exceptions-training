package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	
	private Integer roomNumer;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation() {
	}

	public Reservation(Integer roomNumer, Date chekIn, Date checkOut) {
		if (checkOut.before(checkIn)) {
			throw new DomainException ("Error in reservation: Check-out date must be after check-in date");
		}
		this.roomNumer = roomNumer;
		this.checkIn = chekIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumer() {
		return roomNumer;
	}

	public void setRoomNumer(Integer roomNumer) {
		this.roomNumer = roomNumer;
	}

	public Date getChekIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public void updateDates(Date checkIn, Date checkOut) {
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			throw new DomainException ("Error in reservation: Reservation date must be future dates");
		}
		if (checkOut.before(checkIn)) {
			throw new DomainException ("Error in reservation: Check-out date must be after check-in date");
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	@Override
	public String toString() {
		return "Room " + roomNumer + ", check-in: " + sdf.format(checkIn) + ", check-out: " + sdf.format(checkOut) + ", " + duration() + " nights";    
	}
	
}
