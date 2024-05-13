package cs3220.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.service.Hw3DbService;

@WebServlet("/PatientList")
public class PatientList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PatientList() {
        super();
    }
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Hw3DbService dbService = new Hw3DbService();
		getServletContext().setAttribute("patientEntries", dbService.getPatients());
		getServletContext().setAttribute("entries", dbService.getVaccineEntries());
		dbService.close();
		request.getRequestDispatcher("/WEB-INF/PatientList.jsp").forward(request, response);
	}

}
