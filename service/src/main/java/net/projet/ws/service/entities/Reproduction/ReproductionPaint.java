package net.projet.ws.service.entities.Reproduction;

import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "reproductionPaint")
@Entity
@Table(name="REPRODUCTIONPAINT")
public class ReproductionPaint extends Reproduction{

	@Column(name="particularityTech")
	private String _particularityTech;

	@Column(name="particularitySupport")
	private String _particularitySupport;

	@Column(name="type")
	private final String type="reproductionPaint";

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
	public String getType(){
		return this.type;
	}
}
