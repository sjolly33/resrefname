package net.projet.ws.service.entities.Test;

import net.projet.ws.service.entities.IMuseum;

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

@XmlRootElement(name = "elementTest")
@Entity
@DiscriminatorValue("test")
@Table(name="ELEMENTS")
public class ElementTest extends IMuseum{

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int _element;
	
	@Column(name="NAME")
	private String _name;

	@Column(name="THEME")
	private String _theme;

	@Column(name="ADRESS")
	private String _adress;

	@Column(name="INFORMATION")
	private String _information;

	@XmlElement
	public int getID(){
		return _element;
	}

	public void setID(int id){
		_element = id;
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
}
