package net.projet.ws.service.entities;

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
public abstract class IMuseum{

	@Column(name="TITLE")
	private String _title;

	@Column(name="DESCRIPTION")
	private String _description;

	@Column(name="COMMENT")
	private String _comment;

	@Column(name="TAGS")
	private String _tags;

	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="museum", nullable=true)
	private Museum museum;

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

 	public void setComment(String comment) {
 		this._comment = comment;
 	}

 	@XmlElement
 	public String getTags() {
 		return _tags;
 	}

 	public void setTags(String tags) {
 		this._tags = tags;
 	}

 	@XmlTransient
 	public Museum getMuseum() {
 		return museum;
 	}

 	public void setMuseum(Museum museum){
 		this.museum = museum;
 	}    	
}

