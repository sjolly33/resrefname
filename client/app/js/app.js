'use strict';

var app = angular.module('MuseumManager',['ngRoute','ui.bootstrap', 'MuseumCtrls', 'MuseumServices'])

app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider
    .when('/home', {templateUrl: 'view/home.html',controller: 'homeController'})
    .when('/search', {templateUrl: 'view/search.html',controller: 'searchController'})
    .when('/museum/:id', {templateUrl: 'view/museum.html',controller: 'museumController'})
    .when('/worker/:id', {templateUrl: 'view/worker.html',controller: 'workerController'})
    .when('/museum/:id1/work/:id2', {templateUrl: 'view/work.html',controller: 'workController'})
    .when('/museum/:id1/collection/:id2', {templateUrl: 'view/collection.html',controller: 'collectionController'})
    .when('/create_museum/', {templateUrl: 'view/create_museum.html',controller: 'museumController'})
    .when('/create_worker/:id', {templateUrl: 'view/create_worker.html',controller: 'workerController'})
    .when('/create_work/:id', {templateUrl: 'view/create_work.html',controller: 'workController'})
    .when('/create_collection/:id', {templateUrl: 'view/create_collection.html',controller: 'workerController'})
    .when('/create_picture/:id', {templateUrl: 'view/create_picture.html',controller: 'pictureController'})
    .when('/edit_worker/:id', {templateUrl: 'view/edit_worker.html',controller: 'workerController'})
    .when('/museum/:id1/edit_work/:id2', {templateUrl: 'view/edit_work.html',controller: 'workController'})
    .when('/museum/:id1/edit_collection/:id2', {templateUrl: 'view/edit_collection.html',controller: 'collectionController'})
    .when('/edit_museum/:id', {templateUrl: 'view/edit_museum.html',controller: 'museumController'})
    .when('/edit_picture/:id', {templateUrl: 'view/edit_picture.html',controller: 'pictureController'})
    .otherwise({redirectTo:'/home'})
}]);