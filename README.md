How to use Skinny ORM in Play apps
=================================

This is an example app to show you how to use Skinny ORM in Play apps.

### Live Demo

https://skinny-orm-in-play.herokuapp.com/

### Lightbend Activator Template

Play Framework with Skinny ORM

http://www.lightbend.com/activator/template/skinny-orm-in-play

### Play Framework

https://www.playframework.com/

### Skinny ORM

http://skinny-framework.org/documentation/orm.html

## Overview

### How to setup

#### 1. Add skinny-orm to libraryDependenncies

```scala
"org.skinny-framework" %% "skinny-orm" % "2.1.+"
```

#### 2. Add ScalikeJDBC interagtion settings (Skinny ORM is built upon ScalikeJDBC)

You should add a dependency in build.sbt and add a pluing to conf/play.plugins.

See http://scalikejdbc.org/documentation/playframework-support.html

#### 3. DB Migration

If you need db migration, use Play's evolution or [@tototoshi's flyway plugin](https://github.com/tototoshi/play-flyway).

### How to run?

```sh
# brew install npm
npm install
# npm install -g bower
bower install

./gulp build

# brew install sbt
sbt run

# access localhost:9000 from your browser
```

### Model Generator

`task` sub project is ready. Try `sbt task/run generate:model {Entity} {attributes}`.

```sh
sbt "task/run model Follow followerUserId:Long followingUserId:Long follower:Option[User] following:Option[User]"
sbt "task/run reverse-model Follow"
sbt "task/run reverse-model-all"
```

- Option[{Entity}]: belongsTo association
- Seq[{Entity}]: hasMany association
- Seq[{Entity1}{Entity2}]: hasManyThrough association

### Heroku deployment

```sh
heroku login
heroku git:remote -a {heroku app name}
heroku config:set BUILDPACK_URL=https://github.com/heroku/heroku-buildpack-scala
git push heroku master
```

### Examples

Here are some example DAOs.

https://github.com/skinny-framework/skinny-orm-in-play/tree/master/app/models

### More Information

http://skinny-framework.org/documentation/orm.html

or ask us anything here:

https://groups.google.com/forum/#!forum/skinny-framework

### Under The MIT License

(The MIT License)

Copyright (c) skinny-framework.org

