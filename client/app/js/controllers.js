//Instanciate Controllers

app.controller('createMuseumController', ['$scope', '$routeParams','$route', 
  function($scope, $resource, $routeParams, $route) {
    /*
   alert($scope.title_museum);
    alert($scope.theme_museum);
    alert($scope.adress_museum);
    alert( $scope.information_museum);
    */

    $scope.save = function ($form) {
      alert("save");
    }

  }]);

app.controller('museumController', ['$scope','$routeParams','$route',
 function($scope, $resource, $routeParams, $route) {
  console.log('museumController');
}]);


app.controller('homeController', ['$scope','$routeParams', '$route', 
  function($scope, $resource, $routeParams, $route) {
    console.log('homeController');
  }]);

app.controller('searchController', ['$scope','$routeParams', '$route', 
  function($scope, $resource, $routeParams, $route) {
    console.log('searchController');
  }]);

app.controller('workController', ['$scope','$routeParams', '$route', 
  function($scope, $resource, $routeParams, $route) {
    console.log('workController');

    $scope.ADD = function($http, $scope){

      $scope.pictures = [];

      $scope.addPicture = function () {
        $scope.pictures.push({
          url: ""
        })}

        $scope.removePicture = function ($form) {
          $scope.pictures.splice(-1,1)
        }
      }
    
  }]);

app.controller('workerController', ['$scope','$routeParams', '$route', 
  function($scope, $resource, $routeParams, $route) {
    console.log('workerController');
  }]);