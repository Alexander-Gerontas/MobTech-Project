package City.Project;

import java.util.ArrayList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Path("/vote")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VoteService {
	private Session session;

	public VoteService() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
	}

	@PUT
    public Response vote(Vote vote)    
	{
    	ArrayList<Vote> voteList = new ArrayList<>();
    	ArrayList<Report> reportList = new ArrayList<>();
    	boolean flag = false;
    	
		session.beginTransaction();
		voteList = (ArrayList<Vote>) session.createQuery("from Vote").list();
				
		Vote previousVote = new Vote();
		
		// Έλεγχος αν ο χρήστης έχει ήδη ψηφίσει στην αναφορά.
		for (Vote someVote: voteList)
		{
			if (someVote.getUsername().equals(vote.getUsername())) 
			{
				if (someVote.getReport_id() == vote.getReport_id()) 
				{
					flag = true;
					
					previousVote = someVote;					
			        break;			        
				}					
			}			
		}
							
		reportList = (ArrayList<Report>) session.createQuery("from Report").list();
		
		for (Report report: reportList)
		{
			if (report.getReport_id() == vote.getReport_id()) 
			{		
				// Αν ο χρήστης δεν έχει ψηφίσει στην αναφορά:
				if (!flag)
				{
					// Ενημέρωση του αριθμού των ψήφων στην αναφορά.
					if (vote.getVote() == 'u') report.setUpvotes(report.getUpvotes() + 1);
					else if (vote.getVote() == 'd') report.setDownvotes(report.getDownvotes() + 1);
					
					// Αποθήκευση του αντικειμένου vote στην βάση.
					session.save(vote);
				}	
							 
				if (flag) 
				{
					// Αν ο χρήστης είχε ψηφίσει στην αναφορά και θέλει να κάνει αναίρεση (πχ να είχε κάνει upvote και να ξαναπατήσει upvote για να το αναιρέσει):
					if (previousVote.getVote() == vote.getVote()) 
					{
						// Αφαιρούμε μία ψήφο από το σύνολο των ψήφων στην αναφορά αναλόγως με το τι είχε ψηφίσει.
						if (previousVote.getVote() == 'u') report.setUpvotes(report.getUpvotes() - 1);
						if (previousVote.getVote() == 'd') report.setDownvotes(report.getDownvotes() - 1);
						
						// Διαγράφουμε το vote από την βάση.
						session.delete(previousVote);				
					}
					
					// Αν ο χρήστης είχε ψηφίσει στην αναφορά και θέλει να αλλάξει την ψήφο του τότε: 
					else if (previousVote.getVote() != vote.getVote())
					{
						// Αν είχε κάνει υπερψήφιση προσθέτουμε μία ψήφο στα κατά και αφαιρούμε μία από τα υπέρ.
						if (previousVote.getVote() == 'u') 
						{
							report.setUpvotes(report.getUpvotes() - 1);
							report.setDownvotes(report.getDownvotes() + 1);
						}
						
						// Αν είχε κάνει καταψήφιση προσθέτουμε μία ψήφο στα υπερ και αφαιρούμε μία από τα κατά.
						else if (previousVote.getVote() == 'd')
						{
							report.setUpvotes(report.getUpvotes() + 1);
							report.setDownvotes(report.getDownvotes() - 1);							
						}		
						
						// Ενημερώνουμε το παλιό vote που είχε κάνει.						
						previousVote.setVote(vote.getVote());						
						session.update(previousVote);
					}				
				}						
				
				// Ενημερώνουμε την αναφορά στην βάση.	
				session.update(report);							
			}
		}		
						
		session.getTransaction().commit();    
		session.close();
		
        return Response.status(Response.Status.OK).build();
	}
}