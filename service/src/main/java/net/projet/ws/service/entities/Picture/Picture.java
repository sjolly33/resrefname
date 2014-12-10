package net.projet.ws.service.entities.Work;

import net.projet.ws.service.entities.IMuseum;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import javax.persistence.*;

@XmlRootElement(name = "picture")
@Entity
@Inheritance
@Table(name="PICTURE")
public abstract class Picture extends IMuseum{

	@Id 
	@Column(name="PictureID", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int _pictureID;

	@Column(name="Author")
	private Author _author;

	@Column(name="Date")
	private Date _date;

	@Column(name="Resume")
	private String _resume;

	@ManyToOne(mappedBy="PictureRef")
	@Column(name="WorkRef")
	private Work _workRef;

	@XmlElement
 	public int getID() {
 		return _pictureID;
 	}

 	public void setID(int id){
 		_workID = id;
 	}

	@XmlElement
 	public List<float> getDimension() {
 		return new ArrayList(_dimension);
 	}

 	public void setDimension(List<float> dimension){
 		_dimension = new ArrayList(dimension);
 	}

 	public void setDimension(float x, float y, float z) {
 		_dimension[0] = x;
 		_dimension[1] = y;
 		_dimension[2] = z;
 	}

	@XmlElement
 	public String getAuthor(){
 		return "";
 	}

 	public void setAuthor(Author author){
 		_author = author;
 	}

 	@XmlElement
 	public String getResume(){
 		return _resume;
 	}

 	public void setResume(String resume){
 		_resume = resume;
 	}

 	@XmlElement
 	public Date getDate(){
 		return _date;
 	}

 	public void setDate(Date date){
 		_date = date;
 	}

 	@XmlElement
 	public Particularities getParticularities(){
 		return _particularities;
 	}

 	public void setParticularities(Particularities particularities){
 		_particularities = particularities;
 	}
}