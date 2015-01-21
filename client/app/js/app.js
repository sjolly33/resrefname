'use strict';

var app = angular.module('MuseumManager',['ngRoute','ui.bootstrap', 'MuseumServices'])

app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider
    .when('/home', {templateUrl: 'view/home.html',controller: 'homeController'})
    .when('/search', {templateUrl: 'view/search.html',controller: 'searchController'})
    .when('/museum/:id', {templateUrl: 'view/museum.html',controller: 'museumController'})
    .when('/worker/:id', {templateUrl: 'view/worker.html',controller: 'workerController'})
    .when('/work/:id', {templateUrl: 'view/work.html',controller: 'workController'})
    .when('/collection/:id', {templateUrl: 'view/collection.html',controller: 'collectionController'})
    .when('/create_museum', {templateUrl: 'view/create_museum.html',controller: 'museumController'})
    .when('/create_worker', {templateUrl: 'view/create_worker.html',controller: 'workerController'})
    .when('/create_work', {templateUrl: 'view/create_work.html',controller: 'workController'})
    .when('/create_collection', {templateUrl: 'view/create_collection.html',controller: 'workerController'})
    .when('/create_picture', {templateUrl: 'view/create_picture.html',controller: 'pictureController'})
    .when('/edit_worker', {templateUrl: 'view/edit_worker.html',controller: 'workerController'})
    .when('/edit_work', {templateUrl: 'view/edit_work.html',controller: 'workController'})
    .when('/edit_collection', {templateUrl: 'view/edit_collection.html',controller: 'collectionController'})
    .when('/edit_museum', {templateUrl: 'view/edit_museum.html',controller: 'museumController'})
    .when('/edit_picture', {templateUrl: 'view/edit_picture.html',controller: 'pictureController'})
    .otherwise({redirectTo:'/home'})
}]);