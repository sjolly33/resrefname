package net.projet.ws.service.entities.Collection;

import net.projet.ws.service.entities.Work;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import javax.persistence.*;

@XmlRootElement(name = "collection")
@Entity
@Table(name="COLLECTION")
public class CollectionMuseum extends IMuseum{
	
	@Id 
	@Column(name="CollectionID", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int _collectionID;

	@Column(name="RefMuseum", nullable=false)
	private IMuseum _ref;

	@XmlElement
	public int getCollectionID() {
		return collectionID;
	}

	public void setCollectionID(int id){
		this.collectionID = id;	
	}

 	@XmlElement
 	public IMuseum getRefMuseum() {
 		return ref;
 	}

 	public void setRefMuseum(IMuseum ref) {
 		this.ref = ref;
 	}
}
