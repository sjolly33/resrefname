package net.projet.ws.service.entities.Work;
import net.projet.ws.service.entities.Picture.Picture;
import net.projet.ws.service.entities.Reproduction.ReproductionPaint;

import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "paint")
@Entity
@Table(name="PAINT")
public class Paint extends Work{

	@OneToMany(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy="paint")
	private List<Picture> _pictures = new ArrayList<Picture>();

	@Column(name="particularityTech")
	private String _particularityTech;

	@Column(name="particularitySupport")
	private String _particularitySupport;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="Reproductions")
	private List<ReproductionPaint> _reproductions;

	@Column(name="type")
	private final String type = "paint";

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

	@XmlElement
	public String getType(){
		return this.type;
	}
}
