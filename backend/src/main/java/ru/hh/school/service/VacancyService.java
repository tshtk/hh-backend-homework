package ru.hh.school.service;

import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.dto.FavVacancyDtoFull;
import ru.hh.school.dto.FavVacancyDtoShort;
import ru.hh.school.dto.VacancyDto;
import ru.hh.school.entity.Salary;
import ru.hh.school.entity.VacanciesEmployer;
import ru.hh.school.entity.Vacancy;


import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
public class VacancyService {
    private final HttpClient client;
    private final VacancyDao vacancyDao;
    private final EmployerDao employerDao;
    private final FileSettings fileSettings;

    @Inject
    public VacancyService(final HttpClient client, final VacancyDao vacancyDao, final EmployerDao employerDao, final FileSettings fileSettings) {
        this.client = client;
        this.vacancyDao = vacancyDao;
        this.employerDao = employerDao;
        this.fileSettings = fileSettings;
    }

    public List<VacancyDto> getVacancies(final int page, final int perPage, final String text) {
        return client.getVacancies(page, perPage, text).getItems();
    }

    public VacancyDto getVacancyById(final int id) {
        return client.getVacancyById(id);
    }

    public void addFavVacancy(final FavVacancyDtoShort vacancyDto) {
        final int id = Integer.parseInt(vacancyDto.getId());
        final String comment = vacancyDto.getComment();
        final Vacancy existed = vacancyDao.getById(id);
        if (existed != null) {
            throw new RuntimeException("vacancy " + id + " was previously added favorites");
        }
        final VacancyDto fromApi = client.getVacancyById(id);
        Vacancy vacancy = new Vacancy();
        vacancy.setId(id);
        vacancy.setDateCreate(LocalDate.now());
        vacancy.setViewsCount(1);
        vacancy.setComment(comment);

        vacancy.setName(fromApi.getName());
        vacancy.setArea(fromApi.getArea().getEntity());
        vacancy.setSalary(getSalary(fromApi));
        vacancy.setEmployer(getEmployer(fromApi));
        vacancy.setCreatedAt(fromApi.getCreatedAt());

        vacancyDao.saveVacancy(vacancy);
    }

    public void deleteFavVacancy(final int id) {
        if (!vacancyDao.removeById(id)) {
            throw new RuntimeException("vacancy " + id + " not in favorites");
        }
    }

    public void refreshFavVacancy(final int id) {
        final Vacancy vacancy = vacancyDao.getById(id);
        if (vacancy == null) {
            throw new RuntimeException("vacancy " + id + " not in favorites");
        }
        final VacancyDto fromApi = client.getVacancyById(id);

        vacancy.setName(fromApi.getName());
        vacancy.setArea(fromApi.getArea().getEntity());
        vacancy.setSalary(getSalary(fromApi));
        vacancy.setEmployer(getEmployer(fromApi));
        vacancy.setCreatedAt(fromApi.getCreatedAt());
        vacancyDao.updateVacancy(vacancy);
    }

    public List<FavVacancyDtoFull> getFavVacancies(final int page, final int perPage) {
        final Integer threshold = fileSettings.getInteger("popularityThreshold");
        List<Vacancy> vacancies = vacancyDao.getVacancies(page, perPage);

        favEmployersIncreaseViewCount(vacancies);

        return vacancies
                .stream()
                .map(vacancyDao::increaseViewCount)
                .map(o -> new FavVacancyDtoFull(o, threshold))
                .collect(Collectors.toList());

    }

    private Salary getSalary(final VacancyDto vacancyDto) {
        return vacancyDto.getSalary() == null ? null : vacancyDto.getSalary().getEntity();
    }

    private VacanciesEmployer getEmployer(final VacancyDto vacancyDto) {
        return vacancyDto.getEmployer() == null ? null : vacancyDto.getEmployer().getEntity();
    }

    private void favEmployersIncreaseViewCount(final List<Vacancy> vacancies) {
        vacancies
                .stream()
                .map(o -> o.getEmployer().getId())
                .forEach(employerDao::increaseViewCountById);
    }
}
