var gulp = require('gulp'),
    watch = require('gulp-watch'),
    jshint = require('gulp-jshint'),
    concat = require('gulp-concat'),
    uglify = require('gulp-uglify'),
    browserify = require("browserify"),
    vinylSourceStream = require('vinyl-source-stream'),
    vinylBuffer = require('vinyl-buffer'),
    to5Browserify = require("6to5-browserify"),
    karma = require('karma').server;

var srcAppJs = './front/src/**/*.js';

gulp.task('js:lint', function() {
  return gulp.src(srcAppJs)
    .pipe(jshint())
    .pipe(jshint.reporter('default'));
});

function jsBuild(dest) {
  var bundler = browserify({ 
    entries: ['./front/src/app.js'],
    debug: true 
  });
  return bundler
    .transform(to5Browserify)
    .bundle()
    .on("error", function (err) { console.log("Error : " + err.message); })
    .pipe(vinylSourceStream("app.js"))
    .pipe(vinylBuffer())
    .pipe(gulp.dest(dest));
}

gulp.task('js:build', function() { 
  return jsBuild('public/javascripts/') 
  });

gulp.task('js:test', function (done) {
  jsBuild('front/test/build/');
  karma.start({
    configFile: __dirname + '/front/test/karma.conf.js',
    singleRun: true
  }, done);
});

gulp.task('assets:js', function() {
  return gulp.src([
      'bower_components/jquery/dist/jquery.min.js',
      'bower_components/bootstrap/dist/js/bootstrap.min.js',
      'bower_components/angularjs/angular.min.js'
    ])
    .pipe(concat('assets.js'))
    .pipe(uglify())
    .pipe(gulp.dest('public/javascripts/'));
});

gulp.task('assets:css', function() {
  return gulp.src([
      'bower_components/bootstrap/dist/css/bootstrap.min.css'
    ])
    .pipe(concat('assets.css'))
    .pipe(gulp.dest('public/stylesheets/'));
});
gulp.task('assets:fonts', function() {
  return gulp.src([
      'bower_components/bootstrap/dist/fonts/**/*'
    ])
    .pipe(gulp.dest('public/fonts/'));
});
gulp.task('js', ['js:lint', 'js:build']);
gulp.task('build', ['assets:js', 'assets:css', 'assets:fonts', 'js']);
gulp.task('test', ['js:test']);

gulp.task('watch', function() {
  watching = true;
  gulp.watch(srcAppJs, ['js']);
});

gulp.task('default', ['build', 'watch']);

