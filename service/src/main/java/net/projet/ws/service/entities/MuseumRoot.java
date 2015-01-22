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


	// Get a specific picture. ID is got from front-end so {id} is in a specific museum
	@GET
	@Path("/picture/{id}")
	@Produces("application/json")
	public Picture getPicture(@PathParam("id") int pictureID){
		return PictureData.getPicture(pictureID);
	}


	// Get all pictures but only in a museum otherwise it has no sense (picture of all museum not front)
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
	@Path("/paint/{id}")
	@Produces("application/json")
	public Paint getPaint(@PathParam("id") int paintID){
		return WorkData.getPaint(paintID);
	}

	@GET
	@Path("/{id}/paints")
	@Produces("application/json")
	public List<Paint> getPaints(@PathParam("id") int museumID){
		Museum museum = MuseumData.getMuseum(museumID);
		return museum.getPaints();
	}

	@GET
	@Path("/sculpture/{id}")
	@Produces("application/json")
	public Sculpture getSculpture(@PathParam("id") int sculptureID){
		return WorkData.getSculpture(sculptureID);
	}

	@GET
	@Path("/{id}/sculptures")
	@Produces("application/json")
	public List<Sculpture> getSculptures(@PathParam("id") int museumID){
		Museum museum = MuseumData.getMuseum(museumID);
		return museum.getSculptures();
	}


	@GET
	@Path("/author{id}")
	@Produces("application/json")
	public Author getAuthor(@PathParam("id") int authorID){
		return AuthorData.getAuthor(authorID);
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
	@Path("/collectionWork/{id}")
	@Produces("application/json")
	public CollectionWork getCollectionWork(@PathParam("id") int cWorkID){
		return CollectionData.getCollectionWork(cWorkID);
	}

	@GET
	@Path("/{id}/collectionsWork")
	@Produces("application/json")
	public List<CollectionWork> getCollectionsWork(@PathParam("id") int museumID){
		Museum museum = MuseumData.getMuseum(museumID);
		return museum.getCollectionsWorks();
	}


	@GET
	@Path("/collectionPicture/{id}")
	@Produces("application/json")
	public CollectionPicture getCollectionPicture(@PathParam("id") int cPictureID){
		return CollectionData.getCollectionPicture(cPictureID);
	}

	@GET
	@Path("/{id}/collectionsPicture")
	@Produces("application/json")
	public List<CollectionPicture> getCollectionsPicture(@PathParam("id") int museumID){
		Museum museum = MuseumData.getMuseum(museumID);
		return museum.getCollectionsPictures();
	}

	@GET
	@Path("/{id_museum}/paint/{id_work}")
	@Produces("application/json")
	public Paint getPaint(@PathParam("id_museum") int museumID, @PathParam("id_work") int workID){
		Museum museum = MuseumData.getMuseum(museumID);
		List<Paint> works = museum.getPaints();
		return WorkData.getPaint(works, workID);
	}

	@GET
	@Path("/{id_museum}/sculpture/{id_work}")
	@Produces("application/json")
	public Sculpture getSculpture(@PathParam("id_museum") int museumID, @PathParam("id_work") int workID){
		Museum museum = MuseumData.getMuseum(museumID);
		List<Sculpture> works = museum.getSculptures();
		return WorkData.getSculpture(works, workID);
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
	@Path("/new/paint")
	@Consumes("application/json")
	public Response addPaint(Paint work){
		return WorkData.addPaint(work);
	}

	@POST
	@Path("/new/sculpture")
	@Consumes("application/json")
	public Response addSculpture(Sculpture work){
		return WorkData.addSculpture(work);
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
	@Path("/{id_museum}/new/paint")
	@Consumes("application/json")
	public Response addPaintToMuseum(@PathParam("id_museum") int museumID, Paint work){
		Museum museum = MuseumData.getMuseum(museumID);
		Response res = WorkData.addPaint(work);
		List<Paint> works = museum.getPaints();
		works.add(work);
		museum.setPaints(works);
		MuseumData.updateMuseum(museum);
		return res;
	}

	@POST
	@Path("/{id_museum}/new/sculpture")
	@Consumes("application/json")
	public Response addSculptureToMuseum(@PathParam("id_museum") int museumID, Sculpture work){
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
	@Path("/update/paint")
	@Produces("application/json")
	@Consumes("application/json")
	public Paint updatePaint(Paint work){
		return WorkData.updatePaint(work);
	}

	@PUT
	@Path("/update/sculpture")
	@Produces("application/json")
	@Consumes("application/json")
	public Sculpture updateSculpture(Sculpture work){
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
	@Path("/delete/museum/{id}")
	@Consumes("application/json")
	public void deleteMuseum(@PathParam("id") int museumID){
		MuseumData.deleteMuseum(MuseumData.getMuseum(museumID));
	}

	@DELETE
	@Path("/delete/picture/{id}")
	@Consumes("application/json")
	public void deletePicture(@PathParam("id") int pictureID){
		PictureData.deletePicture(PictureData.getPicture(pictureID));
	}

	@DELETE
	@Path("/delete/paint/{id}")
	@Consumes("application/json")
	public void deletePaint(@PathParam("id") int workID){
		WorkData.deletePaint(WorkData.getPaint(workID));
	}

	@DELETE
	@Path("/delete/sculpture/{id}")
	@Consumes("application/json")
	public void deleteSculpture(@PathParam("id") int workID){
		WorkData.deleteSculpture(WorkData.getSculpture(workID));
	}

	@DELETE
	@Path("/delete/collectionPicture/{id}")
	@Consumes("application/json")
	public void deleteCollectionPicture(@PathParam("id") int cPictureID){
		CollectionData.deleteCollectionPicture(CollectionData.getCollectionPicture(cPictureID));
	}

	@DELETE
	@Path("/delete/collectionWork/{id}")
	@Consumes("application/json")
	public void deleteCollectionWork(@PathParam("id") int cWorkID){
		CollectionData.deleteCollectionWork(CollectionData.getCollectionWork(cWorkID));
	}

	@DELETE
	@Path("/delete/author/{id}")
	@Consumes("application/json")
	public void deleteAuthor(@PathParam("id") int authorID){
		AuthorData.deleteAuthor(AuthorData.getAuthor(authorID));
	}

	//rajouter des chemins dans Path si unidirectionnel sinon Front-End + @PUT
}
