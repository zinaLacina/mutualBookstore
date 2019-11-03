import React, { Component } from 'react';
import "./ListBook.css";
import { connect } from "react-redux";
import { createBookmark } from "../../actions/bookmarkActions";
import PropTypes from "prop-types";

/**
 *  @Lading class
 * Here the Component which will show the element on the welcome page of the website.
 * It takes as props the list of @books coming from the backend.
 * And also it handle the bookmark action @bookmarkBook triggered by the user. 
 * It will parse the different information and call the action @createBookmark from the redux state.
 */
class ListBook extends Component {

    
    // create a bookmark for the login user, if user is not logged in redirect to login page
    bookmarkBook = (bookItem) => {
        if(this.props.security.validToken){
            // console.log(bookItem);
            this.props.createBookmark(bookItem, this.props.history);
        }else{
            this.props.history.push("/login");
        }
        
    }
    truncate = str => {
        return str.length > 100 ? str.substring(0, 80) + "..." : str;
    }

    // JSX element which shows a message to the user if nothing found.
    noBookFoud = (books) => {
        return Object.keys(books.books).length <= 0 && <div className="alert alert-danger" role="alert" style={{width:"100%"}}>No book found</div>;
    }

    // Show the list of stars
    showStars = (bookRating) => {
        let stars = [];
        for(let i=0; i< bookRating; i++){
            stars.push(<li className="list-inline-item m-0" key={i}><i className="fa fa-star text-success"></i></li>);
        }
        return stars;
    }

    render() {
        const { books } = this.props;
        // console.log(books);
        let img = "data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%22286%22%20height%3D%22180%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20viewBox%3D%220%200%20286%20180%22%20preserveAspectRatio%3D%22none%22%3E%3Cdefs%3E%3Cstyle%20type%3D%22text%2Fcss%22%3E%23holder_16e2bd68792%20text%20%7B%20fill%3Argba(255%2C255%2C255%2C.75)%3Bfont-weight%3Anormal%3Bfont-family%3AHelvetica%2C%20monospace%3Bfont-size%3A14pt%20%7D%20%3C%2Fstyle%3E%3C%2Fdefs%3E%3Cg%20id%3D%22holder_16e2bd68792%22%3E%3Crect%20width%3D%22286%22%20height%3D%22180%22%20fill%3D%22%23777%22%3E%3C%2Frect%3E%3Cg%3E%3Ctext%20x%3D%22107.1875%22%20y%3D%2296.6%22%3E286x180%3C%2Ftext%3E%3C%2Fg%3E%3C%2Fg%3E%3C%2Fsvg%3E";
        let title = "The title";
        let description = "The description";
        
        
        return (
            <div className="row">
                <div className="col-lg-12 mx-auto">

                    {/* <!-- List group--> */}
                    <ul className="list-group shadow">
                { Object.keys(books.books).length > 0 && 
                books.books.items != null && 
                books.books.items.map ((book,index) => {
                    let author = "";
                    let image = book.volumeInfo.imageLinks;
                    let tit = book.volumeInfo.title;
                    let desc = book.volumeInfo.description;
                    let auth = book.volumeInfo.authors;
                    let bookRating = book.volumeInfo.averageRating;
                    if(image && image!==null && image !==""){
                        img = image.smallThumbnail;
                    }
                    if(tit && tit!==null) {
                        title = tit;
                    }
                    if(desc && desc!==null && desc !== ""){
                        description = desc;
                    }
                    if(auth && auth!==null && auth !== ""){
                        auth.map((authItem,i) => {
                            if(i<4){
                                author+=" "+authItem+","
                            } 
                            return author;
                        });
                    }
                    
                    
                    // Build the bookmark item
                    const bookmark = {
                        name: title,
                        author: author,
                        userId: "",
                        isbn: "",
                        genre: "",
                        year: book.volumeInfo.publishedDate,
                        url: book.selfLink,
                        bookRating: bookRating
                    };

                    return (

                        // <!-- list group item-->
                        <li className="list-group-item" key={book.id}> 
                        {/* <!-- Custom content--> */}
                        <div className="media align-items-lg-center flex-column flex-lg-row">
                            <img src={img} alt={title} width="100" className="img-responsive" />
                            <div className="media-body order-2 order-lg-1">
                                <h5 className="mt-0 font-weight-bold mb-2 text-uppercase">{title}</h5>
                                <p className="font-italic text-muted mb-0 small">{this.truncate(description)}</p>
                                <div className="d-flex align-items-center justify-content-between mt-1">
                                    <p className="my-2"><em>{author}</em></p>
                                </div>
                            </div>
                            <div className="align-self-start action-element">
                                <button type="button" className="btn btn-outline-success inline-block-el" 
                                title="bookmark" onClick={()=>this.bookmarkBook(bookmark)}>
                                <i className="fas fa-heart"></i>
                                </button>
                                <ul className="list-inline small inline-block-el">
                                    {this.showStars(bookRating).map(item => item)}
                                </ul>

                            </div>
                            
                        </div>
                        {/* <!-- End --> */}
                        </li>);
                })}

                {this.noBookFoud(books)}

                </ul>
                {/* <!-- End List group--> */}
            </div>
        </div>
        )
    }
}
ListBook.propTypes = {
    security: PropTypes.object.isRequired,
    createBookmark: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
  };
  
  const mapStateToProps = state => ({
    security: state.security,
    errors: state.errors
  });
export default connect(
    mapStateToProps,
    { createBookmark }
  )(ListBook);
