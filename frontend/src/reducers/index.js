import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import bookReducer from "./bookReducer";
import bookmarkReducer from "./bookmarkReducer";
import securityReducer from "./securityReducer";

export default combineReducers({
  errors: errorReducer,
  books: bookReducer,
  bookmark: bookmarkReducer,
  security: securityReducer
});