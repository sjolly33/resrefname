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

@XmlRootElement(name = "author")
@Entity
@Table(name="AUTHOR")
public class Author{

	@Id 
	@Column(name="AuthorID", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int _authorID;

	@Column(name="name")
	private String _name;

	@Column(name="adress")
	private String _adress;

	@OneToMany(mappedBy="AuthorRef")
	@Column(name="WorkRef")
	private List<Work> _worksRef;

	@XmlElement
 	public int getID() {
 		return _authorID;
 	}

 	public void setID(int id){
 		_authorID = id;
 	}

	@XmlElement
 	public String getName() {
 		return _name;
 	}

 	public void setID(String name){
 		_name = name;
 	}

	@XmlElement
 	public int getAdress() {
 		return _adress;
 	}

 	public void setAdress(String adress){
 		_adress = adress;
 	}

 	@XmlElement
 	public List<Work> getWorks() {
 		return _worksRef;
 	}

 	public void setWorks(List<Work> works){
 		_worksRef = works;
 	}  	
}