package com.example.movierating.MoviesRating.db;

import com.example.movierating.MoviesRating.model.Rating;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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

    public Rating getRatingByMovieId(int movieID)
    {
        Rating rating = new Rating(movieID);

        /** Open Session and Begin Transaction */
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        rating = session.get(Rating.class, movieID);

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
