package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.resource.EmployerResource;
import ru.hh.school.resource.ExampleResource;
import ru.hh.school.resource.FavoritesEmployerResource;
import ru.hh.school.resource.FavoritesVacancyResource;
import ru.hh.school.resource.VacancyResource;
import ru.hh.school.service.EmployerService;
import ru.hh.school.service.HttpClient;
import ru.hh.school.service.VacancyService;


@Configuration
@Import({
        ExampleResource.class,
        NabCommonConfig.class,
        EmployerResource.class,
        FavoritesEmployerResource.class,
        EmployerService.class,
        EmployerDao.class,
        VacancyResource.class,
        FavoritesVacancyResource.class,
        VacancyService.class,
        VacancyDao.class,
        HttpClient.class,
})
public class CommonConfig {

  @Bean
  public MappingConfig mappingConfig() {
    MappingConfig mappingConfig = new MappingConfig();
    mappingConfig.addPackagesToScan("ru.hh.school.entity");
    return mappingConfig;
  }
}
