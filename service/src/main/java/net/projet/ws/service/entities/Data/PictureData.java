package net.projet.ws.service.entities.Data;

import net.projet.ws.service.entities.Picture.Picture;
import net.projet.ws.service.filters.JpaUtil;

import javax.ws.rs.core.Response;

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

public class PictureData{
	private static List<Picture> pictures = new ArrayList<Picture>();
	
	private static final Logger LOG = Logger.getLogger(PictureData.class);

	public static List<Picture> initPictures(){
		LOG.info("initPicture");
		Picture picture1 = new Picture();
		picture1.setTitle("THE photo");
		picture1.setDescription("Photo en souvenir de qqchose");
		picture1.setResume("Une super photo representant rien, blabla");
		pictures.add(picture1);
		Picture picture2 = new Picture();
		pictures.add(picture2);
		return new ArrayList<Picture>(pictures);
	}

	public static List<Picture> getPictures(){
		LOG.info("getPictures");
		return new ArrayList<Picture>(pictures);
	}

	public static Picture getPicture(int id){
		LOG.info("getPicture");
		for(int i=0;i<pictures.size();++i){
			if(pictures.get(i).getID() == id)
				return pictures.get(i);
		}
		return pictures.get(0);
	}
}
