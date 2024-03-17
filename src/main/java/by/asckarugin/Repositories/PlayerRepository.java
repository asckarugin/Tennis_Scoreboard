package by.asckarugin.Repositories;

import by.asckarugin.Models.Player;
import by.asckarugin.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.util.List;
import java.util.Optional;

public class PlayerRepository implements RepositoryCRUD<Long, Player>{

    @Override
    public List<Player> findAll() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();

            Query<Player> query = session.createQuery("from Player");
            List<Player> players = query.getResultList();

            session.getTransaction().commit();
            return players;
        }
    }

    @Override
    public Optional<Player> findById(Long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();

            Query<Player> query = session.createQuery("FROM Player WHERE id = :id");
            query.setParameter("id", id);
            Optional<Player> player = query.uniqueResultOptional();

            session.getTransaction().commit();
            return player;
        }
    }

    @Override
    public Player save(Player entity) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();

            session.persist(entity);

            session.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public void update(Player entity) {
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

            Player player = session.get(Player.class, id);
            if(player != null){
                session.remove(player);
                session.flush();
            } else{
                throw new RuntimeException();
            }
        }
    }

    public Optional<Player> findByName(String name) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();

            Query<Player> query = session.createQuery("FROM Player WHERE name = :name");
            query.setParameter("name", name);
            Optional<Player> player = query.uniqueResultOptional();

            session.getTransaction().commit();
            return player;
        }
    }
}
