package ru.hh.school.service;

import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dto.EmployerByIdDto;
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.dto.FavEmployerDtoFull;
import ru.hh.school.dto.FavEmployerDtoShort;
import ru.hh.school.entity.Employer;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
public class EmployerService {
    private final HttpClient client;
    private final EmployerDao employerDao;
    private final FileSettings fileSettings;

    @Inject
    public EmployerService(final HttpClient client, final EmployerDao employerDao, final FileSettings fileSettings) {
        this.client = client;
        this.employerDao = employerDao;
        this.fileSettings = fileSettings;
    }

    public List<EmployerDto> getEmployers(final int page, final int perPage, final String text) {
        return client.getEmployers(page, perPage, text).getItems();
    }

    public EmployerByIdDto getEmployerById(final int id) {
        return client.getEmployerById(id);
    }

    public void addFavEmployer(final FavEmployerDtoShort employerDto) {
        final int id = Integer.parseInt(employerDto.getId());
        final String comment = employerDto.getComment();
        final Employer existed = employerDao.getById(id);
        if (existed != null) {
            throw new RuntimeException("employer " + id + " was previously added favorites");
        }
        final EmployerByIdDto fromAPI = client.getEmployerById(id);
        Employer employer = new Employer();
        employer.setId(id);
        employer.setName(fromAPI.getName());
        employer.setDateCreate(LocalDate.now());
        employer.setDescription(fromAPI.getDescription());
        employer.setArea(fromAPI.getArea().getEntity());
        employer.setComment(comment);
        employerDao.saveEmployer(employer);
    }

    public List<FavEmployerDtoFull> getFavEmployers(final int page, final int perPage) {
        final Integer threshold = fileSettings.getInteger("popularityThreshold");
        return employerDao
                .getEmployers(page, perPage)
                .stream()
                .map(employerDao::increaseViewCount)
                .map(o -> new FavEmployerDtoFull(o, threshold))
                .collect(Collectors.toList());
    }

    public void updateFavEmployer(final int id, final FavEmployerDtoShort employerDto) {
        if (!employerDao.updateComment(id, employerDto.getComment())) {
            throw new RuntimeException("employer " + id + " not in favorites");
        }
    }

    public void deleteFavEmployer(final int id) {
        if (!employerDao.removeById(id)) {
            throw new RuntimeException("employer " + id + " not in favorites");
        }
    }

    public void refreshFavEmployer(final int id) {
        final Employer employer = employerDao.getById(id);
        if (employer == null) {
            throw new RuntimeException("employer " + id + " not in favorites");
        }
        final EmployerByIdDto fromAPI = client.getEmployerById(id);
        employer.setName(fromAPI.getName());
        employer.setDescription(fromAPI.getDescription());
        employer.setArea(fromAPI.getArea().getEntity());
        employerDao.updateEmployer(employer);

    }
}
