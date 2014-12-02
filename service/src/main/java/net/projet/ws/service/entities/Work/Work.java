package net.projet.ws.service.entities.Work;

import net.projet.ws.service.entities.IMuseum;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import javax.persistence.*;

@XmlRootElement(name = "work")
@Entity
@Table(name="WORK")
public class Work extends IMuseum(){
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int workID;

	@Column(name="TITLE",nullable=false)
	private String title;

	@Column(name="DESCRIPTION",nullable=false)
	private String description;

	@Column(name="RefMuseum", nullable=false)
	private IMuseum ref;

	@XmlElement
	public int getCollectionID() {
		return collectionID;
	}

	public void setCollectionID(int id){
		this.collectionID = id;	
	}

	@XmlElement
 	public String getDescription() {
 		return description;
 	}

 	public void setDescription(String description) {
 		this.description = description;
 	}

 	@XmlElement
 	public IMuseum getRefMuseum() {
 		return ref;
 	}

 	public void setRefMuseum(IMuseum ref) {
 		this.ref = ref;
 	}
}