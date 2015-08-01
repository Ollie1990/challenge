# Challenge: simple Twitter-like API service
This repo implements a Twitter like API service with the following basic functionality:

1. a call to read the tweets for a given user (include self-tweets and people being followed by user)
an extra “search=” argument can be used to further filter tweets based on keyword
2. a call to get the list of people a user is following as well as the followers of the user
3. a call to start following a user
4. a call to unfollow a user

Please notice that a "expand=true" optional parameter can be added when 
calling to get the list of people a user is following to see full details of those users. Of course
this optional parameter is available also for the followers of the user.
Please notice also that all calls are guarded with an API token in the query string that will serve
to authenticate the calls; if the wrong API token is given, a 401 error is thrown (unauthorized).
Also added handling of 400 and 500 http errors.

To implement this service Spring 4 MVC and JDBC annotation-driven were used.
For efficiency reasons a cache system is implemented using [Memcached](http://memcached.org/) for handling token
validation.

This project is compiled and built using Java 8 and Maven.

In order to launch this application you have to rename and set the properties template files inside `src/main/resources`.

###Test script and MySQL dump

Inside `mysql-dump` folder you'll find a sql script that contains the dump of schema and data used to test the system.
It's necessary to deploy the webapp and for running the test script in Python.
About the script, you can find it inside the `test-script` folder. To run it you need Python 3.4 and 
[Requests](http://docs.python-requests.org/en/latest/) library.