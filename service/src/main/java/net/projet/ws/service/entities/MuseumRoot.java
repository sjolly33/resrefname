package net.projet.ws.service.entities;

import net.projet.ws.service.entities.Data.MuseumData;
import net.projet.ws.service.entities.Data.PictureData;
import net.projet.ws.service.entities.Data.WorkData;
import net.projet.ws.service.entities.Picture.Picture;
import net.projet.ws.service.entities.Work.Work;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

import javax.ws.rs.core.Response;


@Path("/museum")

public class MuseumRoot{
	
	@GET
	@Path("/museums")
	@Produces("application/json")
	public List<Museum> listMuseums(){
		return MuseumData.getMuseums();
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Museum getMuseum(@PathParam("id") int museumID){
		return MuseumData.getMuseum(museumID);
	}

	@GET
	@Path("/{id}/pictures")
	@Produces("application/json")
	public List<Picture> getPictures(@PathParam("id") int museumID){
		return (MuseumData.getMuseum(museumID)).getPictures();
	}

	@GET
	@Path("/{id_museum}/picture/{id_picture}")
	@Produces("application/json")
	public Picture getPicture(@PathParam("id_museum") int museumID, @PathParam("id_picture") int pictureID){
		Museum museum = MuseumData.getMuseum(museumID);
		List<Picture> pictures = museum.getPictures();
		return PictureData.getPicture(pictures, pictureID);
	}

	@GET
	@Path("/{id}/works")
	@Produces("application/json")
	public List<Work> getWorks(@PathParam("id") int museumID){
		Museum museum = MuseumData.getMuseum(museumID);
		return museum.getWorks();
	}

	@GET
	@Path("/{id_museum}/work/{id_work}")
	@Produces("application/json")
	public Work getWork(@PathParam("id_museum") int museumID, @PathParam("id_work") int workID){
		Museum museum = MuseumData.getMuseum(museumID);
		List<Work> works = museum.getWorks();
		return WorkData.getWork(works, workID);
	}

	@GET
	@Path("/{id_museum}/picture/{id_picture}/work")
	@Produces("application/json")
	public Work getWorkByPicture(@PathParam("id_museum") int museumID, @PathParam("id_picture") int pictureID){
		Museum museum = MuseumData.getMuseum(museumID);
		List<Picture> pictures = museum.getPictures();
		Picture picture = PictureData.getPicture(pictures, pictureID);
		return picture.getWork();
	}

	@POST
	@Path("/new/museum")
	@Consumes("application/json")
	public Response addMuseum(Museum museum){
		return MuseumData.addMuseum(museum);
	}

	@POST
	@Path("{id_museum}/new/picture")
	@Consumes("application/json")
	public Response addPicture(@PathParam("id_museum") int museumID, Picture picture){
		Museum museum = MuseumData.getMuseum(museumID);
		Response res = PictureData.addPicture(picture);
		List<Picture> pictures = museum.getPictures();
		pictures.add(picture);
		museum.setPictures(pictures);
		MuseumData.addMuseum(museum);
		return res;
	}

	@POST
	@Path("{id_museum}/new/work")
	@Consumes("application/json")
	public Response addWork(@PathParam("id_museum") int museumID, Work work){
		Museum museum = MuseumData.getMuseum(museumID);
		Response res = WorkData.addWork(work);
		List<Work> works = museum.getWorks();
		works.add(work);
		museum.setWorks(works);
		MuseumData.addMuseum(museum);
		return res;
	}
/*
	@PUT
	@Path("{id_museum}/picture/{id_picture}/new/work")
	@Consumes("application/json")
	public Response addWorkToPicture(@PathParam("id_museum") int museumID, @PathParam("id_picture") int pictureID, Work work){
		Museum museum = MuseumData.getMuseum(museumID);
		List<Picture> pictures = museum.getPictures();
		Picture picture = new Picture();
		picture = PictureData.getPicture(pictures, pictureID);
		pictures.remove(picture);

		Response res = WorkData.addWork(work);
		picture.setWork(work);
		
		picture = PictureData.update(picture);
		pictures.add(picture);
		museum.setPictures(pictures);
		MuseumData.addMuseum(museum);
		return res;
	}*/
}
