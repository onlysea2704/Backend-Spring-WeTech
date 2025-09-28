package com.wetech.backend_spring_wetech.service;

import com.cloudinary.Cloudinary;
import com.wetech.backend_spring_wetech.config.CloudinaryConfig;
import com.wetech.backend_spring_wetech.entity.Course;
import com.wetech.backend_spring_wetech.entity.MyCourse;
import com.wetech.backend_spring_wetech.repository.CourseRepository;
import com.wetech.backend_spring_wetech.repository.MyCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.ArrayList;
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

    public Course createCourse(){

        Course newCourse = new Course();

        LocalDate currentDate = LocalDate.now();
        Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        newCourse.setCreatedAt(date);
        newCourse.setNumberRegister(0);
        return courseRepository.save(newCourse);
    }

    public Course updateCourse(Course course, MultipartFile image) throws IOException {

        String imageUrl = course.getLinkImage();
        if(image != null){
            imageUrl = uploadToCloudinary(image);
        }

        LocalDate currentDate = LocalDate.now();
        Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        course.setLinkImage(imageUrl);
        course.setCreatedAt(date);
        return courseRepository.save(course);
    }

    public boolean deleteCourse(Long courseId) {
        try {
            courseRepository.deleteById(courseId);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    private String uploadToCloudinary(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("resource_type", "auto"));

        return uploadResult.get("secure_url").toString(); // link ảnh trực tiếp
    }
}
