package ru.hh.school.service;

import org.eclipse.jetty.http.HttpHeader;
import ru.hh.school.dto.EmployerByIdDto;
import ru.hh.school.dto.EmployersListDto;
import ru.hh.school.dto.VacanciesListDto;
import ru.hh.school.dto.VacancyDto;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class HttpClient {
    
    private static final String USER_AGENT_VALUE = "my-hh-school-app (th@tshtk.ru)";
    private static final String URI = "https://api.hh.ru";
    private static final String EMPLOYERS_PATH = "employers";
    private static final String VACANCIES_PATH = "vacancies";
    private static final String PAGE = "page";
    private static final String PER_PAGE = "per_page";
    private static final String TEXT = "text";

    private final Client client = ClientBuilder.newClient();

    public final EmployersListDto getEmployers(int page, int perPage, String text) {
        return client
                .target(URI)
                .path(EMPLOYERS_PATH)
                .queryParam(PAGE, page)
                .queryParam(PER_PAGE, perPage)
                .queryParam(TEXT, text)
                .request()
                .header(HttpHeader.USER_AGENT.asString(), USER_AGENT_VALUE)
                .get(EmployersListDto.class);
    }

    public EmployerByIdDto getEmployerById(final int id) {
        return client
                .target(URI)
                .path(EMPLOYERS_PATH)
                .path(String.valueOf(id))
                .request()
                .header(HttpHeader.USER_AGENT.asString(), USER_AGENT_VALUE)
                .get(EmployerByIdDto.class);
    }

    public VacanciesListDto getVacancies(final int page, final int perPage, final String text) {
        return client
                .target(URI)
                .path(VACANCIES_PATH)
                .queryParam(PAGE, page)
                .queryParam(PER_PAGE, perPage)
                .queryParam(TEXT, text)
                .request()
                .header(HttpHeader.USER_AGENT.asString(), USER_AGENT_VALUE)
                .get(VacanciesListDto.class);
    }

    public VacancyDto getVacancyById(final int id) {
        return client
                .target(URI)
                .path(VACANCIES_PATH)
                .path(String.valueOf(id))
                .request()
                .header(HttpHeader.USER_AGENT.asString(), USER_AGENT_VALUE)
                .get(VacancyDto.class);
    }
}
