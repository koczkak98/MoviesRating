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

    public Rating getRatingById(int ratingID)
    {
        Rating rating = new Rating(ratingID);

        /** Open Session and Begin Transaction */
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();

            rating = session.get(Rating.class, ratingID);

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

    public Rating deleteRatingByMovieId(int ratingID)
    {
        Rating rating = new Rating();
        rating = getRatingById(ratingID);

        /** Open Session and Begin Transaction */
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(rating);

        /** Execute transaction and Close Session */
        session.getTransaction().commit();
        session.close();

        return rating;

    }

    public void close ()
    {
        sessionFactory.close();
    }

}
