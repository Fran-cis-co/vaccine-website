package cs3220.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.service.Hw3DbService;

@WebServlet("/NewDoses")
public class NewDoses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NewDoses() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("/WEB-INF/NewDoses.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dosesRecieved = request.getParameter("totalDosesRecieved");
		String vaccineName = request.getParameter("vaccine");
		
		Hw3DbService dbService = new Hw3DbService();
		dbService.addDoses(vaccineName, Integer.parseInt(dosesRecieved));
		response.sendRedirect("VaccineList");		
	}

}
