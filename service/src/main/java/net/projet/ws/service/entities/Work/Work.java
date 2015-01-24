package net.projet.ws.service.entities.Work;

import net.projet.ws.service.entities.IMuseum;
import net.projet.ws.service.entities.Museum;
import net.projet.ws.service.entities.Picture.Picture;
import net.projet.ws.service.entities.Collection.CollectionWork;
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
import javax.xml.bind.annotation.XmlTransient;


@MappedSuperclass
public abstract class Work extends IMuseum{

	@Column(name="Dimension")
	private List<Float> _dimension = new ArrayList<Float>(3);
/*
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "author")
	private Author author;
*/	

	@Temporal(TemporalType.DATE)
	private Date _date;

	@Column(name="Resume")
	private String _resume;

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
/*
	@XmlElement
	public Author getAuthor(){
		return author;
	}

	public void setAuthor(Author author){
		this.author = author;
	}
*/
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
}