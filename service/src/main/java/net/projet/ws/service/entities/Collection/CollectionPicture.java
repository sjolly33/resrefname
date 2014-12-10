package net.projet.ws.service.entities.Collection;

import net.projet.ws.service.entities.Picture;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import javax.persistence.*;

@XmlRootElement(name = "collectionPicture")
@Entity
@DiscriminatorValue("Type")
@Table(name="COLLECTIONPICTURE")
public class CollectionPicture extends CollectionMuseum{

	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(mappedBy="PictureID",targetEntity=Order.class, fetch=FetchType.EAGER)
	@Column(name="PictureID", nullable=false)
	private List<Picture> _pictures;

 	@XmlElement
 	public List<Picture> getRefPicture() {
 		return new ArrayList(_pictures);
 	}

 	public void setRefPicture(List<Picture> pictures) {
 		this._pictures = new ArrayList(pictures);
 	}
}
