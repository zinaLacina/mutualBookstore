import React, { Component } from "react";
import BookmarkItem from "./Bookmark/BookmarkItem";
import { connect } from "react-redux";
import { getBookmarks } from "../actions/bookmarkActions";
import PropTypes from "prop-types";

class Dashboard extends Component {
  componentDidMount() {
    this.props.getBookmarks();
  }

  render() {
    const { bookmarks } = this.props.bookmark;
    console.log(bookmarks);
    return (
      <div className="books">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h4 className="display-5 text-center">My Bookmarked Books</h4>

              <hr />
              {bookmarks.length > 0 && bookmarks.map(book => (
                <BookmarkItem key={book.id} book={book} />
              ))}
              {bookmarks.length===0 && 
              <div className="alert alert-danger" role="alert">
                No book found
              </div>
              }
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Dashboard.propTypes = {
  bookmark: PropTypes.object.isRequired,
  getBookmarks: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
  bookmark: state.bookmark
});

export default connect(
  mapStateToProps,
  { getBookmarks }
)(Dashboard);