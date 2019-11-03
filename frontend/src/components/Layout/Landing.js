import React, { Component } from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { getBooks } from "../../actions/bookAction";
import './Landing.css';
import ListBook from "./ListBook";


/**
 *  @Lading class
 * Here the welcome page of the website.
 * We import the @getBooks actions and call when the user start to search
 * Once the search value is more than 3 parse the books find into @ListBook Component
 */
class Landing extends Component {

  constructor() {
    super();
    this.state = {
      search: "",
      errors: {}
    };
    this.onChange = this.onChange.bind(this);
    this.keyPressed = this.keyPressed.bind(this);
    this.onFilter = this.onFilter.bind(this);
  }


  onChange(e) {
    this.setState({ search: e.target.value });
  }

  onFilter(e) {
    if(e.target.value==="1"){
      this.props.getBooks(this.state.search, 1);
    }else{
      this.props.getBooks(this.state.search, 0);
    }
  }

  keyPressed(event) {
    if (this.state.search.length >= 3) {
      this.props.getBooks(this.state.search, 0);
    }
  }

  // The sort JSX Element input as a function
  inputSort = () => {
    return (this.state.search.length>=3 && 
        <div className="row">
          <div className="col-sm-9"><span className="float-right">Select your sorting preference</span></div>
          <div className="col-sm-3">
              <select className="form-control mb-3"
              onChange={this.onFilter}>
                <option value="0"> sort</option>
                <option value="1">Descending</option>
                <option value="0">Ascending</option>
            
              </select>
            </div>
        </div>);
  }
  
  render() {
    const { books } = this.props;
    
    return (
      <div className="landing">
        <div className="light-overlay landing-inner text-dark">
          <div className="container">
            <div className="row">
              <div className="col-md-12 text-center">
              {/* <!-- Title welcome --> */}
              <div className="row text-center text-gray mb-5">
                  <div className="col-lg-7 mx-auto">
                    <h1 className="display-4">Books Store</h1>
                    <p className="lead mb-0">Type in the search input to see the your desire book</p>
                  </div>
                </div>
                {/* <!-- End --> */}
                <div className="input-group mb-3">
                  <input type="text" className="form-control" 
                  placeholder="Book name" 
                  name="username"
                  value={this.state.search}
                  onChange={this.onChange}
                  onKeyPress={this.keyPressed}/>
                  <div className="input-group-append">
                    <span className="input-group-text" id="basic-addon2"><i className="fas fa-search"></i></span>
                  </div>
                </div>
                {/* Sort form */}
                  {this.inputSort()}
                {/* End of the sort form */}

                {/* 
                  * List all books here
                  * Parse the list of props. We can use history
                 */}
                {this.state.search.length >=3 && <ListBook {...this.props} books={books} />}
                {/* End of the list */}
                {!this.props.security.validToken &&
                <div className={this.state.search.length > 0 ? 'sign-block-hide': 'sign-block-show'}>
                  <p className="lead">
                    You can sign up and manage your books
                  </p>
                  <hr />
                  <Link className="btn btn-lg btn-warning mr-2 text-white" to="/register">
                    Sign Up
                  </Link>
                  <Link className="btn btn-lg btn-success mr-2 text-white" to="/login">
                    Login
                  </Link>
                </div>
                }
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Landing.propTypes = {
  security: PropTypes.object.isRequired,
  books: PropTypes.object.isRequired,
  getBooks: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
  security: state.security,
  books: state.books
});

export default connect(mapStateToProps, { getBooks })(Landing);