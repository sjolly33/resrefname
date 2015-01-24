package net.projet.ws.service.entities;

import net.projet.ws.service.entities.Collection.CollectionPicture;
import net.projet.ws.service.entities.Collection.CollectionWork;
import net.projet.ws.service.entities.Worker.Author;
import net.projet.ws.service.entities.Work.Work;
import net.projet.ws.service.entities.Work.Paint;
import net.projet.ws.service.entities.Work.Sculpture;
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
	@Column(name="Museum_ID")
	private int _museumID;
	
	@Column(name="NAME",nullable=false)
	private String _name;

	@Column(name="THEME", nullable=false)
	private String _theme;

	@Column(name="ADRESS", nullable=false)
	private String _adress;

	@Column(name="INFORMATION")
	private String _information;

	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}) //Just listing so receive all consequences
	@JoinColumn(name="MUSEUM_PICTURE_REF", referencedColumnName="Museum_ID")
	private List<Picture> _pictures;

	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}) //Just listing so receive all consequences
	@JoinColumn(name="MUSEUM_PAINT_REF", referencedColumnName="Museum_ID")
	private List<Paint> _paints;

	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}) //Just listing so receive all consequences
	@JoinColumn(name="MUSEUM_SCULPTURE_REF", referencedColumnName="Museum_ID")
	private List<Sculpture> _sculptures;

	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}) //Just listing so receive all consequences
	@JoinColumn(name="MUSEUM_CollectionPicture_REF", referencedColumnName="Museum_ID")
	private List<CollectionPicture> _picturesCollections;

	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}) //Just listing so receive all consequences
	@JoinColumn(name="MUSEUM_CollectionWork_REF", referencedColumnName="Museum_ID")
	private List<CollectionWork> _worksCollections;

	@OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy="museum")
	private List<Author> _authors;

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
 	public List<Paint> getPaints(){
 		return new ArrayList<Paint>(_paints);
 	}

 	public void setPaints(List<Paint> works){
 		_paints = new ArrayList<Paint>(works);
 	}

 	@XmlElement
 	public List<Sculpture> getSculptures(){
 		return new ArrayList<Sculpture>(_sculptures);
 	}

 	public void setSculptures(List<Sculpture> works){
 		_sculptures = new ArrayList<Sculpture>(works);
 	}

 	@XmlElement
 	public List<CollectionWork> getCollectionsWorks(){
 		return new ArrayList<CollectionWork>(_worksCollections);
 	}

 	public void setCollectionsWorks(List<CollectionWork> worksCollections){
 		_worksCollections = new ArrayList<CollectionWork>(worksCollections);
 	}

 	@XmlElement
 	public List<CollectionPicture> getCollectionsPictures(){
 		return new ArrayList<CollectionPicture>(_picturesCollections);
 	}

 	public void setCollectionsPictures(List<CollectionPicture> picturesCollections){
 		_picturesCollections = new ArrayList<CollectionPicture>(picturesCollections);
 	}

 	@XmlElement
 	public List<Author> getAuthors(){
 		return new ArrayList<Author>(_authors);
 	}

 	public void setAuthors(List<Author> authors){
 		_authors = new ArrayList<Author>(authors);
 	}
}

