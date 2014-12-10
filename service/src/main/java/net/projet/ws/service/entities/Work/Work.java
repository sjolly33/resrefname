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

@XmlRootElement(name = "work")
@Entity
@Inheritance
@DiscriminatorColumn(name="WorkType")
@Table(name="WORK")
public abstract class Work extends IMuseum{

	@Id 
	@Column(name="WorkID", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int _workID;

	@Column(name="Dimension")
	private List<float> _dimension = new ArrayList<float>(3);

	@ManyToOne(fetch=FetchType.EAGER)
	@Column(name="AuthorRef")
	private Author _author;

	@Column(name="Date")
	private Date _date;

	@Column(name="Resume")
	private String _resume;

	@OneToMany(fetch=FetchType.EAGER)
	@Column(name="PictureRef")
	private List<Picture> _pictures;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="WORK_PARTICULARITY", joinColumns=@JoinColumn(name="workID", referencedColumnName="workID"), inverseJoinColumns=@JoinColumn(name="ParticularID", referencedColumnName="ParticularID"))
	@Column(name="Particularities")
	protected List<Particularities> _particularities;

	@XmlElement
 	public int getID() {
 		return _workID;
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
	public Author getAuthor(){
		return _author;
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
 	public List<Particularities> getParticularities(){
 		return new ArrayList<Particularities>(_particularities);
 	}

 	public void setParticularities(List<Particularities> particularities){
 		_particularities = new ArrayList<Particularities>(particularities);
 	}

 	@XmlElement
 	public List<Picture> getPicture(){
 		return new ArrayList<Picture>(_pictures);
 	}

 	public void setPicture(List<Picture> picture){
 		_picture = picture;
 	}
}