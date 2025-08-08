package pt.upacademy.jseproject.maven.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import pt.upacademy.jseproject.maven.model.Product;
import pt.upacademy.jseproject.maven.services.ProductService;
import pt.upacademy.jseproject.maven.utils.ProductValidation;

@Path("/products")
public class ProductController {
	private ProductService PS = new ProductService();

	@Context
	protected UriInfo context;

	// region [GET]
	@GET
	@Path("/status")
	@Produces(MediaType.TEXT_PLAIN)
	public String status() {
		return context.getRequestUri().toString() + " is Ok";
	}

	// [Getter] - Get all products
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAll() {
		return PS.getAllEntities();
	}
	
	// [Getter] - Get one specific Product by Name
	@GET
	@Path("/name")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductByName(@QueryParam("productName") String productName) {
		Product product = PS.findByName(productName);
		if (product == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Product with name: " + productName + " not found!")
					.build();
		}
		return Response.ok(product).build();
	}
	
	// endregion

	// region [POST]
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProduct(Product product, @Context UriInfo uriInfo) {
		ProductValidation.validateProductData(product);
		Product newProduct = PS.create(product);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(newProduct.getId().toString());
		return Response.created(builder.build()).entity(newProduct).build();
	}
	// endregion

	// region [PUT]
	@PUT
    @Path("/{productId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@QueryParam("productId") Long productId, Product product) {
		try {	          
	        Product updatedProduct = PS.update(product);
	        return Response.ok(updatedProduct).build();
	    } catch (NoSuchElementException e) {
	        return Response.status(Response.Status.NOT_FOUND)
	                .entity("Product with ID: " + productId + " not found!")
	                .build();
	    } catch (IllegalArgumentException e) {
	        return Response.status(Response.Status.BAD_REQUEST)
	                .entity(e.getMessage())
	                .build();
	    }
    }
	// endregion

	// region [DELETE]
	// [DELETE] - Delete one Product
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeProduct(@QueryParam("productId") Long id) {
		if (PS.delete(id) == true) {
			return Response.ok()
					.entity("Product with ID: " + id + " removed successfully")
					.build();
		}
		return Response.status(Response.Status.NOT_FOUND)
				.entity("Product with ID: " + id + " not found! Unable to delete")
				.build();
	}
	// endregion
}
