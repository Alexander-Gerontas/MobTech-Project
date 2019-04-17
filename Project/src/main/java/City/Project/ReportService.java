package City.Project;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Path("/report")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReportService {
	private Session session;
	
	public ReportService() 
	{		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
	}

	// Δημιουργία νέας αναφοράς.
	@POST
	public Response createReport(Report report)
	{
		session.beginTransaction();
		session.save(report);
		session.getTransaction().commit();
		
		session.close();
		return Response.status(Response.Status.OK).build();
	}
		
	// Επιστρέφει όλες τις αναφορές στο frontend.
	@GET
	public Response getReportList()
	{
		ArrayList<Report> reportList = new ArrayList<>();
		
		session.beginTransaction();

		reportList = (ArrayList<Report>) session.createQuery("from Report").list();
		session.getTransaction().commit();
		
		GenericEntity<ArrayList> listEntity = new GenericEntity<ArrayList>(reportList, ArrayList.class);
		
		session.close();
		return Response.status(Response.Status.OK).entity(listEntity).build();
	}
	
	// Επιστρέφει μία αναφορά με βάση το id της.
	@GET
	@Path("{report_id}")
	public Response getReport(@PathParam("report_id") int report_id )
	{
		ArrayList<Report> reportList = new ArrayList<>();
		
		session.beginTransaction();

		reportList = (ArrayList<Report>) session.createQuery("from Report").list();
		session.getTransaction().commit();
		
		Report someReport; 
		
		for (Report report: reportList) 
		{
			if (report.getReport_id() == report_id) 
			{					
				session.close();
												
				someReport = report;				
				reportList.clear();
				reportList.add(someReport);
				
				GenericEntity<ArrayList> listEntity = new GenericEntity<ArrayList>(reportList, ArrayList.class);
				
				return Response.status(Response.Status.OK).entity(listEntity).build();									
			}
		}
				
		session.close();
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
}