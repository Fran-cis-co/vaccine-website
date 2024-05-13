package cs3220.servlet;

import java.io.IOException;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.model.VaccineListEntry;
import cs3220.service.*;

@WebServlet("/AddPatient")
public class AddPatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddPatient() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/AddPatient.jsp").forward(request, response);
	}
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//set variables
		String name = request.getParameter("name");
		String vaccine = request.getParameter("vaccine");
		LocalDateTime local = LocalDateTime.now();
		DateTimeFormatter x = DateTimeFormatter.ofPattern("MM/dd/YYYY");
		String date = x.format(local);
		String secondDose = null;
		int c = 0;
		
		// grab lists
		List<VaccineListEntry> entries = (List<VaccineListEntry>) getServletContext().getAttribute("entries");
			
		for(VaccineListEntry y: entries){
			if(y.getVaccineName().contains(vaccine)) {
				c = y.getDosesLeft() - 1;
					
				if(c < 0) {
					c = 0;
				}	
			}
		}
		
		Hw3DbService dbService = new Hw3DbService();
		dbService.addPatient(name, vaccine, date, secondDose, c);
		dbService.close();
		
		response.sendRedirect("PatientList");
	}
}
