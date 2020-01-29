package io.njh.ppmtool.services;

import io.njh.ppmtool.domain.Project;
import io.njh.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

        // LOGIC WILL BE HERE - ESPECIALLY FOR UPDATE OPERATION. "IS USER THE OWNER TO UPDATE IT?"


        return projectRepository.save(project);
    }


}
