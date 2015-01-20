'use strict';

var app = angular.module('MuseumManager',['ngRoute','ui.bootstrap'])

app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider
    .when('/create_museum', {templateUrl: 'view/create_museum.html',controller: 'createMuseumController'})
    .when('/museum/:id', {templateUrl: 'view/museum.html',controller: 'museumController'})
    .when('/home', {templateUrl: 'view/home.html',controller: 'homeController'})
    .when('/search', {templateUrl: 'view/search.html',controller: 'searchController'})
    .when('/worker/:id', {templateUrl: 'view/worker.html',controller: 'workerController'})
    .when('/work/:id', {templateUrl: 'view/work.html',controller: 'workController'})
    .when('/create_worker', {templateUrl: 'view/create_worker.html',controller: 'workerController'})
    .when('/create_work', {templateUrl: 'view/create_work.html',controller: 'workController'})
    .otherwise({redirectTo:'/home'})
  }]);