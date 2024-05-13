package cs3220.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.model.PatientEntryModel;
import cs3220.model.VaccineListEntry;
import cs3220.service.Hw3DbService;


@WebServlet("/RecievedVaccine")
public class RecievedVaccine extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RecievedVaccine() {
        super();
    }
    
    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String vaccineName = "";
		int doses = 0;
		LocalDateTime local = LocalDateTime.now();
		DateTimeFormatter y = DateTimeFormatter.ofPattern("MM/dd/YYYY");
		String date = y.format(local);	
		
		List<PatientEntryModel> patient = (List<PatientEntryModel>) getServletContext().getAttribute("patientEntries");
		List<VaccineListEntry> vaccine = (List<VaccineListEntry>) getServletContext().getAttribute("entries");
		for(PatientEntryModel x: patient) {
			if(x.getId() == id) {
				vaccineName = x.getVaccine();
				for(VaccineListEntry z: vaccine) {
					if(z.getVaccineName().contains(vaccineName)) {
						doses = z.getDosesLeft() - 1;
						if(doses <= 0) {
							doses = 0;
						}
					}
				}
			}
		}
		
		Hw3DbService dbService = new Hw3DbService();
		dbService.recievedVaccine(id, date, vaccineName, doses);
		dbService.close();
		response.sendRedirect("PatientList");
	}
}
