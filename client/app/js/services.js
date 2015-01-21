'use strict';

var MuseumServices = angular.module('MuseumServices', ['ngResource']);

MuseumServices.factory('MuseumService', ['$resource',
  function($resource){

    return $resource('./../api/museum/:id', {
      query: {method:'GET', isArray:true},
      get: {method:'GET', isArray:false},
      post: {method:'POST', isArray:false},
      put: {method:'PUT', isArray:false}
    });
  }]);
