package com.example.school;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SchoolController {
    private List<Student> students = new ArrayList<>(
            List.of(
                    new Student(1L, "Max", "Petrov", 19),
                    new Student(2L, "Dina", "Borisova", 29),
                    new Student(3L, "Leonid", "Kasatonov", 23),
                    new Student(4L, "Sergey", "Borodov", 22),
                    new Student(5L, "Masha", "Poruvaeva", 39)

            )
    );
    private Teacher teacher = new Teacher(1L, "Maria Ivanovna", true);

    @GetMapping("/students")
    public String getStudents(Model model) {
        String status = null;
        model.addAttribute("message", "It's a test message");
        model.addAttribute("students", students);
        model.addAttribute("tutor", teacher);
        model.addAttribute("status", status);
        return "list";
    }

    @GetMapping("/add")
    public String addNewStudent(Student student) {
        return "add-student";
    }

    @GetMapping("/change")
    public String changeTeacher(Model model) {
        model.addAttribute("teacher", teacher);
        return "change-teacher";
    }

    @GetMapping("/update/{id}")
    public String changeStudent(@PathVariable(name = "id") Long id, Model model) {
        Optional<Student> st = students.stream().filter(i -> i.getId().equals(id)).findFirst();
        if (st.isEmpty())
            return getStudents(model);
        model.addAttribute("student", st);
        return "update-student";

    }

    @PostMapping("/update")
    public String updateStudent(
            @Valid @ModelAttribute Student student
    ) {
        for (Student st : students) {
            if (st.getId().equals(student.getId())) {
                st.setFirst(student.getFirst());
                st.setLast(student.getLast());
                st.setAge(student.getAge());
            }
        }
        return "redirect:/students";
    }

    @PostMapping("/teachers")
    public String updateTeacher(
            @Valid @ModelAttribute Teacher teacher,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors())
            return "change-teacher";
        this.teacher = teacher;
        return "redirect:/students";
    }

    @PostMapping("/students")
    public String createStudent(
            @Valid @ModelAttribute Student student,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors())
            return "add-student";
        students.add(student);
        return "redirect:/students";
    }

}
