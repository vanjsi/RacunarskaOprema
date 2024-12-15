package com.mycompany.racunarskaoprema.rest;

import com.mycompany.racunarskaoprema.service.ProductService;
import com.mycompany.racunarskaoprema.data.Product;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.Connection;

@Path("/products")
public class ProductRest {
    private ProductService productService;

    public ProductRest(Connection connection) {
        this.productService = new ProductService(new com.mycompany.racunarskaoprema.dao.ProductDao(connection));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
        try {
            return Response.ok(productService.getAllProducts()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") int id) {
        try {
            Product product = productService.getProductById(id);
            if (product == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(product).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(Product product) {
        try {
            Product createdProduct = productService.createProduct(product);
            return Response.status(Response.Status.CREATED).entity(createdProduct).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") int id, Product product) {
        try {
            product.setIdProduct(id);
            productService.updateProduct(product);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") int id) {
        try {
            productService.deleteProduct(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
