import TweetService from './TweetService';
import TwitterController from './TwitterController';

var services = angular.module('twitter.services', [])
  .service('TweetService', TweetService);

var controllers = angular.module('twitter.controllers', [])
  .controller('TwitterController', TwitterController);

export default angular.module('twitter', [
  services.name,
  controllers.name
]);

