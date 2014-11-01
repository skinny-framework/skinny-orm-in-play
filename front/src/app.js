(() => {
  'use strict';

  class MainController {
    constructor($http, $window) {
      this.tweet = {userName: 'Kaz'};
      this.$http = $http;
      this.$window = $window;
    }

    refreshTimeline() {
      this.$http.get('/tweets').success((tweets) => {
        this.tweets = tweets;
      });
    }

    postTweet(tweet) {
      this.$http.post('/tweets', tweet).success((data) => {
        this.errors = [];
        this.tweet.text = null;
        this.refreshTimeline();
      }).error((errors) => {
        this.errors = errors;
        this.refreshTimeline();
      });
    }

    showTweets(user) {
      this.$http.get('/users/' + user.id + '/tweets').success((tweets) => {
        this.userInModal = user;
        this.userInModal.tweets = tweets;
        $('#myModal').modal('toggle');
      });
    }

    deleteTweet(tweet) {
      this.$http.delete('/tweets/' + tweet.id).success((res) => {
        this.refreshTimeline();
      }).error((res) => {
        this.$window.alert('Failed to delete tweet!');
        this.refreshTimeline();
      });
    }
  }

  angular.module('twitter', []).controller("MainController", MainController);
})();

