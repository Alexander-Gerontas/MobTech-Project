package City.Project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vote 
{	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;
	private int report_id;
	private char vote;
	
	public int getReport_id() 
	{
		return report_id;
	}
	
	public void setReport_id(int report_id) 
	{
		this.report_id = report_id;
	}
	
	public char getVote() 
	{
		return vote;
	}
	
	public void setVote(char vote) 
	{
		this.vote = vote;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public String getUsername() 
	{
		return username;
	}
	
	public void setUsername(String username) 
	{
		this.username = username;
	}
}