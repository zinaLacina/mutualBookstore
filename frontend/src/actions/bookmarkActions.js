import axios from "axios";
import { GET_ERRORS, GET_BOOKMARK, GET_BOOKMARKS, DELETE_BOOKMARK } from "./types";

/**
 * 
 * @param {*} bookmark 
 * @param {*} history 
 * This function is made to create a bookmark for the logged in user.
 * And if the @createBookmark works it redirect user to the dashboard page automatically
 */
export const createBookmark = (bookmark, history) => async dispatch => {
  try {
    await axios.post("/api/bookmark", bookmark);
    history.push("/dashboard");
    dispatch({
      type: GET_BOOKMARK,
      payload: {}
    });
  } catch (err) {
    // console.log(err.response);
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data
    });
  }
};

/**
 * @getBookmarks get all the books bookmarked by the logged in user
 */
export const getBookmarks = () => async dispatch => {
  const res = await axios.get("/api/bookmark/all");
  dispatch({
    type: GET_BOOKMARKS,
    payload: res.data
  });
};

/**
 * 
 * @param {*} id 
 * @param {*} history 
 * This action @getBookmark take in params the @id of a bookmark.
 * Then get the corresponding bookmark of the logged in user.
 * 
 */
export const getBookmark = (id, history) => async dispatch => {
  try {
    const res = await axios.get(`/api/bookmark/${id}`);
    dispatch({
      type: GET_BOOKMARK,
      payload: res.data
    });
  } catch (error) {
    history.push("/dashboard");
  }
};

/**
 * 
 * @param {*} id 
 * This action @deleteBookmark takes in params the @id of bookmark when remove it
 */
export const deleteBookmark= id => async dispatch => {
  if (
    window.confirm(
      "Are you sure? This will delete the bookmark and all the data related to it"
    )
  ) {
    await axios.delete(`/api/bookmark/${id}`);
    dispatch({
      type: DELETE_BOOKMARK,
      payload: id
    });
  }
};