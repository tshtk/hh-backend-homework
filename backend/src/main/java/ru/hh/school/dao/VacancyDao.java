package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.hh.school.entity.Vacancy;

import java.util.List;


public class VacancyDao extends GenericDao {
    public VacancyDao(final SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public void saveVacancy(Vacancy vacancy) {
        Session session = getSession();

        session.merge(vacancy.getArea());
        session.merge(vacancy.getEmployer());
        session.persist(vacancy);
    }

    public Vacancy getById(final int id) {
        return super.get(Vacancy.class, id);
    }

    public List<Vacancy> getVacancies(final int page, final int perPage) {
        Session session = getSession();
        Query<Vacancy> query = session.createQuery("FROM Vacancy v ORDER BY v.id", Vacancy.class);
        query.setFirstResult(page * perPage);
        query.setMaxResults(perPage);
        return query.list();
    }

    public boolean removeById(final int id) {
        Session session = getSession();
        Vacancy vacancy = session.get(Vacancy.class, id);
        if (vacancy == null) {
            return false;
        }
        session.delete(vacancy);
        return true;
    }

    public void updateVacancy(final Vacancy vacancy) {
        Session session = getSession();

        session.merge(vacancy.getArea());
        session.merge(vacancy.getEmployer());
        session.merge(vacancy);
    }

    public Vacancy increaseViewCount(Vacancy vacancy) {
        final int id = vacancy.getId();
        Session session = getSession();
        Query<Vacancy> query = session.createQuery(
                "UPDATE Vacancy v " +
                        "SET v.viewsCount = v.viewsCount + 1 " +
                        "WHERE v.id = :id");
        query.setParameter("id", id).executeUpdate();
        session.refresh(vacancy);
        return vacancy;
    }
}
