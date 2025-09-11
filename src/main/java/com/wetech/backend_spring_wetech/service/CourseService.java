package com.wetech.backend_spring_wetech.service;

import com.cloudinary.Cloudinary;
import com.wetech.backend_spring_wetech.config.CloudinaryConfig;
import com.wetech.backend_spring_wetech.dto.CourseRequest;
import com.wetech.backend_spring_wetech.entity.Course;
import com.wetech.backend_spring_wetech.entity.MyCourse;
import com.wetech.backend_spring_wetech.entity.Section;
import com.wetech.backend_spring_wetech.entity.Video;
import com.wetech.backend_spring_wetech.repository.CourseRepository;
import com.wetech.backend_spring_wetech.repository.MyCourseRepository;
import com.wetech.backend_spring_wetech.repository.SectionRepository;
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
    private SectionRepository sectionRepository;
//    @
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

    public Course createCourse(Course course, MultipartFile image) throws IOException {

        String imageUrl = course.getLinkImage();
        if(course.getLinkImage() != null && !course.getLinkImage().equals("")){
            imageUrl = uploadToCloudinary(image);
        }

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

    public Course updateCourse(Course course, MultipartFile image) throws IOException {

        String imageUrl = course.getLinkImage();
        if(course.getLinkImage() != null && !course.getLinkImage().equals("")){
            imageUrl = uploadToCloudinary(image);
        }

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

    public boolean deleteCourse(Long courseId) {
        try {
            courseRepository.deleteById(courseId);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public List<Section> getSections(Long courseId) {
        List<Section> sections = new ArrayList<>();
        sections = sectionRepository.findByCourseId(courseId);
        return sections;
    }

    public Section createSection(Long courseId, Section section) {
        Section newSection = new Section();
        newSection.setCourseId(courseId);
        newSection.setName(section.getName());
        return sectionRepository.save(newSection);
    }

    public Section updateSection(Section section){
        return sectionRepository.save(section);
    }

    public List<Video> findVideosBySectionId(Long sectionId) {
        return video
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
