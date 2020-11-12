package com.example.movierating.MoviesRating.db;

import com.example.movierating.MoviesRating.model.Rating;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;


import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Hibernate_SQLHandler {

    private SessionFactory sessionFactory;

    public void open ()
    {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

        sessionFactory =
                new MetadataSources(registry).buildMetadata().buildSessionFactory();

    }

    public Rating getRatingByMovieId(int ratingID)
    {
        Rating rating = new Rating(ratingID);

        /** Open Session and Begin Transaction */
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();

            rating = session.get(Rating.class, ratingID);


            /** Execute transaction and Close Session */
            session.getTransaction().commit();
            session.close();
        }
        catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }


        /** Execute transaction and Close Session */
        session.getTransaction().commit();
        session.close();

        return rating;

    }

    public List<Rating> deleteRatingByMovieId(int movieID)
    {
        /** Open Session and Begin Transaction */
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        /** Prepare Criteria */
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Rating> criteria = builder.createQuery( Rating.class );

        /** Prepare HQL Statement */
        Root<Rating> fromTable = criteria.from(Rating.class);

        Predicate condition = builder.between( fromTable.get("movieId") , movieID , movieID);
        CriteriaQuery<Rating> hqlStatement = criteria.select(fromTable).where(condition);
        TypedQuery<Rating> query = session.createQuery(hqlStatement);

        /** Execute HQL Statement */
        List<Rating> results = query.getResultList();
        try {
            for (int i = 0; i < results.size(); i++) {
                session.delete(results.get(i));
            }
        }
        catch (NoResultException e) {
            results = new ArrayList<Rating>();
        }
        catch (NonUniqueResultException e) {
            results = new ArrayList<Rating>();
        }
        catch (Exception e)
        {
            results = new ArrayList<Rating>();
        }
        /** Execute transaction and Close Session */
        session.getTransaction().commit();
        session.close();

        return results;

    }

    public void close ()
    {
        sessionFactory.close();
    }

}
