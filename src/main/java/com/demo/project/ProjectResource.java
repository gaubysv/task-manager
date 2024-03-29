package com.demo.project;

import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import org.jboss.resteasy.reactive.ResponseStatus;

@RolesAllowed("user")
@Path("/api/v1/projects")
public class ProjectResource {

    private final ProjectService projectService;

    @Inject
    public ProjectResource(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GET
    public Uni<List<Project>> get() {
        return projectService.listForUser();
    }

    @POST
    @ResponseStatus(201)
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Project> create(Project project) {
        return projectService.create(project);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Project> update(@PathParam("id") long id, Project project) {
        project.id = id;
        return projectService.update(project);
    }

    @DELETE
    @Path("/{id}")
    public Uni<Void> delete(@PathParam("id") long id) {
        return projectService.delete(id);
    }
}
