package org.example.lab5q2.Controller;

import org.example.lab5q2.ApiResponse.ApiResponse;
import org.example.lab5q2.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/Q2")
public class ProjectTrackerController {
    ArrayList<Project> projects = new ArrayList<>();

    @PostMapping("/add-project")
    public ApiResponse addProject(@RequestBody Project project) {
        projects.add(project);
        return new ApiResponse("New Project has been Added");
    }

    @GetMapping("/display-projects")
    public ArrayList<Project> displayProjects() {
        return projects;
    }

    @PutMapping("/update-project/{id}")
    public ApiResponse updateProject(@PathVariable int id, @RequestBody Project project) {
        boolean flag = false;
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getId() == id) {
                projects.set(i, project);
                flag = true;
                break;

            }
        }
        if (!flag) return new ApiResponse("No project with such ID");
        return new ApiResponse("project has been updated");
    }

    @DeleteMapping("/delete-project/{id}")
    public ApiResponse deleteProject(@PathVariable int id) {
        boolean flag = false;
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getId() == id) {
                projects.remove(i);
                flag = true;
                break;

            }
        }
        if (!flag) return new ApiResponse("No project with such ID");
        return new ApiResponse("project has been deleted");
    }

    @PutMapping("/change-status/{id}")
    public ApiResponse changeStatus(@PathVariable int id) {
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getId() == id) {
                if (projects.get(i).getStatus().equalsIgnoreCase("Not done")) {
                    projects.get(i).setStatus("Done");
                } else {
                    projects.get(i).setStatus("Not done");
                }
                return new ApiResponse("Status has been changed to " + projects.get(i).getStatus());


            }
        }
        return new ApiResponse("No project with such ID");

    }

    @GetMapping("/search-by-title/{title}")
    public Object searchByTitle(@PathVariable String title) {

        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getTitle().equalsIgnoreCase(title)) {
                return projects.get(i);


            }
        }
        return new ApiResponse("No project with such title");

    }

    @GetMapping("/search-by-company-name/{companyName}")
    public ArrayList<Project> searchByCompanyName(@PathVariable String companyName) {
        ArrayList<Project> projects1 = new ArrayList<>();
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getCompanyName().equalsIgnoreCase(companyName)) {
                projects1.add(projects.get(i));


            }
        }
        return projects1 ;

    }


}
