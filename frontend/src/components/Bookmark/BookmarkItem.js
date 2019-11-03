import React, { Component } from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { deleteBookmark } from "../../actions/bookmarkActions";

/**
 * class @BookmarkItem
 * This class will list all the book bookmarked by the user.
 */

class BookmarkItem extends Component {
  onDeleteClick = id => {
    this.props.deleteBookmark(id);
  };

  render() {
    const { book } = this.props;
    return (
      <div className="container">
        <div className="card card-body bg-light mb-3">
          <div className="row">
            <div className="col-2">
              <span className="mx-auto">{book.name}</span>
            </div>
            <div className="col-lg-6 col-md-4 col-8">
              <h6>{book.name}</h6>
              <p>{book.author}</p>
            </div>
            <div className="col-md-4 d-none d-lg-block">
              <ul className="list-group">
                <Link to={`/bookmark/${book.id}`}>
                  <li className="list-group-item board">
                    <i className="fas fa-eye">Show book</i>
                  </li>
                </Link>
                

                <li
                  className="list-group-item delete"
                  onClick={this.onDeleteClick.bind(
                    this,
                    book.id
                  )}
                >
                  <i className="fa fa-minus-circle pr-1"> Delete bookmark</i>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

BookmarkItem.propTypes = {
  deleteBookmark: PropTypes.func.isRequired
};

export default connect(
  null,
  { deleteBookmark }
)(BookmarkItem);