package cs3220.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.service.Hw3DbService;

@WebServlet("/EditVaccine")
public class EditVaccine extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditVaccine() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		Hw3DbService dbService = new Hw3DbService();
		request.setAttribute("entry", dbService.getVaccine(id));
		dbService.close();
		
		request.getRequestDispatcher("/WEB-INF/EditVaccine.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		int id = Integer.parseInt(request.getParameter("id"));
		String vaccineName = request.getParameter("VaccineName");
		String dosesRequired = request.getParameter("DosesRequired");
		String daysBetweenDoses = request.getParameter("DaysBetween");
		
		Hw3DbService dbService = new Hw3DbService();
		dbService.updateVaccine(id, vaccineName, Integer.parseInt(dosesRequired), Integer.parseInt(daysBetweenDoses));
		dbService.close();
		
		response.sendRedirect("VaccineList");
	}

}
