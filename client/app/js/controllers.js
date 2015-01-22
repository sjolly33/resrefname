'use strict';
var appControllers = angular.module('MuseumCtrls', []);

////////////////////////////////////////////////////////////////////////
// MUSEUM
////////////////////////////////////////////////////////////////////////
appControllers.controller('museumController', ['$scope', '$resource', '$routeParams','$route', 'MuseumService',
  function($scope, $resource, $routeParams, $route, MuseumService) {
    console.log('museumController');

    $scope.initMuseumCtrl = function($scope){
    $scope.works_list = []
    $scope.workers_list = []
    $scope.collections_list = []
    $scope.museum = {id:0, name: null, theme: null, adress: null, information: null};
    $scope.museumInfo = MuseumService.get({id:$routeParams.id}, function (res, req){
       $scope.museum.id = res.id;
       $scope.museum.name = res.name;
       $scope.museum.theme = res.theme;
       $scope.museum.adress = res.adress;
       $scope.museum.information = res.information;

       $scope.works_list = res.paints;
       $scope.works_list = $scope.works_list.concat(res.sculptures);

       $scope.workers_list = res.authors;

       $scope.collections_list = res.collectionsPictures;
       $scope.collections_list = $scope.collections_list.concat(res.collectionsWorks);
       console.log($scope.museum.id)
       return res;
    });

    $scope.remove = function(){  
      MuseumService.remove({id:$routeParams.id}, function(res, req){})
    }
  }

  $scope.save = function ($form) {
    if($form.$valid){
      var new_museum = new MuseumService();
      new_museum.name = $scope.title_museum;
      new_museum.theme = $scope.theme_museum;
      new_museum.adress = $scope.adress_museum;
      new_museum.information = $scope.information_museum;
      MuseumService.save(new_museum,function(res, req){})
      }
  }

  $scope.editMuseumController = function($scope){

   $scope.title_museum = "title_museum";
   $scope.theme_museum = "title_museum";
   $scope.adress_museum = "description_museum";
   $scope.information_museum = "museum_information";
   $scope.museumInfo = MuseumService.get({id:$routeParams.id}, function (res, req){
      console.log(res)
      $scope.title_museum=res.name;
      $scope.theme_museum=res.theme;
      $scope.adress_museum = res.adress;
      $scope.information_museum = res.information;
   })
   $scope.edit = function ($form){
    if($form.$valid){
      var edit_museum = new MuseumService();
      edit_museum.name = $scope.title_museum;
      edit_museum.theme = $scope.theme_museum;
      edit_museum.adress = $scope.adress_museum;
      edit_museum.information = $scope.information_museum;
      edit_museum.id = $routeParams.id;
      MuseumService.put({id:$routeParams.id}, edit_museum, function(res, rep){});
      }
    }
  }

}]);


////////////////////////////////////////////////////////////////////////
// HOME
////////////////////////////////////////////////////////////////////////
appControllers.controller('homeController', ['$scope', '$resource', '$routeParams', '$route', 'MuseumService',
  function($scope, $resource, $routeParams, $route, MuseumService) {
    console.log('homeController');

    $scope.museums_list = MuseumService.query()

  }]);


////////////////////////////////////////////////////////////////////////
// SEARCH
////////////////////////////////////////////////////////////////////////
appControllers.controller('searchController', ['$scope','$routeParams', '$route', 
  function($scope, $resource, $routeParams, $route) {
    console.log('searchController');
  }]);



