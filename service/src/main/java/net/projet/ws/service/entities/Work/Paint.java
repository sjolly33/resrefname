package net.projet.ws.service.entities.Work;
import net.projet.ws.service.entities.Picture.Picture;
import net.projet.ws.service.entities.Reproduction.ReproductionPaint;

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

@XmlRootElement(name = "paint")
@Entity
@Table(name="PAINT")
public class Paint extends Work{

	@Id 
	@Column(name="PAINTID", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int _paintID;

	@OneToMany(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy="paint")
	private List<Picture> _pictures = new ArrayList<Picture>();

	@Column(name="particularityTech")
	private String _particularityTech;

	@Column(name="particularitySupport")
	private String _particularitySupport;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="Reproductions")
	private List<ReproductionPaint> _reproductions;

	@XmlElement
 	public int getID() {
 		return _paintID;
 	}

 	public void setID(int id){
 		_paintID = id;
 	}

 	@XmlElement
 	public List<Picture> getPictures(){
 		return new ArrayList<Picture>(_pictures);
 	}

 	public void setPictures(List<Picture> picture){
 		_pictures = picture;
 	}

 	@XmlElement
 	public String getParticularityTech(){
		return _particularityTech;
	}
	
	@XmlElement
	public String getParticularitySupport(){
		return _particularitySupport;
	}

	public void setParticularityTech(String tech){
		_particularityTech = tech;
	}
	
	public void setParticularitySupport(String support){
		_particularitySupport = support;
	}

	@XmlElement
	public List<ReproductionPaint> getReproductions(){
		return new ArrayList<ReproductionPaint>(_reproductions);
	}

	public void setReproductions(List<ReproductionPaint> reproductions){
		_reproductions = new ArrayList<ReproductionPaint>(reproductions);
	}
}
