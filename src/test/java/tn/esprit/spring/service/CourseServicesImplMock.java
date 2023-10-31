package tn.esprit.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.Assertions;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties.Registration;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.SkiStationProject.entities.Course;
import tn.esprit.SkiStationProject.entities.enums.Support;
import tn.esprit.SkiStationProject.entities.enums.TypeCourse;
import tn.esprit.SkiStationProject.repositories.CourseRepository;
import tn.esprit.SkiStationProject.services.CourseServicesImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CourseServicesImplMock {
    @Mock
    CourseRepository courseRepository = Mockito.mock(CourseRepository.class);

    @InjectMocks
    CourseServicesImpl courseService;

    Course c = new Course(3, TypeCourse.COLLECTIVE_CHILDREN, Support.SKI, 2f, 3, null);

    List<Course> listCourses = new ArrayList<Course>() {
        {
            add(new Course(4, TypeCourse.COLLECTIVE_ADULT, Support.SNOWBOARD, 4f, 1, null));
            add(new Course(9, TypeCourse.INDIVIDUAL, Support.SKI, 7f, 21, null));
        }
    };

    @Test
    public void testRetrieveCourse() {
        Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(c));
        Course c1 = courseService.retrieveCourse((long) 1);
        Assertions.assertNotNull(c1);
    };
}