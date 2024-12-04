package org.example;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        ShapeDAO shapeDAO = new ShapeDAO(sessionFactory);

        try {
            Color color1 = new Color(255, 255, 255, 1);
            Color color2 = new Color(87, 93,  115, 0.5);
            Color color3 = new Color(5, 6,  7);
            Color color4 = new Color(14, 255, 0);

            Triangle triangle = new Triangle(5,5,5, color1);
            Triangle triangle2 = new Triangle(5,5,5, color2);
            shapeDAO.saveShape(triangle);
            shapeDAO.saveShape(triangle2);

            Rectangle rectangle = new Rectangle(10, 80, color3);
            Rectangle rectangle2 = new Rectangle(10, 80, color4);
            shapeDAO.saveShape(rectangle);
            shapeDAO.saveShape(rectangle2);

            shapeDAO.getAllShapes().forEach((Shape s) -> {System.out.println(
                    s.getClassName() + ", ID:" + s.getId() + ", " + s + ", color: " + s.getColorDescription()
            );});
        }
        finally {
            shapeDAO.close();
        }
    }
}
