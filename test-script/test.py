from urllib.request import urlopen
import json
import sys
import requests

def get_json(url):
	f = urlopen(url)
	charset = f.info().get_param('charset', 'utf8')
	data = f.read()
	decoded = json.loads(data.decode(charset))
	return decoded

print ("These tests are done with a local database. Please refer to the sql script to know the data that populate DB.")
sep="-----------------------------------------------------------------------------"
# ********************* TWEETS *****************************************************
print(sep)
print("***** Get tweets *****")
print("GET: challenge-API/api/tweets   PARAMETERS: userId=1&token=1111222233334444")
url = "http://localhost:8080/challenge-API/api/tweets?userId=1&token=1111222233334444";
print("URL: " + url)
decoded = get_json(url)
print ("RESPONSE:")
print(decoded)
print(sep)

print("***** Get tweets using search key *****")
print("GET: challenge-API/api/tweets   PARAMETERS: userId=1&token=1111222233334444&search=train")
url = "http://localhost:8080/challenge-API/api/tweets?userId=1&token=1111222233334444&search=train";
print("URL: " + url)
decoded = get_json(url)
print ("RESPONSE:")
print(decoded)
print(sep)

print("***** Get tweets given a not valid user *****")
print("GET: challenge-API/api/tweets   PARAMETERS: userId=-12&token=1111222233334444")
url = "http://localhost:8080/challenge-API/api/tweets?userId=-12&token=1111222233334444";
print("URL: " + url)
decoded = get_json(url)
print ("RESPONSE:")
print(decoded)
print(sep)

# ********************* GET FOLLOWING *****************************************************
print("***** Get following given a user *****")
print("GET: challenge-API/api/getFollowing   PARAMETERS: userId=2&token=1234567890123456")
url = "http://localhost:8080/challenge-API/api/getFollowing?userId=2&token=1234567890123456"
print("URL: " + url)
decoded = get_json(url)
print ("RESPONSE:")
print(decoded)
print(sep)

print("***** Get detailed list of following given a user *****")
print("GET: challenge-API/api/getFollowing   PARAMETERS: userId=2&token=1234567890123456&expand=true")
url = "http://localhost:8080/challenge-API/api/getFollowing?userId=2&token=1234567890123456&expand=true"
print("URL: " + url)
decoded = get_json(url)
print ("RESPONSE:")
print(decoded)
print(sep)

print("***** Get following given a not valid user *****")
print("GET: challenge-API/api/getFollowing   PARAMETERS: userId=-12&token=1234567890123456")
url = "http://localhost:8080/challenge-API/api/getFollowing?userId=-12&token=1234567890123456"
print("URL: " + url)
decoded = get_json(url)
print ("RESPONSE:")
print(decoded)
print(sep)

print("***** Get detailed list of following given a not valid user *****")
print("GET: challenge-API/api/getFollowing   PARAMETERS: userId=-12&token=1234567890123456&expand=true")
url = "http://localhost:8080/challenge-API/api/getFollowing?userId=-12&token=1234567890123456&expand=true"
print("URL: " + url)
decoded = get_json(url)
print ("RESPONSE:")
print(decoded)
print(sep)

# ********************* GET FOLLOWERS *****************************************************
print("***** Get followers of a user *****")
print("GET: challenge-API/api/getFollowers   PARAMETERS: userId=1&token=1234567890123456")
url = "http://localhost:8080/challenge-API/api/getFollowers?userId=1&token=1234567890123456"
print("URL: " + url)
decoded = get_json(url)
print ("RESPONSE:")
print(decoded)
print(sep)

print("***** Get detailed list of followers of a given user *****")
print("GET: challenge-API/api/getFollowers  PARAMETERS: userId=1&token=1234567890123456&expand=true")
url = "http://localhost:8080/challenge-API/api/getFollowers?userId=1&token=1234567890123456&expand=true"
print("URL: " + url)
decoded = get_json(url)
print ("RESPONSE:")
print(decoded)
print(sep)

print("***** Get followers of a not valid user *****")
print("GET: challenge-API/api/getFollowers   PARAMETERS: userId=-12&token=1234567890123456")
url = "http://localhost:8080/challenge-API/api/getFollowers?userId=-12&token=1234567890123456"
print("URL: " + url)
decoded = get_json(url)
print ("RESPONSE:")
print(decoded)
print(sep)

print("***** Get detailed list of followers of a not valid user *****")
print("GET: challenge-API/api/getFollowers   PARAMETERS: userId=-12&token=1234567890123456&expand=true")
url = "http://localhost:8080/challenge-API/api/getFollowers?userId=-12&token=1234567890123456&expand=true"
print("URL: " + url)
decoded = get_json(url)
print ("RESPONSE:")
print(decoded)
print(sep)

