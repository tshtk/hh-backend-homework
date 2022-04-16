package ru.hh.school.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.dto.VacancyDto;
import ru.hh.school.service.VacancyService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Singleton
@Path("/vacancy")
public class VacancyResource {

  private static final Logger logger = LoggerFactory.getLogger(VacancyResource.class);
  private final VacancyService vacancyService;

  @Inject
  public VacancyResource(final VacancyService vacancyService) {
    this.vacancyService = vacancyService;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getEmployers(
          @DefaultValue("")@QueryParam("query") String query,
          @DefaultValue("0") @QueryParam("page") @Min(0) int page,
          @DefaultValue("20") @QueryParam("per_page") @Min(0) @Max(100) int perPage) {
    final List<VacancyDto> vacancies = vacancyService.getVacancies(page, perPage, query);
    return Response
            .ok()
            .entity(vacancies)
            .build();
  }

  @GET
  @Path("/{vacancy_id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getEmployer(@PathParam("vacancy_id") int id) {
    VacancyDto vacancy = vacancyService.getVacancyById(id);
    return Response
            .ok()
            .entity(vacancy)
            .build();
  }

}
