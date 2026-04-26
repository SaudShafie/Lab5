package org.example.lab5q1.Controller;

import org.example.lab5q1.ApiRespons.ApiRespons;
import org.example.lab5q1.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/Q1")
public class StudentController {
    ArrayList<Student> students = new ArrayList<>();

    @PostMapping("/add-student")
    public ApiRespons addStudent(@RequestBody Student student) {
students.add(student);
        return new ApiRespons("New Student has been added");

    }
    @GetMapping("/display-student")
    public ArrayList<Student> displayStudent(){
        return students;
    }
    @PutMapping("/update-student/{id}")
    public ApiRespons updateStudent(@PathVariable int id,@RequestBody Student student){
        boolean flag=false;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId()==id){
                students.set(i,student);
                flag=true;
                break;
            }
        }
        if (!flag)return new ApiRespons("There is no such student with this ID!!");
        return new ApiRespons("Student Updated Successfully");
    }
    @DeleteMapping("/delet-student/{id}")
    public ApiRespons deleteStudent(@PathVariable int id){
        boolean flag=false;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId()==id){
                students.remove(i);
                flag=true;
                break;
            }
        }
        if (!flag)return new ApiRespons("There is no such student with this ID!!");
        return new ApiRespons("Student Deleted Successfully");
    }
    @GetMapping("/classify-students")
    public  ApiRespons classifyingStudent(){
        ArrayList<Objects> r=new ArrayList<>();
        String R1="First Honor Student: ";

        String R2=" Second Honor Students:";
        String R3=" Non-Honor Students:";
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getGPA()>=4.75){
                R1+=" "+students.get(i);

            }else  if(students.get(i).getGPA()>=4.50){
                R2+=" "+students.get(i);
            }else {
                R3+=" "+students.get(i);
            }
        }
        return new ApiRespons("{ "+R1+" } "+"{ "+R2+" } {" +R3+" }");

    }
    @GetMapping("/above-avg-student")
    public ArrayList<Student> displayAboveAvg(){
        ArrayList<Student> students1 =new ArrayList<>();
        double sum =0;
        for (Student x: students){
            sum+=x.getGPA();
        }
        for (Student x: students){
            if (x.getGPA()>(sum/students.size())){
                students1.add(x);
            }
        }
        return students1;

    }



}
