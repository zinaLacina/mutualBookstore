import React, { Component } from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { logout } from "../../actions/securityActions";

/**
 * Header of the application
 * @Header will show the navbar of the application
 */
class Header extends Component {
  logout() {
    this.props.logout();
    window.location.href = "/";
  }
  render() {
    const { validToken, user } = this.props.security;

    const userIsAuthenticated = (
      <div className="collapse navbar-collapse" id="navbarCollapse">
        <ul className="navbar-nav mr-auto">
          <li className="nav-item">
            <Link className="nav-link text-white font-weight-bold" to="/dashboard">
              Dashboard
            </Link>
          </li>
        </ul>

        <ul className="navbar-nav ml-auto">
          <li className="nav-item">
            <Link className="nav-link text-white" to="/dashboard">
              <i className="fas fa-user-circle mr-1" />
              <strong>{user.fullName}</strong>
            </Link>
          </li>
          <li className="nav-item">
            <Link
              className="nav-link text-white"
              to="/logout"
              onClick={this.logout.bind(this)}
            >
              Logout<strong>Logout</strong>
            </Link>
          </li>
        </ul>
      </div>
    );

    const userIsNotAuthenticated = (
      <div className="collapse navbar-collapse" id="navbarCollapse">
        <ul className="navbar-nav ml-auto">
          <li className="nav-item">
            <Link className="nav-link text-white" to="/register">
              <strong>Sign Up</strong>
            </Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link text-white" to="/login">
              <strong>Login</strong>
            </Link>
          </li>
        </ul>
      </div>
    );

    let headerLinks;

    if (validToken && user) {
      headerLinks = userIsAuthenticated;
    } else {
      headerLinks = userIsNotAuthenticated;
    }

    return (
      <nav className="navbar navbar-expand-md navbar-dark bg-warning">
        <Link className="navbar-brand font-weight-bold text-uppercase" to="/">
            Mutual Book Store
          </Link>
        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
        {headerLinks}
        </nav>
    );
  }
}

Header.propTypes = {
  logout: PropTypes.func.isRequired,
  security: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  security: state.security
});

export default connect(
  mapStateToProps,
  { logout }
)(Header);