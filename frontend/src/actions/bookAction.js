import axios from "axios";
import { GET_BOOKS } from "./types";

/**
 * Get data from the server of type book base on the params @name
 * Once the we get the data check the second params
 * if @order = 0 so ascending sort else @order = 1 descending.
 */ 

export const getBooks = (name, order) => async dispatch => {
  const res = await axios.get(`/api/books/${name}`);
  // Sorting
  res.data.items.sort((a, b) => {
    let aTitle = a.volumeInfo.title.toLowerCase();
    let bTitle = b.volumeInfo.title.toLowerCase();
    if(order===1) {
      // Ascending
      return aTitle < bTitle ? 1 : aTitle > bTitle ? -1 : 0;
    }else{
      // descending
      return aTitle > bTitle ? 1 : aTitle < bTitle ? -1 : 0;
    }
    
  });
  //console.log(res.data.items);
  dispatch({
    type: GET_BOOKS,
    payload: res.data
  });
};

