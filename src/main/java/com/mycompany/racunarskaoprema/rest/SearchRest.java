package com.mycompany.racunarskaoprema.rest;

import com.mycompany.racunarskaoprema.service.SearchService;
import com.mycompany.racunarskaoprema.data.Search;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.Connection;

@Path("/searches")
public class SearchRest {
    private SearchService searchService;

    public SearchRest(Connection connection) {
        this.searchService = new SearchService(new com.mycompany.racunarskaoprema.dao.SearchDao(connection));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSearches() {
        try {
            return Response.ok(searchService.getAllSearches()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSearchById(@PathParam("id") int id) {
        try {
            Search search = searchService.getSearchById(id);
            if (search == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(search).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSearch(Search search) {
        try {
            Search createdSearch = searchService.createSearch(search);
            return Response.status(Response.Status.CREATED).entity(createdSearch).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSearch(@PathParam("id") int id) {
        try {
            searchService.deleteSearch(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}