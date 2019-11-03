import { GET_BOOKMARKS, GET_BOOKMARK, DELETE_BOOKMARK } from "../actions/types";

const initialState = {
  bookmarks: [],
  bookmark: {}
};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_BOOKMARKS:
      return {
        ...state,
        bookmarks: action.payload
      };

    case GET_BOOKMARK:
      return {
        ...state,
        bookmark: action.payload
      };

    case DELETE_BOOKMARK:
      return {
        ...state,
        bookmarks: state.bookmarks.filter(
            bookmark => bookmark.id !== action.payload
        )
      };
    default:
      return state;
  }
}