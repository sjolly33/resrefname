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

MuseumServices.factory('PaintService', ['$resource',
  function($resource){

    return $resource('./../api/paint/:id', {id: '@id'}, {
      get: {method:'GET', isArray:false},
      post: {method:'POST', isArray:false},
      put: {method:'PUT', isArray:false},
      remove: {method:'DELETE', isArray:false}
    });
  }]);

MuseumServices.factory('SculptureService', ['$resource',
  function($resource){

    return $resource('./../api/sculpture/:id', {id: '@id'}, {
      get: {method:'GET', isArray:false},
      post: {method:'POST', isArray:false},
      put: {method:'PUT', isArray:false},
      remove: {method:'DELETE', isArray:false}
    });
  }]);

MuseumServices.factory('Picture', ['$resource',
  function($resource){

    return $resource('./../api/picture/:id', {id: '@id'}, {
      get: {method:'GET', isArray:false},
      post: {method:'POST', isArray:false},
      put: {method:'PUT', isArray:false},
      remove: {method:'DELETE', isArray:false}
    });
  }]);

MuseumServices.factory('CollectionWork', ['$resource',
  function($resource){

    return $resource('./../api/collectionWork/:id', {id: '@id'}, {
      get: {method:'GET', isArray:false},
      post: {method:'POST', isArray:false},
      put: {method:'PUT', isArray:false},
      remove: {method:'DELETE', isArray:false}
    });
  }]);

MuseumServices.factory('CollectionPicture', ['$resource',
  function($resource){

    return $resource('./../api/collectionPicture/:id', {id: '@id'}, {
      get: {method:'GET', isArray:false},
      post: {method:'POST', isArray:false},
      put: {method:'PUT', isArray:false},
      remove: {method:'DELETE', isArray:false}
    });
  }]);

MuseumServices.factory('ReproductionPaint', ['$resource',
  function($resource){

    return $resource('./../api/reproductionPaint/:id', {id: '@id'}, {
      get: {method:'GET', isArray:false},
      post: {method:'POST', isArray:false},
      put: {method:'PUT', isArray:false},
      remove: {method:'DELETE', isArray:false}
    });
  }]);

MuseumServices.factory('ReproductionSculpture', ['$resource',
  function($resource){

    return $resource('./../api/reproductionSculpture/:id', {id: '@id'}, {
      get: {method:'GET', isArray:false},
      post: {method:'POST', isArray:false},
      put: {method:'PUT', isArray:false},
      remove: {method:'DELETE', isArray:false}
    });
  }]);

