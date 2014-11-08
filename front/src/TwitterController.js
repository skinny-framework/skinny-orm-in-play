export default class TwitterController {
  constructor($http, $window, TweetService) {
    this.newTweet = {userName: 'Kaz'};
    this.validationErrors = [];
    this.$window = $window;
    this.service = TweetService;
  }

  refreshTimeline() {
    this.service.findAll().success((tweets) => {
      this.tweets = tweets;
    });
  }

  postTweet(newTweet) {
    this.service.post(newTweet).success((data) => {
      this.newTweet.text = null;
      this.validationErrors = [];
      this.refreshTimeline();
    }).error((errors) => {
      this.validationErrors = errors;
      this.refreshTimeline();
    });
  }

  showTweetsForUser(user) {
    this.service.findAllByUserId(user.id).success((tweets) => {
      this.userInModal = user;
      this.userInModal.tweets = tweets;
      $('#myModal').modal('toggle');
    });
  }

  deleteTweet(tweet) {
    this.service.deleteByTweetId(tweet.id).success((res) => {
      this.refreshTimeline();
    }).error((res) => {
      this.$window.alert('Failed to delete tweet!');
      this.refreshTimeline();
    });
  }

}

