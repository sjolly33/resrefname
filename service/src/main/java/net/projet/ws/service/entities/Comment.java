package net.projet.ws.service.entities;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import javax.persistence.*;

@XmlRootElement(name = "comment")
@Entity
@Table(name="COMMENT")
public class Comment{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int commentID;

	@Column(name="DESCRIPTION",nullable=false)
	private String description;
	
	@Column(name="RefMuseum",nullable=false)
	private IMuseum ref;

	@XmlElement
	public int getCommentID() {
		return commentID;
	}

	public void setCommentID(int id){
		this.commentID = id;	
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
