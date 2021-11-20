package com.anchietastudent.tuts.course.service;

import com.anchietastudent.tuts.category.model.Category;
import com.anchietastudent.tuts.category.service.CategoryService;
import com.anchietastudent.tuts.course.dto.CourseCreateDTO;
import com.anchietastudent.tuts.course.dto.CourseDTO;
import com.anchietastudent.tuts.course.dto.CourseFilterDTO;
import com.anchietastudent.tuts.course.model.Course;
import com.anchietastudent.tuts.course.repository.CourseRepository;
import com.anchietastudent.tuts.topic.TopicDTO;
import com.anchietastudent.tuts.topic.model.Topic;
import com.anchietastudent.tuts.user.model.User;
import com.anchietastudent.tuts.user.model.enumeration.RoleName;
import com.anchietastudent.tuts.user.service.UserService;
import com.anchietastudent.tuts.util.dto.MessageResponseDTO;
import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    public List<CourseDTO> findAll() {
        List<Course> courses = repository.findAll();
        List<CourseDTO> dto = courses.stream().map(c -> CourseDTO.buildDTO(c, c.getTeacher()))
                .collect(Collectors.toList());
        return dto;
    }

    public Course save(Course Course) {
        return repository.save(Course);
    }

    public MessageResponseDTO save(CourseCreateDTO dto) throws NotFoundException {
        Category category = categoryService.findById(dto.getCategoryId());
        User teacher = userService.findOne(dto.getTeacherId());
        Course course = null;
        if(Objects.nonNull(dto.getTopics()) && !CollectionUtils.isEmpty(dto.getTopics())) {
            List<Topic> topics = dto.getTopics().stream().map(topic -> TopicDTO.toTopicEntity(topic))
                    .collect(Collectors.toList());
            course = CourseCreateDTO.toCourseEntity(dto, category, teacher, topics);
            Course finalCourse = course;
            topics.forEach(topic -> topic.setCourse(finalCourse));
        } else {
            course = CourseCreateDTO.toCourseEntity(dto, category, teacher, null);
        }
        repository.save(course);
        return new MessageResponseDTO("Curso criado com sucesso!");
    }

    public Course findById(UUID id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Curso não encontrado"));
    }

    public void delete(Course Course) {
        repository.delete(Course);
    }

    public Long count() {
        return Long.valueOf(repository.count());
    }

    public List<CourseDTO> findAllByCategoryId(UUID categoryId) throws NotFoundException {
        Category category = categoryService.findById(categoryId);
        List<Course> courses = repository.findByCategory(category);
        List<CourseDTO> dto = courses.stream().map(c -> CourseDTO.buildDTO(c, c.getTeacher()))
                .collect(Collectors.toList());
        return dto;
    }

    public List<CourseDTO> findByFilter(CourseFilterDTO courseFilterDTO) throws NotFoundException {
        User user = userService.findOne(courseFilterDTO.getUserId());
        List<Course> courses = new ArrayList<Course>();
        if(user.getRoles().stream().allMatch(r -> r.getName().equals(RoleName.STUDENT))) {
            courses = repository.findByStudents(user);
        }
        else if(user.getRoles().stream().allMatch(r -> r.getName().equals(RoleName.TEACHER))) {
            courses = repository.findByTeacher(user);
        }
        List<CourseDTO> dto = courses.stream().map(c -> CourseDTO.buildDTO(c, c.getTeacher()))
                .collect(Collectors.toList());
        return dto;
    }

    public MessageResponseDTO enrollStudent(UUID courseId, UUID userId) throws NotFoundException {
        Course course = findById(courseId);
        User user = userService.findOne(userId);
        List<User> students = course.getStudents();
        Boolean isStudentAlreadyEnrolled = students.stream().map(User::getId).anyMatch(id -> userId.equals(id));
        if(!isStudentAlreadyEnrolled) {
            students.add(user);
            course.setStudents(students);
            repository.save(course);
            return new MessageResponseDTO("Matriculado com sucesso!");
        }
        return new MessageResponseDTO("Usuário já matriculado!");
    }

    public MessageResponseDTO uploadImage(MultipartFile file, UUID courseId) throws BadHttpRequest, NotFoundException {
        try {
            Course course = findById(courseId);
            course.setImage(file.getBytes());
            course.setImageContentType(file.getContentType());
            course.setImageFileName(file.getOriginalFilename());
            repository.save(course);
            return new MessageResponseDTO("Imagem salva com sucesso!");
        } catch (IOException ex) {
            throw new BadHttpRequest();
        }
    }

    public ResponseEntity<byte[]> downloadImage(UUID courseId) throws NotFoundException {
        Course course = findById(courseId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + course.getImageFileName() + "\"")
                .contentType(MediaType.valueOf(course.getImageContentType()))
                .body(course.getImage());
    }
}
