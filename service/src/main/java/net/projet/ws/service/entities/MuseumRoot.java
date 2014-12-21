package net.projet.ws.service.entities;

import net.projet.ws.service.entities.Data.MuseumData;
import net.projet.ws.service.entities.Data.PictureData;
import net.projet.ws.service.entities.Data.WorkData;
import net.projet.ws.service.entities.Data.CollectionData;
import net.projet.ws.service.entities.Picture.Picture;
import net.projet.ws.service.entities.Work.Work;
import net.projet.ws.service.entities.Collection.CollectionWork;
import net.projet.ws.service.entities.Collection.CollectionPicture;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
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
	@Path("/{id}/collectionsWork")
	@Produces("application/json")
	public List<CollectionWork> getCollectionsWork(@PathParam("id") int museumID){
		Museum museum = MuseumData.getMuseum(museumID);
		return museum.getCollectionsWorks();
	}

	@GET
	@Path("/{id}/collectionsPicture")
	@Produces("application/json")
	public List<CollectionPicture> getCollectionsPicture(@PathParam("id") int museumID){
		Museum museum = MuseumData.getMuseum(museumID);
		return museum.getCollectionsPictures();
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

	@GET
	@Path("/{id}/collectionWork/{id_cWork}")
	@Produces("application/json")
	public CollectionWork getCollectionWork(@PathParam("id") int museumID, @PathParam("id_cWork") int cWorkID){
		Museum museum = MuseumData.getMuseum(museumID);
		List<CollectionWork> cWorks = museum.getCollectionsWorks();
		return CollectionData.getCollectionWork(cWorks, cWorkID);
	}

	@GET
	@Path("/{id}/collectionPicture/{id_cPicture}")
	@Produces("application/json")
	public CollectionPicture getCollectionPicture(@PathParam("id") int museumID, @PathParam("id_cPicture") int cPictureID){
		Museum museum = MuseumData.getMuseum(museumID);
		List<CollectionPicture> cPictures = museum.getCollectionsPictures();
		return CollectionData.getCollectionPicture(cPictures, cPictureID);
	}

	@POST
	@Path("/new/museum")
	@Consumes("application/json")
	public Response addMuseum(Museum museum){
		return MuseumData.addMuseum(museum);
	}

	@POST
	@Path("/new/picture")
	@Consumes("application/json")
	public Response addPicture(Picture picture){
		return PictureData.addPicture(picture);
	}

	@POST
	@Path("/new/work")
	@Consumes("application/json")
	public Response addWork(Work work){
		return WorkData.addWork(work);
	}

	@POST
	@Path("/new/collectionPicture")
	@Consumes("application/json")
	public Response addCollectionPicture(CollectionPicture collectionPicture){
		return CollectionData.addCollectionPicture(collectionPicture);
	}

	@POST
	@Path("/new/collectionWork")
	@Consumes("application/json")
	public Response addCollectionWork(CollectionWork collectionWork){
		return CollectionData.addCollectionWork(collectionWork);
	}

	@POST
	@Path("/{id_museum}/new/picture")
	@Consumes("application/json")
	public Response addPictureToMuseum(@PathParam("id_museum") int museumID, Picture picture){
		Museum museum = MuseumData.getMuseum(museumID);
		Response res = PictureData.addPicture(picture);
		List<Picture> pictures = museum.getPictures();
		pictures.add(picture);
		museum.setPictures(pictures);
		//MuseumData.addMuseum(museum);
		MuseumData.updateMuseum(museum);
		return res;
	}

	@POST
	@Path("/{id_museum}/new/work")
	@Consumes("application/json")
	public Response addWorkToMuseum(@PathParam("id_museum") int museumID, Work work){
		Museum museum = MuseumData.getMuseum(museumID);
		Response res = WorkData.addWork(work);
		List<Work> works = museum.getWorks();
		works.add(work);
		museum.setWorks(works);
		MuseumData.updateMuseum(museum);
		return res;
	}

	@POST
	@Path("/{id_museum}/new/collectionPicture")
	@Consumes("application/json")
	public Response addCollectionPictureToMuseum(@PathParam("id_museum") int museumID, CollectionPicture collectionPicture){
		Museum museum = MuseumData.getMuseum(museumID);
		Response res = CollectionData.addCollectionPicture(collectionPicture);
		List<CollectionPicture> collectionPictures = museum.getCollectionsPictures();
		collectionPictures.add(collectionPicture);
		museum.setCollectionsPictures(collectionPictures);
		MuseumData.updateMuseum(museum);
		return res;
	}

	@POST
	@Path("/{id_museum}/new/collectionWork")
	@Consumes("application/json")
	public Response addCollectionWorkToMuseum(@PathParam("id_museum") int museumID, CollectionWork collectionWork){
		Museum museum = MuseumData.getMuseum(museumID);
		Response res = CollectionData.addCollectionWork(collectionWork);
		List<CollectionWork> collectionWorks = museum.getCollectionsWorks();
		collectionWorks.add(collectionWork);
		museum.setCollectionsWorks(collectionWorks);
		MuseumData.updateMuseum(museum);
		return res;
	}

	//Pas necessaire
	@POST
	@Path("/{id_museum}/picture/{id_picture}/new/work")
	@Consumes("application/json")
	public Response addWorkToPictureToMuseum(@PathParam("id_museum") int museumID, @PathParam("id_picture") int pictureID, Work work){
		Response res = WorkData.addWork(work);
		Museum museum = MuseumData.getMuseum(museumID);
		List<Picture> pictures = museum.getPictures();

		Picture picture = PictureData.getPicture(pictures, pictureID);
		picture.setWork(work);
		picture = PictureData.updatePicture(picture);
		
		museum.setPictures(pictures);
		MuseumData.updateMuseum(museum);
		return res;
	}


	@PUT
	@Path("/update/museum")
	@Produces("application/json")
	@Consumes("application/json")
	public Museum updateMuseum(Museum museum){
		return MuseumData.updateMuseum(museum);
	}

	@PUT
	@Path("/update/picture")
	@Produces("application/json")
	@Consumes("application/json")
	public Picture updatePicture(Picture picture){
		return PictureData.updatePicture(picture);
	}

	@PUT
	@Path("/update/work")
	@Produces("application/json")
	@Consumes("application/json")
	public Work updateWork(Work work){
		return WorkData.updateWork(work);
	}

	@PUT
	@Path("/update/collectionPicture")
	@Produces("application/json")
	@Consumes("application/json")
	public CollectionPicture updateCollectionPicture(CollectionPicture cPicture){
		return CollectionData.updateCollectionPicture(cPicture);
	}

	@PUT
	@Path("/update/collectionWork")
	@Produces("application/json")
	@Consumes("application/json")
	public CollectionWork updateCollectionWork(CollectionWork cWork){
		return CollectionData.updateCollectionWork(cWork);
	}

	//rajouter des chemins dans Path si unidirectionnel sinon Front-End + @PUT
}
