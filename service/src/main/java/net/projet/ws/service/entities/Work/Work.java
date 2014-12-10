package net.projet.ws.service.entities.Work;

import net.projet.ws.service.entities.IMuseum;
import net.projet.ws.service.entities.Picture.Picture;
import net.projet.ws.service.entities.Worker.Author;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
	private List<Float> _dimension = new ArrayList<Float>(3);

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "AuthorRef")
	private Author _authorRef;

	@Column(name="Date")
	private Date _date;

	@Column(name="Resume")
	private String _resume;

	@OneToMany(mappedBy="_workRef")
	@Column(name="PictureRef")
	private List<Picture> _pictures;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="WORK_PARTICULARITY", joinColumns=@JoinColumn(name="workID", referencedColumnName="workID"), inverseJoinColumns=@JoinColumn(name="ParticularID", referencedColumnName="ParticularID"))
	@Column(name="Particularities")
	protected List<Particularity> _particularities;

	@XmlElement
 	public int getID() {
 		return _workID;
 	}

 	public void setID(int id){
 		_workID = id;
 	}

	@XmlElement
 	public List<Float> getDimension() {
 		return new ArrayList(_dimension);
 	}

 	public void setDimension(List<Float> dimension){
 		_dimension = new ArrayList(dimension);
 	}

 	public void setDimension(Float x, Float y, Float z) {
 		_dimension.set(0,x);
 		_dimension.set(1,y);
 		_dimension.set(2,z);
 	}

	@XmlElement
	public Author getAuthor(){
		return _authorRef;
	}

	public void setAuthor(Author author){
		_authorRef = author;
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
 	public List<Particularity> getParticularities(){
 		return new ArrayList<Particularity>(_particularities);
 	}

 	public void setParticularities(List<Particularity> particularities){
 		_particularities = new ArrayList<Particularity>(particularities);
 	}

 	@XmlElement
 	public List<Picture> getPicture(){
 		return new ArrayList<Picture>(_pictures);
 	}

 	public void setPicture(List<Picture> picture){
 		_pictures = picture;
 	}
}