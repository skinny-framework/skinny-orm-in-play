export default class TweetService {
  constructor($http) {
    this.$http = $http;
  }

  findAll() {
    return this.$http.get('/tweets');
  }
 
  findAllByUserId(userId) {
    return this.$http.get('/users/' + userId + '/tweets');
  }

  post(tweet) {
    return this.$http.post('/tweets', tweet);
  }

  deleteByTweetId(tweetId) {
    return this.$http.delete('/tweets/' + tweetId);
  }

}

