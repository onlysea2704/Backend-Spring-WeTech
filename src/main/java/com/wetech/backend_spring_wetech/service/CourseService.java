package com.wetech.backend_spring_wetech.service;

import com.cloudinary.Cloudinary;
import com.wetech.backend_spring_wetech.config.CloudinaryConfig;
import com.wetech.backend_spring_wetech.dto.CourseRequest;
import com.wetech.backend_spring_wetech.entity.Course;
import com.wetech.backend_spring_wetech.entity.MyCourse;
import com.wetech.backend_spring_wetech.repository.CourseRepository;
import com.wetech.backend_spring_wetech.repository.MyCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private MyCourseRepository myCourseRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private UserService userService;

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public List<Course> getTop() {
        return courseRepository.getTop();
    }

    public List<Course> findByType(List<String> types) {
        return courseRepository.findByType(types);
    }

    public Course findByCourseId(Long courseId) {
        return courseRepository.findFirstByCourseId(courseId);
    }

    public Boolean checkHaveCourse(Long courseId, Long userId) {
        MyCourse exist = myCourseRepository.findFirstByCourseIdAndUserId(courseId,  userId);
        return exist != null? true : false;
    }

    public List<Course> findMyCourse(Long userId) {
        return myCourseRepository.findMyCourseByUserId(userId);
    }

    public Course createCourse(CourseRequest course, MultipartFile image) throws IOException {

        String imageUrl = uploadToCloudinary(image);
        LocalDate currentDate = LocalDate.now();
        Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Course newCourse = Course.builder()
                .title(course.getTitle())
                .description(course.getDescription())
                .author(course.getAuthor())
                .realPrice(course.getRealPrice())
                .salePrice(course.getSalePrice())
                .typeCourse(course.getTypeCourse())
                .linkImage(imageUrl)
                .intro1(course.getIntro1())
                .intro2(course.getIntro2())
                .createdAt(date)
                .build();

        return courseRepository.save(newCourse);
    }

//    public Course createCourse(Course course) {
//        return courseRepository.save(course);
//    }
//
//    public Course updateCourse(Course course) {
//        return courseRepository.save(course);
//    }
//
//    public void deleteCourse(Long courseId) {
//        courseRepository.deleteById(courseId);
//    }

    private String uploadToCloudinary(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("resource_type", "auto"));

        return uploadResult.get("secure_url").toString(); // link ảnh trực tiếp
    }
}
