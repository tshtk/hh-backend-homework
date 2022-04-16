package ru.hh.school.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.dto.FavEmployerDtoFull;
import ru.hh.school.dto.FavEmployerDtoShort;
import ru.hh.school.service.EmployerService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Singleton
@Path("/favorites/employer")
public class FavoritesEmployerResource {

  private static final Logger logger = LoggerFactory.getLogger(FavoritesEmployerResource.class);
  private final EmployerService employerService;

  @Inject
  public FavoritesEmployerResource(final EmployerService employerService) {
    this.employerService = employerService;
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)

  public Response addFavEmployer(final FavEmployerDtoShort employerDto) {
    employerService.addFavEmployer(employerDto);

    return Response
            .noContent()
            .build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getFavEmployers(
          @DefaultValue("0") @QueryParam("page") @Min(0) int page,
          @DefaultValue("20") @QueryParam("per_page") @Min(0) @Max(100) int perPage) {
    final List<FavEmployerDtoFull> employers = employerService.getFavEmployers(page, perPage);
    return Response
            .ok()
            .entity(employers)
            .build();
  }

  @PUT
  @Path("/{employer_id}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateFavEmployer(@PathParam("employer_id") int id, final FavEmployerDtoShort employerDto) {
    employerService.updateFavEmployer(id, employerDto);

    return Response
            .noContent()
            .build();
  }

  @DELETE
  @Path("/{employer_id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteFavEmployer(@PathParam("employer_id") int id) {
    employerService.deleteFavEmployer(id);

    return Response
            .noContent()
            .build();
  }


  @POST
  @Path("/{employer_id}/refresh")
  @Produces(MediaType.APPLICATION_JSON)
  public Response refreshFavEmployer(@PathParam("employer_id") int id) {
    employerService.refreshFavEmployer(id);
    return Response
            .noContent()
            .build();
  }



}
