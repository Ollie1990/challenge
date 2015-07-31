# challenge
This repo implements a Twitter like API service with the following basic functionality:

1. a call to read the tweets for a given user (include self-tweets and people being followed by user)
an extra “search=” argument can be used to further filter tweets based on keyword
2. a call to get the list of people a user is following as well as the followers of the user
3. a call to start following a user
4. a call to unfollow a user

Please notice that all calls are guarded with an API token in the query string that will serve
to authenticate the calls; if the wrong API token is given, a 401 error is thrown (unauthorized).

Also added handling of 400 and 500 http errors.

Notice that a "expand" (boolean) optional parameter can be added when 
calling to get the list of people a user is following to see full details of those users. Of course
this optional parameter is available also for the followers of the user.
TODO...
