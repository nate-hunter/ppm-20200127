package io.njh.ppmtool.services;

import io.njh.ppmtool.domain.Project;
import io.njh.ppmtool.exceptions.ProjectIdException;
import io.njh.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

        // LOGIC WILL BE HERE - ESPECIALLY FOR UPDATE OPERATION. "IS USER THE OWNER TO UPDATE IT?"

        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e){
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists.");
        }
    }

    public Project findProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier((projectId.toUpperCase()));

        if(project == null){
            throw new ProjectIdException("Project ID '" + projectId + "' does not exist.");
        }

        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId);

        if(project == null){
            throw new ProjectIdException("Cannot Project with ID '" + projectId + "'. This project does not exist.");
        }

        projectRepository.delete(project);
    }



}
