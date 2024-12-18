package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.ModelLayer.AssignmentTask;
import com.LMS.LMS.ModelLayer.Assignment;
import com.LMS.LMS.ServiceLayer.AssignmentTaskService;
import com.LMS.LMS.ServiceLayer.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/assignment-tasks")
public class AssignmentTaskController {

    @Autowired
    private AssignmentTaskService taskService;

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping("/assignment/{assignmentId}")
    public ResponseEntity<AssignmentTask> addTaskToAssignment(
            @PathVariable Long assignmentId,
            @RequestBody Map<String, Object> taskData) {
        try {
            Assignment assignment = assignmentService.getAssignment(assignmentId)
                    .orElseThrow(() -> new RuntimeException("Assignment not found"));

            AssignmentTask task = new AssignmentTask();
            task.setAssignment(assignment);
            task.setTaskDescription((String) taskData.get("taskDescription"));
            task.setExpectedOutput((String) taskData.get("expectedOutput"));
            task.setPoints((Integer) taskData.get("points"));
            task.setTaskType((String) taskData.get("taskType"));
            task.setAdditionalResources((String) taskData.get("additionalResources"));

            return ResponseEntity.ok(taskService.createTask(task));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<List<AssignmentTask>> getAssignmentTasks(@PathVariable Long assignmentId) {
        try {
            Assignment assignment = assignmentService.getAssignment(assignmentId)
                    .orElseThrow(() -> new RuntimeException("Assignment not found"));
            return ResponseEntity.ok(taskService.getAssignmentTasks(assignment));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        try {
            taskService.deleteTask(taskId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}