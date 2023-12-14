package com.kyungil.webcar.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyungil.webcar.reservation.dao.ReservationDAO;
import com.kyungil.webcar.reservation.domain.Reservation;

@Service
public class ReservationService {
	@Autowired
	private ReservationDAO reservationDAO;

	public void add(Reservation reservation) {
		reservationDAO.addReservation(reservation);
	}

	public List<Reservation> getReservation(int userId) {
		return reservationDAO.getUserReservations(userId);
	}

	public List<Reservation> getAdminReservations() {
		return reservationDAO.getAdminReservations();
	}

	public void delivery(int[] userIds) {
		for (int userId : userIds) {
			reservationDAO.delivery(userId);
		}
	}
    public List<Reservation> getReservationByPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return reservationDAO.getPageReservations(offset, pageSize);
    }
	public int getPageCount() {
		return reservationDAO.getCount();
	}
}
