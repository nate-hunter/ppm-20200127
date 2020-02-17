package io.njh.ppmtool.services;

import io.njh.ppmtool.domain.Backlog;
import io.njh.ppmtool.domain.ProjectTask;
import io.njh.ppmtool.repositories.BacklogRepository;
import io.njh.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){

        // WE WANT ALL PROJ TASKS TO BE ADDED TO A SPECIFIC PROJECT, project != null (IT EXISTS)
        // SET THE BACKLOG TO THE PROJECT TASK << THIS SETS THE RELATIONSHIP


        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        projectTask.setBacklog(backlog);

        Integer BacklogSequence = backlog.getPTSequence();

        BacklogSequence++;
        backlog.setPTSequence(BacklogSequence);

        projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        if(projectTask.getPriority() == null){  // FOR UI, NEED: `projectTask.getPriority() == 0` TO HANDLE THE FORM.
            projectTask.setPriority(3);
        }
        if(projectTask.getStatus() == "" || projectTask.getStatus() == null){
            projectTask.setStatus("TO_DO");     // USE ENUMS HERE? EXPLORE AND REFACTOR LATER
        }

        return projectTaskRepository.save(projectTask);

    }

    public Iterable<ProjectTask> findBacklogById(String id){
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

//    public List<ProjectTask> findBacklogById(String backlog_id) {
//
//    }
}
