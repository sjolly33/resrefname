package net.projet.ws.service.entities.Worker;

import net.projet.ws.service.entities.IMuseum;
import net.projet.ws.service.entities.Museum;
import net.projet.ws.service.entities.Work.Work;
import net.projet.ws.service.entities.Work.Paint;
import net.projet.ws.service.entities.Work.Sculpture;

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
/*
	@OneToMany(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy="author")
	private List<Paint> _paintsRef = new ArrayList<Paint>();

	@OneToMany(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy="author")
	private List<Sculpture> _SculpturesRef = new ArrayList<Sculpture>();
*/
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="museum", nullable=true)
	private Museum museum;

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

 	public void setName(String name){
 		_name = name;
 	}

	@XmlElement
 	public String getAdress() {
 		return _adress;
 	}

 	public void setAdress(String adress){
 		_adress = adress;
 	}
/*
 	@XmlElement
 	public List<Paint> getPaints() {
 		return _paintsRef;
 	}

 	public void setPaints(List<Paint> works){
 		_paintsRef = works;
 	} 

 	@XmlElement
 	public List<Sculpture> getSculptures() {
 		return _SculpturesRef;
 	}

 	public void setSculptures(List<Sculpture> works){
 		_SculpturesRef = works;
 	}
*/
	@XmlTransient
 	public Museum getMuseum() {
 		return museum;
 	}

 	public void setMuseum(Museum museum){
 		this.museum = museum;
 	}    	
}