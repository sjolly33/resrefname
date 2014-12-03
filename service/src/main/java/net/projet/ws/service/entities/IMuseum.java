package net.projet.ws.service.entities;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import javax.persistence.*;

@MappedSuperclass
public abstract class IMuseum{
	
	@Column(name="TITLE",nullable=false)
	private String title;

	@Column(name="DESCRIPTION",nullable=false)
	private String description;

	@XmlElement
	public int getID(){
		return _elementID;
	}

	public void setID(int id){
		_elementID = id;
	}

	@XmlElement
 	public String getTitle() {
 		return title;
 	}

 	public void setTitle(String title) {
 		this.title = title;
 	}

	@XmlElement
 	public String getDescription() {
 		return description;
 	}

 	public void setDescription(String description) {
 		this.description = description;
 	}
}

