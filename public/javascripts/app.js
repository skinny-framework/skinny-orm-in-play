var app = angular.module('twitter', []);

app.controller("MainController", function($scope, $http, $window) {

  $scope.tweet = {userName: 'Kaz'};

  $scope.refreshTimeline = function() {
    $http.get('/tweets').success(function (tweets) {
      $scope.tweets = tweets;
    });
  };

  $scope.post = function(tweet) {
    $http.post('/tweets', tweet).success(function(data) {
      $scope.errors = [];
      $scope.tweet.text = null;
      $scope.refreshTimeline();
    }).error(function (errors) {
      $scope.errors = errors;
      $scope.refreshTimeline();
    });
  };

  $scope.showTweets = function(user) {
    $http.get('/users/' + user.id + '/tweets').success(function(tweets) {
      $scope.userInModal = user;
      $scope.userInModal.tweets = tweets;
      $('#myModal').modal('toggle');
    });
  };

  $scope.deleteTweet = function(tweet) {
    $http.delete('/tweets/' + tweet.id).success(function(res) {
      $scope.refreshTimeline();
    }).error(function(res) {
      $window.alert('Failed to delete tweet!');
      $scope.refreshTimeline();
    });
  }
});

