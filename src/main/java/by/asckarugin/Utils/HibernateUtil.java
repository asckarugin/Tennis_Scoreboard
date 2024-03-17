package by.asckarugin.Utils;

import by.asckarugin.Models.Match;
import by.asckarugin.Models.Player;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {
        @Getter
        private static final SessionFactory sessionFactory = buildSessionFactory();

        private static SessionFactory buildSessionFactory() {
            try {
                return new Configuration().configure()
                        .addAnnotatedClass(Player.class)
                        .addAnnotatedClass(Match.class)
                        .buildSessionFactory();
            } catch (Throwable ex) {
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
}

