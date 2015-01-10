package net.projet.ws.service.entities;

import net.projet.ws.service.entities.Data.MuseumData;
import net.projet.ws.service.entities.Data.PictureData;
import net.projet.ws.service.entities.Data.WorkData;
import net.projet.ws.service.entities.Data.AuthorData;
import net.projet.ws.service.entities.Data.CollectionData;
import net.projet.ws.service.entities.Picture.Picture;
import net.projet.ws.service.entities.Work.Work;
import net.projet.ws.service.entities.Work.Paint;
import net.projet.ws.service.entities.Work.Sculpture;
import net.projet.ws.service.entities.Worker.Author;
import net.projet.ws.service.entities.Collection.CollectionWork;
import net.projet.ws.service.entities.Collection.CollectionPicture;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
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

	// GET to find entity. For a simplified uses, direct association to museum has been implemented
	
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
	public List<Paint> getWorks(@PathParam("id") int museumID){
		Museum museum = MuseumData.getMuseum(museumID);
		return museum.getPaints();
	}

	@GET
	@Path("/{id}/authors")
	@Produces("application/json")
	public List<Author> getAuthors(@PathParam("id") int museumID){
		Museum museum = MuseumData.getMuseum(museumID);
		return museum.getAuthors();
	}

	@GET
	@Path("/{id}/author/{id_author}")
	@Produces("application/json")
	public Author getAuthor(@PathParam("id") int museumID, @PathParam("id") int authorID){
		Museum museum = MuseumData.getMuseum(museumID);
		List<Author> authors = museum.getAuthors();
		return AuthorData.getAuthor(authors, authorID);
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
	public Paint getWork(@PathParam("id_museum") int museumID, @PathParam("id_work") int workID){
		Museum museum = MuseumData.getMuseum(museumID);
		List<Paint> works = museum.getPaints();
		return WorkData.getPaint(works, workID);
	}

	@GET
	@Path("/{id_museum}/picture/{id_picture}/work")
	@Produces("application/json")
	public Paint getWorkByPicture(@PathParam("id_museum") int museumID, @PathParam("id_picture") int pictureID){
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



///////////////////////////////////////////////////////////////////////////

	// POST to create entity. For a simplified uses, direct association to museum has been implemented


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
	public Response addWork(Paint work){
		return WorkData.addPaint(work);
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
	@Path("/new/author")
	@Consumes("application/json")
	public Response addAuthor(Author author){
		return AuthorData.addAuthor(author);
	}

	@POST
	@Path("{id_museum}/new/author")
	@Consumes("application/json")
	public Response addAuthorToMuseum(@PathParam("id_museum") int museumID, Author author){
		Museum museum = MuseumData.getMuseum(museumID);
		Response res = AuthorData.addAuthor(author);
		List<Author> authors = museum.getAuthors();
		authors.add(author);
		museum.setAuthors(authors);
		MuseumData.updateMuseum(museum);
		return res;
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
		MuseumData.updateMuseum(museum);
		return res;
	}

	@POST
	@Path("/{id_museum}/new/work")
	@Consumes("application/json")
	public Response addWorkToMuseum(@PathParam("id_museum") int museumID, Paint work){
		Museum museum = MuseumData.getMuseum(museumID);
		Response res = WorkData.addPaint(work);
		List<Paint> works = museum.getPaints();
		works.add(work);
		museum.setPaints(works);
		MuseumData.updateMuseum(museum);
		return res;
	}

	@POST
	@Path("/{id_museum}/new/work")
	@Consumes("application/json")
	public Response addWorkToMuseum(@PathParam("id_museum") int museumID, Sculpture work){
		Museum museum = MuseumData.getMuseum(museumID);
		Response res = WorkData.addSculpture(work);
		List<Sculpture> works = museum.getSculptures();
		works.add(work);
		museum.setSculptures(works);
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
	public Response addWorkToPictureToMuseum(@PathParam("id_museum") int museumID, @PathParam("id_picture") int pictureID, Paint work){
		Response res = WorkData.addPaint(work);
		Museum museum = MuseumData.getMuseum(museumID);
		List<Picture> pictures = museum.getPictures();

		Picture picture = PictureData.getPicture(pictures, pictureID);
		picture.setWork(work);
		picture = PictureData.updatePicture(picture);
		
		museum.setPictures(pictures);
		MuseumData.updateMuseum(museum);
		return res;
	}



///////////////////////////////////////////////////////////////////////////

	// PUT operation used to modify an existing entity
	// Need to send directly an object to update

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
	public Paint updateWork(Paint work){
		return WorkData.updatePaint(work);
	}

	@PUT
	@Path("/update/work")
	@Produces("application/json")
	@Consumes("application/json")
	public Sculpture updateWork(Sculpture work){
		return WorkData.updateSculpture(work);
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

	@PUT
	@Path("/update/author")
	@Produces("application/json")
	@Consumes("application/json")
	public Author updateAuthor(Author author){
		return AuthorData.updateAuthor(author);
	}



///////////////////////////////////////////////////////////////////////////

	// DELETE : It's only remove an entity into DB. No care about association
	// Need to send directly an object to remove

	@DELETE
	@Path("/delete/museum")
	@Consumes("application/json")
	public void deleteMuseum(Museum museum){
		MuseumData.deleteMuseum(museum);
	}

	@DELETE
	@Path("/delete/picture")
	@Consumes("application/json")
	public void deletePicture(Picture picture){
		PictureData.deletePicture(picture);
	}

	@DELETE
	@Path("/delete/work")
	@Consumes("application/json")
	public void deleteWork(Paint work){
		WorkData.deletePaint(work);
	}

	@DELETE
	@Path("/delete/work")
	@Consumes("application/json")
	public void deleteWork(Sculpture work){
		WorkData.deleteSculpture(work);
	}

	@DELETE
	@Path("/delete/collectionPicture")
	@Consumes("application/json")
	public void deleteCollectionPicture(CollectionPicture cPicture){
		CollectionData.deleteCollectionPicture(cPicture);
	}

	@DELETE
	@Path("/delete/collectionWork")
	@Consumes("application/json")
	public void deleteCollectionWork(CollectionWork cWork){
		CollectionData.deleteCollectionWork(cWork);
	}

	@DELETE
	@Path("/delete/author")
	@Consumes("application/json")
	public void deleteAuthor(Author author){
		AuthorData.deleteAuthor(author);
	}

	//rajouter des chemins dans Path si unidirectionnel sinon Front-End + @PUT
}
