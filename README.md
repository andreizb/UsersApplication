# Users application

### Description

Backend for an application that keeps track of users based on their county and locality.
Below are the summarized implementation details:

* JWT-based authorization, with a basic authentication using a predefined account.
* Localities and counties are loaded in the database from the CSV files provided as resources.
* We can fully interact only with the users table. Moreover, we can get a list of all the localities from a given county.
* Users with duplicate emails are not allowed. Users must have the required data: full name, email, county, locality.
* Special checks are done to ensure that the locality is part of the county when an user is added or updated.

### Guidelines

The following guidelines are provided in order to test the application, which should be used with the Postman collection
provided as a resource:

* Authentication:
    * Use the "JWT authentication" request as it is to get an authorization token.
    * Use this token to perform any of the further tests, as no other endpoint is accessible without a valid token.
    

* Get all localities from a county based on the county code:
    * Using the according request from our collection, change the county code (provided: "AR").
  

* Get all users from a given locality/county:
    * Using the according request, change the locality/county ID.
    

* Adding user to database:
    * Using the according request, change its body to add a new user.

    
* Update user data:
    * Using the according request, change its body to update user data.
    * Identical structure as adding an user, however we update an existing entry based on its ID.
    

* Delete a specific user:
    * Using the according request, change the ID of the endpoint to attempt a delete.
    * No error will arise if the user with the given ID doesn't exist in the database.
