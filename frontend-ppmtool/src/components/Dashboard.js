import React, { Component } from "react";
import ProjectItem from "./Project/ProjectItem";
import CreateProjectButton from "./Project/CreateProjectButton";
import { connect } from "react-redux";
import { getProjects } from "../actions/projectActions";
import PropTypes from "prop-types";

class Dashboard extends Component {
  componentDidMount() {
    this.props.getProjects();
  }

  render() {
    // const projectObj = {
    //   projectName: "ProjectName Props",
    //   projectIdentifier: "PROPS",
    //   description: "Description Props"
    // };

    const projects = this.props.project.projects;

    const projectsList = projects.map(project => {
      return <ProjectItem key={project.id} project={project} />;
    });

    return (
      <div>
        <div className="projects">
          <div className="container">
            <div className="row">
              <div className="col-md-12">
                <h1 className="display-4 text-center">:: Projects ::</h1>
                <br />
                <CreateProjectButton />

                <br />
                <hr />

                {projectsList}
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Dashboard.propTypes = {
  project: PropTypes.object.isRequired,
  getProjects: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
  project: state.project
});

export default connect(mapStateToProps, { getProjects })(Dashboard);
