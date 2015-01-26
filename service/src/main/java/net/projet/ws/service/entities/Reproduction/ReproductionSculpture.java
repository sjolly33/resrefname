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

@XmlRootElement(name = "reproductionSculpture")
@Entity
@Table(name="REPRODUCTIONSCULPTURE")
public class ReproductionSculpture extends Reproduction{

	@Column(name="particularitySupport")
	private List<String> _particularitiesSupport;
	
	@Column(name="type")
	private final String type="reproductionSculpture";
	
	@XmlElement
	public List<String> getParticularitySupport(){
		return new ArrayList<String>(_particularitiesSupport);
	}

	public void setParticularitiesSupport(List<String> support){
		_particularitiesSupport = new ArrayList<String>(support);
	}

	@XmlElement
	public String getType(){
		return this.type;
	}	
}
