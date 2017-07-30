# Tips 

[![Build Status](https://travis-ci.org/mattvv/tips.svg?branch=master)](https://travis-ci.org/mattvv/tips)

[![Coverage Status](https://coveralls.io/repos/github/mattvv/tips/badge.svg?branch=master)](https://coveralls.io/github/mattvv/tips?branch=master)

This is a sample project for running java applications on google cloud.
This example is a simple footy tipping application.


## Running Locally

```
mvn clean appengine:devserver -P local
```

## Deploying

```
mvn appengine:update
```