/*'use strict';

var QCMServices = angular.module('QCMServices', ['ngResource']);

QCMServices.factory('QCM', ['$resource',
  function($resource){

    return $resource('./../api/qcms/:id', {id: '@_id'}, {
      query: {method:'GET', isArray:true},
      get: {method:'GET', isArray:false},
      create: {method:'POST', isArray:false},
      updateQCM: {method:'PUT', isArray:false}
    });
  }]);
*/