////////////////////////////////////////////////////////////////////////
// WORK
////////////////////////////////////////////////////////////////////////
appControllers.controller('workController', ['$scope','$routeParams', '$route', '$sce',
  function($scope, $resource, $routeParams, $route, $sce) {
    console.log('workController');

    $scope.ADD = function($http, $scope){

      $scope.tags = [];

      $scope.addTag = function () {
        $scope.tags.push({
          url: ""
        })}

        $scope.removeTag = function () {
          $scope.tags.splice(-1,1)
        }
      }

      $scope.viewPreviousPicture = function(){
        alert("viewPreviousPicture");
      }

      $scope.viewNextPicture = function(){
        alert("viewNextPicture");
      }

      $scope.forSce = function($sce,$compile){

        $scope.painting = function(){

          var html_content =  
          "<label for='technical_work'> Technical : </label>"+
          "<select class='form-control' ng-model='technical_work' >"+
          "<option>Watercolor</option>"+
          "<option>Gouache</option>"+
          "<option>Oil paints</option>"+
          "<option>Acrylic</option>"+
          "<option>Other</option>"+
          "</select>"+
          "<br>"+
          "<label for='sheld_work'> Sheld : </label>"+
          "<select class='form-control' ng-model='sheld_work'>"+
          "<option>None</option>"+
          "<option>Flax Canvas</option>"+
          "<option>Cotton Canvas</option>"+
          "<option>Cardboard</option>"+
          "<option>Paper</option>"+
          "<option>Wood</option>"+
          "<option>Other</option>"+
          "</select>";

          $scope.myHTML = $sce.trustAsHtml(html_content);

        }

        $scope.sculture = function(){
          var html_content =  
          "<label for='material_work'> Material : </label>"+
          "<select class='form-control' ng-model='material_work' >"+
          "<option>Wax</option>"+
          "<option>Clay</option>"+
          "<option>Plasticine</option>"+
          "<option>Plaster</option>"+
          "<option>Cement</option>"+
          "<option>Stone</option>"+
          "<option>Wood</option>"+
          "<option>Other</option>"+
          "</select>";

          $scope.myHTML = $sce.trustAsHtml(html_content);
        }

        $scope.drawing = function(){
          alert("Rien pour le moment");
        }

        $scope.photography = function(){
         var html_content =  
         "<label for='sheld_work'> Shell : </label>"+
         "<select class='form-control' ng-model='sheld_work' >"+
         "<option>None</option>"+
         "<option>Aluminium</option>"+
         "<option>Wood</option>"+
         "<option>Cardboard</option>"+
         "<option>Other</option>"+
         "</select>";

         $scope.myHTML = $sce.trustAsHtml(html_content);
       }

     }

     $scope.save = function($form){
      if($form.$valid){
    // Recuperaton des donnees du formulaire :
    var new_work = {};
    new_work.title = $scope.title_work;
    new_work.description = $scope.description_work;
    new_work.author = $scope.author_work;
    new_work.dimension = $scope.dimension_work;
    new_work.resume = $scope.resume_work;
    new_work.date = $scope.date_work;

    // A GERER !!
    new_work.specialities = $scope.sheld_work;
    alert($scope.sheld_work);
    alert($scope.myHTML.sheld_work);

    // Puis sauvegarde : 
    console.log(new_work);
    alert("save work in workController");
  }
  
}

$scope.editWorkController = function($scope){

 // MuseumFactory -> recuperer work en fonction de l'id puis :
$scope.title_work = "title_work";
$scope.description_work = "description_work";
$scope.author_work = "author_work";
$scope.dimension_work = "dimension_work";
$scope.resume_work = "resume_work";
$scope.date_work = "date_work";
 $scope.tags = [
 {"text" : "tag1"},
 {"text" : "tag2"},
 {"text" : "tag3"}
 ];

 $scope.addTag = function () {
  $scope.tags.push({
    url: ""
  })}

  $scope.removeTag = function () {
    $scope.tags.splice(-1,1)
  }


 $scope.edit = function ($form){
  if($form.$valid){
    // Recuperaton des donnees du formulaire :
    var edit_work = {};
    edit_work.title = $scope.title_work;
    edit_work.description = $scope.description_work;
    edit_work.author = $scope.author_work;
    edit_work.dimension = $scope.dimension_work;
    edit_work.resume = $scope.resume_work;
    edit_work.date = $scope.date_work;
    edit_work.tags = $scope.tags;

    // Puis sauvegarde : 
    console.log(edit_work);
    alert("edit work in editWorkController");
  }
}
}



}]);


////////////////////////////////////////////////////////////////////////
// WORKER
////////////////////////////////////////////////////////////////////////
appControllers.controller('workerController', ['$scope', '$resource', '$routeParams', '$route', 'MuseumService', 'AuthorService',
  function($scope, $resource, $routeParams, $route, MuseumService, AuthorService) {
    console.log('workerController');
    $scope.museumInfo = MuseumService.get({id:$routeParams.id}, function (res, req){})
    $scope.save = function($form){
      if($form.$valid){
    var new_worker = new AuthorService();
    new_worker.name = $scope.name_worker;
    new_worker.adress = $scope.adress_worker;
    AuthorService.save(new_worker, function(){})
    $scope.museumInfo.authors.push(new_worker); 

    var newMuseum = new MuseumService();
    newMuseum.id = $scope.museumInfo.id;
    newMuseum.name = $scope.museumInfo.name;
    newMuseum.theme = $scope.museumInfo.theme;
    newMuseum.adress = $scope.museumInfo.adress;
    newMuseum.information = $scope.museumInfo.information;
    newMuseum.collectionsPictures = $scope.museumInfo.collectionsPictures;
    newMuseum.collectionsWorks = $scope.museumInfo.collectionsWorks;
    newMuseum.pictures = $scope.museumInfo.pictures;
    newMuseum.authors = $scope.museumInfo.authors;
    newMuseum.sculptures = $scope.museumInfo.sculptures;
    newMuseum.paints = $scope.museumInfo.paints;
    MuseumService.put({id:$routeParams.id}, newMuseum, function(res, req){});
  }
}

$scope.editWorkerController = function($scope){

 // MuseumFactory -> recuperer worker en fonction de l'id puis :
 $scope.name_worker = "name_worker";
 $scope.adress_worker = "adress_worker";

 $scope.edit = function ($form){
  if($form.$valid){
    // Recuperaton des donnees du formulaire :
    var edit_worker = {};
    edit_worker.name = $scope.name_worker;
    edit_worker.adress = $scope.adress_worker;

    // Puis sauvegarde : 
    console.log(edit_worker);
    alert("edit worker in workerController");
  }
}
}
}]);


