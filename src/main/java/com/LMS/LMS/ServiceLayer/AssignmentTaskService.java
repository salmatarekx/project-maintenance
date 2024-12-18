package com.LMS.LMS.ServiceLayer;

import com.LMS.LMS.ModelLayer.AssignmentTask;
import com.LMS.LMS.ModelLayer.Assignment;
import com.LMS.LMS.RepositoryLayer.AssignmentTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssignmentTaskService {

    @Autowired
    private AssignmentTaskRepository taskRepository;

    public AssignmentTask createTask(AssignmentTask task) {
        return taskRepository.save(task);
    }

    public List<AssignmentTask> getAssignmentTasks(Assignment assignment) {
        return taskRepository.findByAssignment(assignment);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}