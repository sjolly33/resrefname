'use strict';

var MuseumServices = angular.module('MuseumServices', ['ngResource']);

MuseumServices.factory('MuseumService', ['$resource',
  function($resource){

    return $resource('./../api/museum/:id', {id: '@id'}, {
      query: {method:'GET', isArray:true},
      get: {method:'GET', isArray:false},
      post: {method:'POST', isArray:false},
      put: {method:'PUT', isArray:false},
      remove: {method:'DELETE', isArray:false}
    });
  }]);

MuseumServices.factory('AuthorService', ['$resource',
  function($resource){

    return $resource('./../api/author/:id', {id: '@id'}, {
      get: {method:'GET', isArray:false},
      post: {method:'POST', isArray:false},
      put: {method:'PUT', isArray:false},
      remove: {method:'DELETE', isArray:false}
    });
  }]);

MuseumServices.factory('WorkService', ['$resource',
  function($resource){

    return $resource('./../api/work/:id', {id: '@id'}, {
      get: {method:'GET', isArray:false},
      post: {method:'POST', isArray:false},
      put: {method:'PUT', isArray:false},
      remove: {method:'DELETE', isArray:false}
    });
  }]);

MuseumServices.factory('PictureService', ['$resource',
  function($resource){

    return $resource('./../api/picture/:id', {id: '@id'}, {
      get: {method:'GET', isArray:false},
      post: {method:'POST', isArray:false},
      put: {method:'PUT', isArray:false},
      remove: {method:'DELETE', isArray:false}
    });
  }]);

MuseumServices.factory('CollectionService', ['$resource',
  function($resource){

    return $resource('./../api/collection/:id', {id: '@id'}, {
      get: {method:'GET', isArray:false},
      post: {method:'POST', isArray:false},
      put: {method:'PUT', isArray:false},
      remove: {method:'DELETE', isArray:false}
    });
  }]);

MuseumServices.factory('ReproductionService', ['$resource',
  function($resource){

    return $resource('./../api/reproduction/:id', {id: '@id'}, {
      get: {method:'GET', isArray:false},
      post: {method:'POST', isArray:false},
      put: {method:'PUT', isArray:false},
      remove: {method:'DELETE', isArray:false}
    });
  }]);

