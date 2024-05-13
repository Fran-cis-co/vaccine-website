package cs3220.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.service.*;
@WebServlet("/NewVaccine")
public class NewVaccine extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NewVaccine() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/NewVaccine.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String vaccineName = request.getParameter("VaccineName");
		String dosesRequired = request.getParameter("DosesRequired");
		String daysBetween = request.getParameter("DaysBetween");
 	
		Hw3DbService dbService = new Hw3DbService();
		dbService.addVaccine(vaccineName, Integer.parseInt(dosesRequired), Integer.parseInt(daysBetween));
		response.sendRedirect("VaccineList");
	}

}
