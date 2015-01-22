package net.projet.ws.service.entities.Reproduction;

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

@XmlRootElement(name = "reproductionPaint")
@Entity
@Table(name="REPRODUCTIONPAINT")
public class ReproductionPaint extends Reproduction{

	@Id 
	@Column(name="REPRODUCTION_PAINTID", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int _reproductionID;

	@Column(name="particularityTech")
	private String _particularityTech;

	@Column(name="particularitySupport")
	private String _particularitySupport;

	@XmlElement
 	public int getID() {
 		return _reproductionID;
 	}

 	public void setID(int id){
 		_reproductionID = id;
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
}