# ********************* FOLLOW *****************************************************
print("***** Start following a user *****")
print("POST: challenge-API/api/follow   PAYLOAD: {\"follower\": {\"userId\":2, \"followerId\":1},\"token\": 1234567890123456}")
url = "http://localhost:8080/challenge-API/api/follow"
print("URL: " + url)
payload = {"follower": {"userId":2, "followerId":1}, "token": "1234567890123456"}
decoded = requests.post(url, json=payload)
print ("RESPONSE:")
print(decoded.text)
print(sep)

print("***** Start following a user already followed *****")
print("POST: challenge-API/api/follow   PAYLOAD: {\"follower\": {\"userId\":2, \"followerId\":1},\"token\": 1234567890123456}")
url = "http://localhost:8080/challenge-API/api/follow"
print("URL: " + url)
payload = {"follower": {"userId":2, "followerId":1}, "token": "1234567890123456"}
decoded = requests.post(url, json=payload)
print ("RESPONSE:")
print(decoded.text)
print(sep)

print("***** User starts to follow itself *****")
print("POST: challenge-API/api/follow   PAYLOAD: {\"follower\": {\"userId\":1, \"followerId\":1},\"token\": 1234567890123456}")
url = "http://localhost:8080/challenge-API/api/follow"
print("URL: " + url)
payload = {"follower": {"userId":1, "followerId":1}, "token": "1234567890123456"}
decoded = requests.post(url, json=payload)
print ("RESPONSE:")
print(decoded.text)
print(sep)

# ********************* UNFOLLOW *****************************************************
print("***** Start unfollowing a user *****")
print("POST: challenge-API/api/unfollow   PAYLOAD: {\"follower\": {\"userId\":2, \"followerId\":1},\"token\": 1234567890123456}")
url = "http://localhost:8080/challenge-API/api/unfollow"
print("URL: " + url)
payload = {"follower": {"userId":2, "followerId":1}, "token": "1234567890123456"}
decoded = requests.post(url, json=payload)
print ("RESPONSE:")
print(decoded.text)
print(sep)

print("***** Start unfollowing a user already unfollowed *****")
print("POST: challenge-API/api/unfollow   PAYLOAD: {\"follower\": {\"userId\":2, \"followerId\":1},\"token\": 1234567890123456}")
url = "http://localhost:8080/challenge-API/api/unfollow"
print("URL: " + url)
payload = {"follower": {"userId":2, "followerId":1}, "token": "1234567890123456"}
decoded = requests.post(url, json=payload)
print ("RESPONSE:")
print(decoded.text)
print(sep)

print("***** User starts to unfollow itself *****")
print("POST: challenge-API/api/unfollow   PAYLOAD: {\"follower\": {\"userId\":2, \"followerId\":2},\"token\": 1234567890123456}")
url = "http://localhost:8080/challenge-API/api/unfollow"
print("URL: " + url)
payload = {"follower": {"userId":2, "followerId":2}, "token": "1234567890123456"}
decoded = requests.post(url, json=payload)
print ("RESPONSE:")
print(decoded.text)
print(sep)

# ********* OTHER ERROR SCENARIOS 400, 401, 404 *************************************
print("***** 404 endpoint not found *****")
print("GET: challenge-API/api/wrongEndpoint   PARAMETERS: userId=1&token=1111222233334444")
url = "http://localhost:8080/challenge-API/api/wrongEndpoint?userId=1&token=1111222233334444";
print("URL: " + url)
decoded = get_json(url)
print ("RESPONSE:")
print(decoded)
print(sep)

print("***** 400 Bad Request - malformed request (userId must be a long value) *****")
print("GET: challenge-API/api/tweets   PARAMETERS: userId=notValidId&token=1111222233334444")
url = "http://localhost:8080/challenge-API/api/tweets?userId=notValidId&token=1111222233334444";
print("URL: " + url)
decoded = requests.get(url);
print ("RESPONSE:")
print(decoded.text)
print(sep)

print("***** 400 Bad Request - malformed request (users ids not valid) *****")
print("POST: challenge-API/api/follow   PAYLOAD: {\"follower\": {\"userId\":-2, \"followerId\":-3},\"token\": 1234567890123456}")
url = "http://localhost:8080/challenge-API/api/follow"
print("URL: " + url)
payload = {"follower": {"userId":-2, "followerId":-3}, "token": "1234567890123456"}
decoded = requests.post(url, json=payload)
print ("RESPONSE:")
print(decoded.text)
print(sep)

print("***** 401 unauthorized - wrong token *****")
print("GET: challenge-API/api/tweets   PARAMETERS: userId=1&token=wrongtoken")
url = "http://localhost:8080/challenge-API/api/tweets?userId=1&token=wrongtoken";
print("URL: " + url)
decoded = get_json(url)
print ("RESPONSE:")
print(decoded)
print(sep)

sys.exit(0)