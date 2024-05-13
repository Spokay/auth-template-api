## AuthTemplate API

This repository is a template for REST API Authentication and Authorization.

The purpose of this project was to be used in my future school projects, in any project requiring an authentication system.

Currently, it uses a simple in memory database, and I plan to add Spring Profiles for multiple types of Authentication.

## The profiles available for now are:

- <span style='color:green;'>jwt</span> : This profile is the default profile, it uses JWT tokens for Authentication, Authorization and the the application acts as the ressource server as well
- <span style='color:green;'>oauth2-auth-server</span> : This profile run the application as an authorization server only

