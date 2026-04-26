package org.example.lab5q3.Controller;

import org.example.lab5q3.ApiResponse.ApiResponse;
import org.example.lab5q3.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/Q3")
public class EventController {
    ArrayList<Event> events = new ArrayList<>();

    @PostMapping("/add-event")
    public ApiResponse addEvent(@RequestBody Event event) {
        events.add(event);
        return new ApiResponse("Event has been added");
    }

    @GetMapping("/display-events")
    public ArrayList<Event> displayEvents() {
        return events;
    }

    @PutMapping("/update-event/{id}")
    public ApiResponse updateEvent(@PathVariable int id, @RequestBody Event event) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId() == id) {
                events.set(i, event);
                return new ApiResponse("Event has been updated");

            }
        }
        return new ApiResponse("No event with such ID");


    }

    @DeleteMapping("/delete-event/{id}")
    public ApiResponse deleteEvent(@PathVariable int id) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId() == id) {
                events.remove(i);
                return new ApiResponse("Event has been deleted");

            }
        }
        return new ApiResponse("No event with such ID");

    }

    @PutMapping("/change-event-capacity/{id}/{capacity}")
    public ApiResponse changeEvent(@PathVariable int id, @PathVariable int capacity) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId() == id) {
                events.get(i).setCapacity(capacity);
                return new ApiResponse("Event capacity has been updated");

            }
        }
        return new ApiResponse("No event with such ID");

    }

    @GetMapping("/get-event-by-id/{id}")
    public Object getEventById(@PathVariable int id) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId() == id) {

                return events.get(i);

            }
        }
        return new ApiResponse("No event with such ID");

    }


}
