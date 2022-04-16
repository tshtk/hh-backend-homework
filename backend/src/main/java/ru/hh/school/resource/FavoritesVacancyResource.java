package ru.hh.school.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.dto.FavVacancyDtoFull;
import ru.hh.school.dto.FavVacancyDtoShort;
import ru.hh.school.service.VacancyService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Singleton
@Path("/favorites/vacancy")
public class FavoritesVacancyResource {

  private static final Logger logger = LoggerFactory.getLogger(FavoritesVacancyResource.class);
  private final VacancyService vacancyService;

  @Inject
  public FavoritesVacancyResource(final VacancyService vacancyService) {
    this.vacancyService = vacancyService;
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)

  public Response addFavEmployer(final FavVacancyDtoShort vacancyDto) {
    vacancyService.addFavVacancy(vacancyDto);

    return Response
            .noContent()
            .build();
  }

  @DELETE
  @Path("/{vacancy_id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteFavVacancy(@PathParam("vacancy_id") int id) {
    vacancyService.deleteFavVacancy(id);

    return Response
            .noContent()
            .build();
  }

  @POST
  @Path("/{vacancy_id}/refresh")
  @Produces(MediaType.APPLICATION_JSON)
  public Response refreshFavVacancy(@PathParam("vacancy_id") int id) {
    vacancyService.refreshFavVacancy(id);
    return Response
            .noContent()
            .build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getFavVacancies(
          @DefaultValue("0") @QueryParam("page") @Min(0) int page,
          @DefaultValue("20") @QueryParam("per_page") @Min(0) @Max(100) int perPage) {
    final List<FavVacancyDtoFull> vacancies = vacancyService.getFavVacancies(page, perPage);
    return Response
            .ok()
            .entity(vacancies)
            .build();
  }





}
