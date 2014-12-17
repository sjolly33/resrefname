package net.projet.ws.service.entities;

import net.projet.ws.service.entities.Collection.CollectionMuseum;
import net.projet.ws.service.entities.Worker.Author;
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


@XmlRootElement(name = "museum")
@Entity
@Table(name="MUSEUM")
public class Museum{

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int _museumID;
	
	@Column(name="NAME",nullable=false)
	private String _name;

	@Column(name="THEME", nullable=false)
	private String _theme;

	@Column(name="ADRESS", nullable=false)
	private String _adress;

	@Column(name="INFORMATION")
	private String _information;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL) //Just listing so receive all consequences
	@JoinColumn(name="PICTURES")
	private List<Picture> _pictures;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL) //Just listing so receive all consequences
	@JoinColumn(name="WORKS")
	private List<Picture> _works;

	@XmlElement
	public int getID(){
		return _museumID;
	}

	public void setID(int id){
		_museumID = id;
	}

	@XmlElement
 	public String getName() {
 		return _name;
 	}

 	public void setName(String name) {
 		this._name = name;
 	}

 	@XmlElement
 	public String getTheme(){
 		return _theme;
 	}

 	public void setTheme(String theme){
 		_theme = theme;
 	}

	@XmlElement
 	public String getInformation() {
 		return _information;
 	}

 	public void setInformation(String information) {
 		this._information = information;
 	}

 	@XmlElement
 	public String getAdress(){
 		return _adress;
 	}

 	public void setAdress(String adress){
 		_adress = adress;
 	}

 	@XmlElement
 	public List<Picture> getPictures(){
 		return new ArrayList<Picture>(_pictures);
 	}

 	public void setPictures(List<Picture> pictures){
 		_pictures = new ArrayList<Picture>(pictures);
 	}

 	@XmlElement
 	public List<Work> getWorks(){
 		return new ArrayList<Work>(_works);
 	}

 	public void setWorks(List<Work> works){
 		_works = new ArrayList<Work>(works);
 	}
}

