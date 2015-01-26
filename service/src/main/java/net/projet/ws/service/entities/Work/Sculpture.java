package net.projet.ws.service.entities.Work;
import net.projet.ws.service.entities.Picture.Picture;
import net.projet.ws.service.entities.Reproduction.ReproductionSculpture;

import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "sculpture")
@Entity
@Table(name="SCULPTURE")
public class Sculpture extends Work{

	@OneToMany(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy="sculpture")
	private List<Picture> _pictures = new ArrayList<Picture>();

	@Column(name="particularitySupport")
	private List<String> _particularitiesSupport;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="Reproductions")	
	private List<ReproductionSculpture> _reproductions;

	@Column(name="type")
	private final String type = "sculpture";

 	@XmlElement
 	public List<Picture> getPictures(){
 		return new ArrayList<Picture>(_pictures);
 	}

 	public void setPictures(List<Picture> picture){
 		_pictures = picture;
 	}

 	@XmlElement
 	public List<String> getParticularitiesSupport(){
		return new ArrayList<String>(_particularitiesSupport);
	}
	
	public void setParticularitiesSupport(List<String> support){
		_particularitiesSupport = new ArrayList<String>(support);
	}

	@XmlElement
	public List<ReproductionSculpture> getReproductions(){
		return new ArrayList<ReproductionSculpture>(_reproductions);
	}

	public void setReproductions(List<ReproductionSculpture> reproductions){
		_reproductions = new ArrayList<ReproductionSculpture>(reproductions);
	}

	@XmlElement
	public String getType(){
		return this.type;
	}
}
