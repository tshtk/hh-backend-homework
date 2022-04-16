package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.hh.school.entity.Employer;

import java.util.List;


public class EmployerDao extends GenericDao {
    public EmployerDao(final SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public void saveEmployer(Employer employer) {
        Session session = getSession();

        session.merge(employer.getArea());
        session.persist(employer);
    }

    public Employer getById(final int id) {
        return super.get(Employer.class, id);
    }

    public List<Employer> getEmployers(final int page, final int perPage) {
        Session session = getSession();
        Query<Employer> query = session.createQuery("FROM Employer e ORDER BY e.id", Employer.class);
        query.setFirstResult(page * perPage);
        query.setMaxResults(perPage);
        return query.list();
    }

    public Employer increaseViewCount(Employer employer) {
        final int id = employer.getId();
        Session session = getSession();
        Query<Employer> query = session.createQuery(
                "UPDATE Employer e " +
                   "SET e.viewsCount = e.viewsCount + 1 " +
                   "WHERE e.id = :id");
        query.setParameter("id", id).executeUpdate();
        session.refresh(employer);
        return employer;
    }

    public void increaseViewCountById(int id) {

        Session session = getSession();
        if (super.get(Employer.class, id) == null) {
            return;
        }
        Query<Employer> query = session.createQuery(
                "UPDATE Employer e " +
                        "SET e.viewsCount = e.viewsCount + 1 " +
                        "WHERE e.id = :id");
        query.setParameter("id", id).executeUpdate();
    }


    public boolean updateComment(final int id, final String comment) {
        Session session = getSession();
        Employer employer = session.get(Employer.class, id);
        if (employer == null) {
            return false;
        }
        employer.setComment(comment);
        return true;

    }

    public boolean removeById(final int id) {
        Session session = getSession();
        Employer employer = session.get(Employer.class, id);
        if (employer == null) {
            return false;
        }
        session.delete(employer);
        return true;
    }

    public void updateEmployer(final Employer employer) {
        Session session = getSession();

        session.merge(employer.getArea());
        session.merge(employer);
    }
}
