package pt.upacademy.jseproject.maven.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import pt.upacademy.jseproject.maven.model.Product;
import pt.upacademy.jseproject.maven.model.Shelf;
import pt.upacademy.jseproject.maven.services.ProductService;
import pt.upacademy.jseproject.maven.services.ShelfService;
import pt.upacademy.jseproject.maven.utils.ShelfValidation;

@Path("/shelves")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShelfController {
	private ProductService PS = new ProductService();
	private ShelfService SS = new ShelfService();

	@Context
	protected UriInfo context;

	// region [GET]
	@GET
	@Path("status")
	@Produces(MediaType.TEXT_PLAIN)
	public String status() {
		return context.getRequestUri().toString() + " is Ok";
	}

	// [GET] - Get all shelves
	@GET
	public Response getAll() {
		//return SS.getAllEntities();
		List<Shelf> shelves = SS.getAllEntities();
		return Response.ok(shelves).build();
	}

	// [GET] - Get shelf by ID
	@GET
	@Path("/{shelfId}") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response getShelfById(@PathParam("shelfId") Long id) {
		Shelf shelf = SS.findById(id);
		if (shelf == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("[Error] - Shelf with ID: " + id + " not found!").build();
		}
		return Response.ok(shelf).build();
	}
	// endregion

	// region [POST]
	// [POST] - Add one shelf
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createShelf(Shelf shelf, @Context UriInfo uriInfo) {
		ShelfValidation.validateShelfData(shelf);
		Shelf newShelf = SS.create(shelf);
		UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder().path(newShelf.getId().toString());
		return Response.created(uriBuilder.build()).entity(newShelf).build();
	}
	// endregion

	// region [PUT]
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateShelf(Shelf shelf) {
		if (shelf.getId() == null) {
			throw new IllegalArgumentException("[Error] - Shelf ID must be provided in the body");
		}
	    Shelf existingShelf = SS.findById(shelf.getId());
	    ShelfValidation.validateShelfExists(existingShelf, shelf.getId());

	    // Validate incoming data fields (capacity / rentPrice)
	    ShelfValidation.validateShelfData(shelf);
	    
	    Shelf updatedShelf = SS.update(shelf);
	    return Response.ok(updatedShelf).build();
	}
	
	@PUT
	@Path("/product")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProductToShelf(@QueryParam("shelfId") Long shelfId, Product product) {
		if (shelfId == null) {
			throw new IllegalArgumentException("[Error] - Shelf ID must be provided");
		}
	    if (product == null || product.getId() == null) {
	        throw new IllegalArgumentException("[Error] - Product ID must be provided to assign to a shelf");
	    }

	    // Find shelf & validate
	    Shelf shelf = SS.findById(shelfId);
	    ShelfValidation.validateShelfExists(shelf, shelfId);
	    ShelfValidation.validateShelfIsAvailable(shelf);

	    // Find the product and validate
	    Product productToAssociate = PS.findById(product.getId());
	    ShelfValidation.validateProductToAssociate(productToAssociate, product.getId());

	    // Assign product to shelf
	    shelf.setProduct(productToAssociate);
	    SS.update(shelf);

	    List<Long> shelfIds = productToAssociate.getShelvesId();
	    if (shelfIds == null) {
	        shelfIds = new ArrayList<>();
	    }
	    if (!shelfIds.contains(shelfId)) {
	        shelfIds.add(shelfId);
	        productToAssociate.setShelvesId(shelfIds);
	        PS.update(productToAssociate);
	    }

	    // Return updated Shelf
	    return Response.ok(shelf).build();
	}
	// endregion

	// region [DELETE]
	// [DELETE] - Delete one Shelf
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeShelf(Shelf shelf) {
		if (shelf.getId() == null) {
			throw new IllegalArgumentException("[Error] - Shelf ID must be provided in the body");
		}
		Shelf shelfToRemove = SS.findById(shelf.getId());
		ShelfValidation.validateShelfExists(shelfToRemove, shelf.getId());
		if (shelfToRemove.getProduct() != null) {
			SS.removeProductFromShelf(shelfToRemove.getProduct(), shelf.getId());
		}
		SS.delete(shelf.getId());
		return Response.ok().entity("Shelf with ID: " + shelf.getId() + " removed successfully").build();
	}
	// endregion
}
