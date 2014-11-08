// Karma configuration
// http://karma-runner.github.io/0.12/config/configuration-file.html

module.exports = function(config) {
  'use strict';
  config.set({
    autoWatch: true,
    basePath: '../../',
    frameworks: ['jasmine'],
    files: [
      'bower_components/angular/angular.js',
      'bower_components/angular-mocks/angular-mocks.js',
      'node_modules/chai/chai.js',
      'front/test/build/**/*.js', // app will be prepared here by gulp
      'front/test/**/*.js'
    ],
    exclude: [],
    port: 9432,
    browsers: ['PhantomJS'],
    plugins: [
      'karma-phantomjs-launcher',
      'karma-jasmine'
    ],
    singleRun: false,
    colors: true,
    logLevel: config.LOG_INFO,
  });
};
