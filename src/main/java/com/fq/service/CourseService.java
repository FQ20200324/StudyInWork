package com.fq.service;

import com.fq.entity.Course;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CourseService {
    private List<Course> courses = new ArrayList<>();

    @Tool(name = "my_courses", description = "get a list of course from myCourse")
    public List<Course> getCourses() {
        return courses;
    }

    @Tool(name = "my_course", description = "get a course from myCourse")
    public List<Course> getCourse() {
        return courses;
    }

    @PostConstruct
    public void init() {
        courses.addAll(List.of(
                new Course("mcp", "mcp课程", "https://www.bilibili.com/video/BV1HmojYNE76/?spm_id_from=333.1387.favlist.content.click&vd_source=1b0454c53e76992ee22a6ac499960ce0"),
                new Course("blbl", "搜索", "https://search.bilibili.com/all?vt=15098055&keyword=%E6%98%8E%E6%97%A5%E6%96%B9%E8%88%9F&from_source=webtop_search&spm_id_from=333.1007&search_source=2")

        ));
    }
}