////////////////////////////////////////////////////////////////////////
// COLLECTION
////////////////////////////////////////////////////////////////////////
appControllers.controller('collectionController', ['$scope','$routeParams', '$route', 
  function($scope, $resource, $routeParams, $route) {
    console.log('collectionController');

    // JSON STATIC A CHANGER
    $scope.works_list = [
    {'id' : '2' ,'title' : "La joconde"},
    {'id' : '10' ,'title' : 'Un tableau moche'},
    {'id' : '27'  ,'title' : 'Une statuette'},
    ];

    $scope.pictures_list = [
    {'id' : '2' ,'title' : "La joconde",'url' :"./img/test1.jpg"},
    {'id' : '10' ,'title' : 'Un tableau moche','url' :"./img/test1.jpg"},
    {'id' : '27'  ,'title' : 'Une statuette','url' :"./img/test1.jpg"},
    ];

    $scope.ADD = function($http, $scope){

      $scope.tags = [];

      $scope.addTag = function () {
        $scope.tags.push({
          url: ""
        })}

        $scope.removeTag = function () {
          $scope.tags.splice(-1,1)
        }
      }

      $scope.save = function ($form){
        if($form.$valid){
    // Recuperaton des donnees du formulaire :
    var new_collection = {};
    new_collection.title = $scope.title_collection;
    new_collection.description = $scope.description_collection;
    new_collection.comments = $scope.comment_collection;
    new_collection.tags = $scope.tags;

    // Puis sauvegarde : 
    console.log(new_collection);
    alert("save collection in collectionController");
  }
}


$scope.editCollectionController = function($scope){

 // MuseumFactory -> recuperer collection en fonction de l'id puis :
 $scope.title_collection = "title_collection";
 $scope.description_collection = "description_collection";
 $scope.comment_collection = "comment_collection";
 $scope.tags = [
 {"text" : "tag1"},
 {"text" : "tag2"},
 {"text" : "tag3"}
 ];

 $scope.addTag = function () {
  $scope.tags.push({
    url: ""
  })}

  $scope.removeTag = function () {
    $scope.tags.splice(-1,1)
  }

  $scope.edit = function ($form){
    if($form.$valid){
    // Recuperaton des donnees du formulaire :
    var new_collection = {};
    new_collection.title = $scope.title_collection;
    new_collection.description = $scope.description_collection;
    new_collection.comments = $scope.comment_collection;
    new_collection.tags = $scope.tags;

    // Puis sauvegarde : 
    console.log(edit_collection);
    alert("edit collection in pictureController");
  }
}

}

}]);

////////////////////////////////////////////////////////////////////////
// PICTURE
////////////////////////////////////////////////////////////////////////
appControllers.controller('pictureController', ['$scope','$routeParams', '$route', 
  function($scope, $resource, $routeParams, $route) {
    console.log('pictureController');

    $scope.ADD = function($http, $scope){

      $scope.tags = [];

      $scope.addTag = function () {
        $scope.tags.push({
          url: ""
        })}

        $scope.removeTag = function () {
          $scope.tags.splice(-1,1)
        }
      }


      $scope.editPictureController = function($scope){

 // MuseumFactory -> recuperer picture en fonction de l'id puis :
 $scope.picture_url = "picture_url";
 $scope.title_picture = "title_picture";
 $scope.description_picture = "description_picture";
 $scope.comment_picture = "comment_picture";
 $scope.tags = [
 {"text" : "tag1"},
 {"text" : "tag2"},
 {"text" : "tag3"}
 ];

 $scope.addTag = function () {
  $scope.tags.push({
    url: ""
  })}

  $scope.removeTag = function () {
    $scope.tags.splice(-1,1)
  }

  $scope.edit = function ($form){
    if($form.$valid){
    // Recuperaton des donnees du formulaire :
    var new_picture = {};
    new_picture.title = $scope.title_picture;
    new_picture.description = $scope.description_picture;
    new_picture.comments = $scope.comment_picture;
    new_picture.tags = $scope.tags;

    // Puis sauvegarde : 
    console.log(edit_picture);
    alert("edit picture in pictureController");
  }
}
}

$scope.save = function ($form){
  if($form.$valid){
    // Recuperaton des donnees du formulaire :
    var new_picture = {};
    new_picture.title = $scope.title_picture;
    new_picture.description = $scope.description_picture;
    new_picture.comments = $scope.comment_picture;
    new_picture.tags = $scope.tags;

    // Puis sauvegarde : 


    console.log(new_picture);
    alert("save picture in pictureController");
  }
}

}]);