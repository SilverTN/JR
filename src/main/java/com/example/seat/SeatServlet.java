package com.example.seat;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/seats")
public class SeatServlet extends HttpServlet {
    private final SeatDao seatDao = new SeatDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Seat> seats = seatDao.getAllSeats();
        req.setAttribute("seats", seats);
        req.getRequestDispatcher("/views/seats.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int seatId = Integer.parseInt(req.getParameter("seatId"));
        String passportNumber = req.getParameter("passportNumber");
        seatDao.reserveSeat(seatId, passportNumber);
        resp.sendRedirect("seats");
    }
} 