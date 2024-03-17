package by.asckarugin.Repositories;

import by.asckarugin.Models.Match;
import by.asckarugin.Models.Player;
import by.asckarugin.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class MatchRepository implements RepositoryCRUD<Long, Match>{

    @Override
    public List<Match> findAll() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();

            Query<Match> query = session.createQuery("FROM Match");
            List<Match> matches = query.getResultList();

            session.getTransaction().commit();
            return matches;
        }
    }

    @Override
    public Optional<Match> findById(Long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();

            Optional<Match> match = session.createQuery("FROM Match WHERE id = :id", Match.class)
                                .setParameter("id", id)
                                .uniqueResultOptional();

            session.getTransaction().commit();
            return match;
        }
    }

    @Override
    public Match save(Match entity) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();

            session.persist(entity);

            session.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public void update(Match entity) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();

            session.merge(entity);

            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();

            Match match = session.get(Match.class, id);
            if(match != null){
                session.remove(match);
                session.flush();
            } else{
                throw new RuntimeException();
            }
        }
    }

    public List<Match> findByName(String name){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();

            Query<Player> query = session.createQuery("FROM Player WHERE name = :name", Player.class);
            query.setParameter("name", name);

            List<Match> matches;
            try {
                Player player = query.getSingleResult();
                Query<Match> query1 = session.createQuery("FROM Match WHERE firstPlayer = :player OR secondPlayer = :player", Match.class);
                query1.setParameter("player", player);
                matches = query1.getResultList();

                return matches;
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                session.getTransaction().commit();
            }

        }
    }
}
