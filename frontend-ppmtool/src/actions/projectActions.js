import axios from "axios";
import { GET_ERRORS, GET_PROJECTS, GET_PROJECT, DELETE_PROJECT } from "./types";

export const createProject = (project, history) => async dispatch => {
  try {
    const res = await axios.post("/api/project", project);
    history.push("/dashboard");
  } catch (error) {
    console.log("ERROR: ", error.response.data.description);
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};

export const getProjects = () => async dispatch => {
  const resp = await axios.get("/api/project/all");
  dispatch({
    type: GET_PROJECTS,
    payload: resp.data
  });
};

export const getProject = (id, history) => async dispatch => {
  try {
    const resp = await axios.get(`/api/project/${id}`);
    dispatch({
      type: GET_PROJECT,
      payload: resp.data
    });
  } catch (error) {
    history.push("/dashboard");
  }
};

export const deleteProject = id => async dispatch => {
  if (
    window.confirm("Are you sure you want to delete this project and its data?")
  ) {
    await axios.delete(`/api/project/${id}`);
    dispatch({
      type: DELETE_PROJECT,
      payload: id
    });
  }
};
