"use strict";
((function() {
  'use strict';

  var MainController = function() {
    var MainController = function MainController($http, $window) {
      this.tweet = {userName: 'Kaz'};
      this.$http = $http;
      this.$window = $window;
    };

    Object.defineProperties(MainController.prototype, {
      refreshTimeline: {
        writable: true,

        value: function() {
          var _this = this;
          this.$http.get('/tweets').success(function(tweets) {
            _this.tweets = tweets;
          });
        }
      },

      postTweet: {
        writable: true,

        value: function(tweet) {
          var _this2 = this;
          this.$http.post('/tweets', tweet).success(function(data) {
            _this2.errors = [];
            _this2.tweet.text = null;
            _this2.refreshTimeline();
          }).error(function(errors) {
            _this2.errors = errors;
            _this2.refreshTimeline();
          });
        }
      },

      showTweets: {
        writable: true,

        value: function(user) {
          var _this3 = this;
          this.$http.get('/users/' + user.id + '/tweets').success(function(tweets) {
            _this3.userInModal = user;
            _this3.userInModal.tweets = tweets;
            $('#myModal').modal('toggle');
          });
        }
      },

      deleteTweet: {
        writable: true,

        value: function(tweet) {
          var _this4 = this;
          this.$http.delete('/tweets/' + tweet.id).success(function(res) {
            _this4.refreshTimeline();
          }).error(function(res) {
            _this4.$window.alert('Failed to delete tweet!');
            _this4.refreshTimeline();
          });
        }
      }
    });

    return MainController;
  }();

  angular.module('twitter', []).controller("MainController", MainController);
}))();

