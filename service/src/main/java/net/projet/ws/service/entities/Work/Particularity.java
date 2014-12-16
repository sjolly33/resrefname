package net.projet.ws.service.entities.Work;

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

@XmlRootElement(name = "particularity")
@Entity
@Inheritance
@DiscriminatorColumn(name="Type")
@Table(name="PARTICULARITY")
public abstract class Particularity{
	
	@Id
	@Column(name="ParticularID", nullable=false) 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int _particularID;

	@Column(name="name")
	private String _name;

	@Column(name="information")
	private String _information;

	@XmlElement
	public int getID(){
		return _particularID;
	}

	public void setID(int id){
		_particularID = id;
	}

	@XmlElement
	public String getName(){
		return _name;
	}

	public void setName(String name){
		_name = name;
	}

	@XmlElement
	public String getInformation(){
		return _information;
	}

	public void setInformation(String information){
		_information = information;
	}
}