package tn.esprit.spring.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.SkiStationProject.SkiStationProjectApplication;
import tn.esprit.SkiStationProject.entities.Course;
import tn.esprit.SkiStationProject.services.ICourseServices;
import java.util.List;

@SpringBootTest(classes = {SkiStationProjectApplication.class})
@TestMethodOrder(OrderAnnotation.class)
public class CourseServicesImplTest {
    @Autowired
    ICourseServices cs;

    @Test
    @Order(1)
    public void testRetrieveAllCourses() {
        List<Course> courses = cs.retrieveAllCourses();
        Assertions.assertEquals(0, courses.size());
    }
}