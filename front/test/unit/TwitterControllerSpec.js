describe("Unit: TwitterController", function() {

  beforeEach(angular.mock.module('twitter.controllers'));

  it('should be injected', inject(function($controller, TweetService) {
    var controller = $controller('TwitterController', TweetService);
    expect(controller).not.to.equal(null);
  }));

});

