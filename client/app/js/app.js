'use strict';

var app=angular.module('qcmApp',['ngRoute', 'QCMControllers', 'QCMServices'])


app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/stats/:id', {
        templateUrl: 'view/stats.html',
        controller: 'statsCtrl'
      }).
      when('/qcm/:id', {
        templateUrl: 'view/qcm.html',
        controller: 'qcmCtrl'
      }).
      when('/accueil', {
        templateUrl: 'view/accueil.html',
        controller: 'accueil'
  	  }).
      otherwise({
        redirectTo: '/accueil'
      });
  	}]);