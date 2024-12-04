package org.example;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class ShapeDAOTest {
    private static SessionFactory sessionFactory;
    private Session session;

    @BeforeAll
    static void setupSessionFactory() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    @BeforeEach
    void setupSession() {
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @AfterEach
    void tearDownSession() {
        if (session != null) {
            session.getTransaction().commit();
            session.close();
        }
    }

    @AfterAll
    static void tearDownSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    void testShapeTableCreation() {
        assertDoesNotThrow(() -> {
            session.getTransaction().commit();
            session.beginTransaction();
        });
    }

    @Test
    void testInsertAndRetrieveTriangle() {
        Triangle triangle = new Triangle(6, 7, 8, new Color(14, 0, 105));
        session.save(triangle);

        List<Triangle> triangles = session.createQuery("FROM Triangle", Triangle.class).list();
        assertEquals(1, triangles.size());
        assertEquals(7, triangles.get(0).getB());
        assertEquals(105, triangles.get(0).getColor().getBlue());
    }

    @Test
    void testInsertAndRetrieveRectangle() {
        Rectangle rectangle = new Rectangle(15, 104.5, new Color(0, 255, 100));
        session.save(rectangle);

        List<Rectangle> rectangles = session.createQuery("FROM Rectangle", Rectangle.class).list();
        assertEquals(1, rectangles.size());
        assertEquals(15, rectangles.get(0).getWidth());
        assertEquals(255, rectangles.get(0).getColor().getGreen());
    }

    @Test
    void testUpdateTriangle() {
        Triangle triangle = new Triangle(6, 7, 8, new Color(14, 0, 105));
        session.save(triangle);

        Triangle retrievedTriangle = session.get(Triangle.class, triangle.getId());
        retrievedTriangle.setA(10.0);
        session.update(retrievedTriangle);

        Triangle updatedTriangle = session.get(Triangle.class, triangle.getId());
        assertEquals(10.0, updatedTriangle.getA());
    }

    @Test
    void testDeleteRectangle() {
        Rectangle rectangle = new Rectangle(15, 104.5, new Color(0, 255, 100));
        session.save(rectangle);

        Rectangle retrievedRectangle = session.get(Rectangle.class, rectangle.getId());
        session.delete(retrievedRectangle);

        Rectangle deletedRectangle = session.get(Rectangle.class, rectangle.getId());
//        assertNull(deletedRectangle);
        assertEquals(null, deletedRectangle);
    }
}