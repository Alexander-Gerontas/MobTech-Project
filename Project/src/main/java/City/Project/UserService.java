package City.Project;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserService 
{
	private Session session;
	
	public UserService() 
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
	}
		
	// Ελέγχος των στοιχείων που έδωσε ο χρήστης για να κάνει login, και αν είναι σωστά επιστρέφουμε το είδος του χρήστη (πολίτης / δημοτικός υπάλληλος).
	@GET
	@Path("{username}/{password}")
	public Response login(@PathParam("username") String username, @PathParam("password") String password) 
	{
		System.out.println("checking user password");
		
		String userType;
		ArrayList<User> userList = new ArrayList<>();

		session.beginTransaction();

		userList = (ArrayList<User>) session.createQuery("from User").list();
		session.getTransaction().commit();
		
		for (User user: userList) {
			if (user.getUsername().equals(username)) {
				if (user.getPassword().equals(password)) {
					userType = user.getType();
					session.close();
					return Response.status(Response.Status.OK).entity(userType).build();
				}
			}
		}

		session.close();
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	// Έλεγχος αν το username είναι δεσμευμένο.
	@GET
	@Path("{username}")
	public Response checkUser(@PathParam("username") String username)
	{		
		String userType;
		ArrayList<User> userList = new ArrayList<>();

		session.beginTransaction();

		userList = (ArrayList<User>) session.createQuery("from User").list();
		session.getTransaction().commit();
		
		for (User user: userList) 
		{
			if (user.getUsername().equals(username)) 
			{					
				session.close();
				return Response.status(Response.Status.BAD_REQUEST).build();	
			}
		}
		
		session.close();
		return Response.status(Response.Status.OK).build();	
	}
	
	// Δημιουργία νέου χρήστη
	@POST
	public Response createUser(User user)
	{							
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		
		session.close();
		return Response.status(Response.Status.OK).build();
	}	
}
