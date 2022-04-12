package ru.hh.school.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.dto.EmployerByIdDto;
import ru.hh.school.dto.EmployerDto;
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
@Path("/employer")
public class EmployerResource {

  private static final Logger logger = LoggerFactory.getLogger(EmployerResource.class);
  private final EmployerService employerService;

  @Inject
  public EmployerResource(final EmployerService employerService) {
    this.employerService = employerService;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getEmployers(
          @DefaultValue("")@QueryParam("query") String query,
          @DefaultValue("0") @QueryParam("page") @Min(0) int page,
          @DefaultValue("20") @QueryParam("per_page") @Min(0) @Max(100) int perPage) {
    final List<EmployerDto> employers = employerService.getEmployers(page, perPage, query);
    return Response
            .ok()
            .entity(employers)
            .build();
  }


  @GET
  @Path("/{employer_id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getEmployer(@PathParam("employer_id") int id) {
    EmployerByIdDto employer = employerService.getEmployerById(id);
    return Response
            .ok()
            .entity(employer)
            .build();
  }
}
