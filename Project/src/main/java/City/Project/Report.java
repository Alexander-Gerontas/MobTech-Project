package City.Project;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Report")
public class Report 
{	
	@Id
    @Column(name = "report_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)    
	private int report_id;
		
	private String username;
	private String title;
	private String text;
    private String type;
    private double lat, lon; 
    private int upvotes, downvotes;
    private String time;
    private String path;
            
    public int getReport_id() 
    {
		return report_id;
	}
    
	public void setReport_id(int report_id) 
	{
		this.report_id = report_id;
	} 
	
	public String getText() 
	{
		return text;
	}
	
	public void setText(String text) 
	{
		this.text = text;
	}
	
	public String getType() 
	{
		return type;
	}
	
	public void setType(String type) 
	{
		this.type = type;
	}
	
	public double getLat() 
	{
		return lat;
	}
	
	public void setLat(double lat) 
	{
		this.lat = lat;
	}
	
	public double getLon() 
	{
		return lon;
	}
	
	public void setLon(double lon) 
	{
		this.lon = lon;
	}
	
	public int getUpvotes() 
	{
		return upvotes;
	}
	
	public void setUpvotes(int upvotes) 
	{
		this.upvotes = upvotes;
	}
	
	public int getDownvotes() 
	{
		return downvotes;
	}
	
	public void setDownvotes(int downvotes) 
	{
		this.downvotes = downvotes;
	}
	
	public String getTime() 
	{
		return time;
	}
	
	public void setTime(String time) 
	{
		this.time = time;
	}
	
	public String getPath() 
	{
		return path;
	}
	
	public void setPath(String path) 
	{
		this.path = path;
	}	
	
	public String getTitle() 
	{
		return title;
	}
	
	public void setTitle(String title) 
	{
		this.title = title;
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