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
	
	@Id 
	@Column(name="ElementID", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int _elementID;

	@Column(name="TITLE",nullable=false)
	private String _title;

	@Column(name="DESCRIPTION",nullable=false)
	private String _description;

	@Column(name="COMMENT")
	private String _comment;

	@Column(name="TAGS")
	private String _tags;

	@XmlElement
 	public int getElement() {
 		return _elementID;
 	}

 	public void setElement(int element) {
 		this._elementID = element;
 	}

	@XmlElement
 	public String getTitle() {
 		return _title;
 	}

 	public void setTitle(String title) {
 		this._title = title;
 	}

	@XmlElement
 	public String getDescription() {
 		return _description;
 	}

 	public void setDescription(String description) {
 		this._description = description;
 	}

	@XmlElement
 	public String getComment() {
 		return _comment;
 	}

 	public void setDescription(String comment) {
 		this._comment = comment;
 	}

 	@XmlElement
 	public String getTags() {
 		return _tags;
 	}

 	public void setTags(String tags) {
 		this._tags = tags;
 	}
}

