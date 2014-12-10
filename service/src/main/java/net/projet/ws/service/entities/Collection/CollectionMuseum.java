package net.projet.ws.service.entities.Collection;

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
@Inheritance
@DiscriminatorColumn(name="Type")
@Table(name="COLLECTION")
public abstract class CollectionMuseum extends IMuseum{
	
	@Id 
	@Column(name="CollectionID", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int _collectionID;

	@XmlElement
	public int getCollectionID() {
		return collectionID;
	}

	public void setCollectionID(int id){
		this.collectionID = id;	
	}
}
