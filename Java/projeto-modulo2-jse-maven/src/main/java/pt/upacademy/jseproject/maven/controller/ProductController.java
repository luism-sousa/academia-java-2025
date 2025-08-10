package pt.upacademy.jseproject.maven.controller;

import java.util.ArrayList;
import java.util.List;

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
import pt.upacademy.jseproject.maven.services.ShelfService;
import pt.upacademy.jseproject.maven.utils.ProductValidation;

@Path("/products")
public class ProductController {
	private ProductService PS = new ProductService();
	private ShelfService SS = new ShelfService();

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
	public Response getAll() {
		//return PS.getAllEntities();
		List<Product> products = PS.getAllEntities();
		return Response.ok(products).build();
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
		ProductValidation.validateProductData(product, PS);
		Product newProduct = PS.create(product);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(newProduct.getId().toString());
		return Response.created(builder.build()).entity(newProduct).build();
	}
	// endregion

	// region [PUT]
	@PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(Product product) {
		if (product.getId() == null) {
			throw new IllegalArgumentException("[Error] - Product ID must be provided in the body");
		}
		Product productToUpdate = PS.findById(product.getId());
		ProductValidation.validateProductExists(productToUpdate, product.getId());
		ProductValidation.validateProductData(product, PS, product.getId());
		
        productToUpdate.setName(product.getName());
        productToUpdate.setDiscount(product.getDiscount());
        productToUpdate.setPvp(product.getPvp());
        productToUpdate.setVat(product.getVat());
        Product updatedProduct = PS.update(productToUpdate);
        return Response.ok(updatedProduct).build();
    }
	// endregion

	// region [DELETE]
	// [DELETE] - Delete one Product
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeProduct(Product product) {
		if (product.getId() == null) {
			throw new IllegalArgumentException("[Error] - Product ID must be provided in the body");
		}
		Product productToDelete = PS.findById(product.getId());
	    ProductValidation.validateProductExists(productToDelete, product.getId());

	    if (productToDelete.getShelvesId() != null && !productToDelete.getShelvesId().isEmpty()) {
	        List<Long> shelvesToRemove = new ArrayList<Long>(productToDelete.getShelvesId());
	        for (Long shelfId : shelvesToRemove) {
	            SS.removeProductFromShelf(productToDelete, shelfId);
	        }
	    }

	    PS.delete(product.getId());
	    return Response.ok().entity("Product with ID: " + product.getId() + " removed successfully").build();
	}
	// endregion
}
