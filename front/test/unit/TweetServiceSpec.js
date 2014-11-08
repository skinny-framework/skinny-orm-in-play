describe("Unit: TweetService", function() {

  beforeEach(angular.mock.module('twitter.services'));

  it('should be injected', inject(function(TweetService) {
    expect(TweetService).not.to.equal(null);
  }));

  it('should have #findAll', inject(function(TweetService) {
    var tweets = TweetService.findAll();
    expect(tweets).not.to.equal(null);
  }));

  it('should have #findAllByUserId', inject(function(TweetService) {
    var tweets = TweetService.findAllByUserId(123);
    expect(tweets).not.to.equal(null);
  }));

  it('should have #post', inject(function(TweetService) {
    var tweet = {userName: 'Kaz', text: 'Hello, Twitter!'};
    var resultPromise = TweetService.post(tweet);
    // actually mocked service doesn't fail
    resultPromise.catch(function (error) { fail(error); });
  }));

  it('should have #deleteByTweetId', inject(function(TweetService) {
    var resultPromise = TweetService.deleteByTweetId(123);
    resultPromise.catch(function (error) { fail(error); });
  }));

});

