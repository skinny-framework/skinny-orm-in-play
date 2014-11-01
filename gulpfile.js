var gulp = require('gulp'),
    watch = require('gulp-watch'),
    jshint = require('gulp-jshint'),
    concat = require('gulp-concat'),
    uglify = require('gulp-uglify'),
    es6to5 = require('gulp-6to5');

var srcAppJs = './front/src/**/*.js';

gulp.task('js', function() {
  return gulp.src(srcAppJs)
    .pipe(jshint())
    .pipe(jshint.reporter('jshint-stylish'))
    .pipe(es6to5())
    .pipe(gulp.dest('public/javascripts/'));
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
gulp.task('build', ['assets:js', 'assets:css', 'assets:fonts', 'js']);

gulp.task('watch', function() {
  watching = true;
  gulp.watch(srcAppJs, ['js']);
});

gulp.task('default', ['build', 'watch']);

