package net.projet.ws.service.entities.Picture;

import net.projet.ws.service.entities.IMuseum;
import net.projet.ws.service.entities.Work.Work;
import net.projet.ws.service.entities.Collection.CollectionPicture;

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

@XmlRootElement(name = "picture")
@Entity
@Inheritance
@Table(name="PICTURE")
public class Picture extends IMuseum{

	@Id 
	@Column(name="PictureID", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int _pictureID;

	@Temporal(TemporalType.DATE)
	private Date _date;

	@Column(name="Resume")
	private String _resume;
/*
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "WorkRef")
	private Work _workRef;
*/

	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  	@JoinColumn(name="Collection", nullable=true)
  	private CollectionPicture _collection;

	@XmlElement
 	public int getID() {
 		return _pictureID;
 	}

 	public void setID(int id){
 		_pictureID = id;
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
 	public CollectionPicture getCollection(){
 		return _collection;
 	}
}