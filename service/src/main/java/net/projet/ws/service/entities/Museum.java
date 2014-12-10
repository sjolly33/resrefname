package net.projet.ws.service.entities;

import net.projet.ws.service.entities.Collection.CollectionMuseum;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
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

	@OneToMany(fetch=FetchType.EAGER)
	@Column(name="ELEMENT")
	private List<IMuseum> _elements;

	@OneToMany(fetch=FetchType.EAGER)
	@Column(name="AUTHOR")
	private List<Author> _authors;

	@XmlElement
	public List<Author> getAuthors(){
		return new ArrayList(_authors);
	}

	public void setAuthors(List<Author> author){
		_authors = new ArrayList(author);
	}

	@XmlElement
	public List<IMuseum> getElements(){
		return new ArrayList(_elements);
	}

	public void setElements(List<IMuseum> elements){
		_elements = new ArrayList(elements);
	}

	@XmlElement
	public int getID(){
		return _museumID;
	}

	public void setID(int id){
		_museumID = id;
	}

	@XmlElement
 	public String getName() {
 		return name;
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

