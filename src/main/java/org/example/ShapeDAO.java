package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ShapeDAO {
    private final SessionFactory sessionFactory;

    public ShapeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveShape(Shape shape) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(shape);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public Shape getShapeById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Shape.class, id);
        }
    }

    public List<Shape> getAllShapes() {
        try (Session session = sessionFactory.openSession()) {
            List<Shape> result = session.createQuery("FROM Rectangle", Shape.class).list();
            result.addAll(session.createQuery("FROM Triangle", Shape.class).list());
            return result;
        }
    }

    public void updateShape(Shape shape) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(shape);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void deleteShape(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Shape shape = session.get(Shape.class, id);
            if (shape != null) {
                session.remove(shape);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void close() {
        sessionFactory.close();
    }
}
