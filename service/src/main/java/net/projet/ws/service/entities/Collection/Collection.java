package net.projet.ws.service.entities.Collection;
import net.projet.ws.service.entities.IMuseum;
import net.projet.ws.service.entities.Museum;
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
import javax.xml.bind.annotation.XmlTransient;

@MappedSuperclass
public abstract class Collection extends IMuseum{

	@Id 
	@Column(name="CollectionID", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int _collectionID;

	@XmlElement
 	public int getID() {
 		return _collectionID;
 	}

 	public void setID(int id){
 		_collectionID = id;
 	}
 }