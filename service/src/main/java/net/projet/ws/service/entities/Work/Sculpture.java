package net.projet.ws.service.entities.Work;
import net.projet.ws.service.entities.Picture.Picture;


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

@XmlRootElement(name = "sculpture")
@Entity
@Table(name="SCULPTURE")
public class Sculpture extends Work{

	@Id 
	@Column(name="SCULPTUREID", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int _sculptureID;

	@OneToMany(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy="work2")
	private List<Picture> _pictures = new ArrayList<Picture>();

	@Column(name="particularitySupport")
	private List<String> _particularitiesSupport;

	@XmlElement
 	public int getID() {
 		return _sculptureID;
 	}

 	public void setID(int id){
 		_sculptureID = id;
 	}

 	@XmlElement
 	public List<Picture> getPicture(){
 		return new ArrayList<Picture>(_pictures);
 	}

 	public void setPicture(List<Picture> picture){
 		_pictures = picture;
 	}
	
	public void setParticularitiesSupport(List<String> support){
		_particularitiesSupport = new ArrayList<String>(support);
	}
}
