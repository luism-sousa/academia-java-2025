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
	public List<Shelf> getAll() {
		return SS.getAllEntities();
	}

	// [GET] - Get shelf by ID
	@GET
	@Path("/shelf")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getShelfById(@QueryParam("shelfId") Long id) {
		Shelf shelf = SS.findById(id);
		if (shelf == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("[Error] - Shelf with ID: " + id + " not found!").build();
		}
		return Response.ok(id).build();
	}
	// endregion

	// region [POST]
	// [POST] - Add one shelf
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Shelf createShelf(Shelf s) {
		return SS.create(s);
	}
	// endregion

	// region [PUT]
	@PUT
	@Path("/{shelfId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Shelf updateShelf(@QueryParam("shelfId") Long shelfId, Shelf shelf) {
		if (shelfId == null) {
			throw new IllegalArgumentException("[Error] - Shelf ID is required to update!");
		}
		
		return SS.update(shelf);
	}
	
	@PUT
	@Path("/{shelfId}/product")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProductToShelf(@PathParam("shelfId") Long shelfId, Product product) {
		Shelf shelf = SS.findById(shelfId);
		ShelfValidation.validateShelfExists(shelf, shelfId);
		ShelfValidation.validateShelfIsAvailable(shelf);
			
		Product productToAssociate;
		if (product.getId() != null && product.getId() >= 0) {
			productToAssociate = PS.findById(product.getId());
			// Retificar
			ShelfValidation.validateProductToAssociate(productToAssociate, shelfId);
		} else {			
			ShelfValidation.validateProductToAssociate(product, null);
			productToAssociate = PS.create(product);
		}

		shelf.setProduct(productToAssociate);
		List<Long> shelfIds = productToAssociate.getShelvesId();
		if (shelfIds == null) {
			shelfIds = new ArrayList<>();
		}

		if (!shelfIds.contains(shelf.getId())) {
			shelfIds.add(shelfId);
			productToAssociate.setShelvesId(shelfIds);
		}

		SS.update(shelf);
		PS.update(productToAssociate);

		return Response.ok(shelf).build();
	}
	// endregion

	// region [DELETE]
	// [DELETE] - Delete one Shelf
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeProduct(@QueryParam("shelfId") Long shelfId) {
		Shelf shelf = SS.findById(shelfId);
		ShelfValidation.validateShelfExists(shelf, shelfId);
		SS.delete(shelfId);
		return Response.ok().build();
	}
	// endregion
}
