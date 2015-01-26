'use strict';
var appControllers = angular.module('MuseumCtrls', ['angularFileUpload']);

////////////////////////////////////////////////////////////////////////
// MUSEUM
////////////////////////////////////////////////////////////////////////
appControllers.controller('museumController', ['$scope', '$resource', '$routeParams','$route', 'MuseumService', '$location', 
  function($scope, $resource, $routeParams, $route, MuseumService, $location) {

    console.log('museumController');

    $scope.initMuseumCtrl = function($scope){

      $scope.works_list = []
      $scope.workers_list = []
      $scope.collections_list = []
      $scope.museum = {id:0, name: null, theme: null, adress: null, information: null};
      $scope.museumInfo = MuseumService.get({id:$routeParams.id}, function (res, req){
       console.log("res");
       console.log(res);
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
       console.log($scope.works_list)
       console.log($scope.workers_list)
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
      $location.path('/museum/'+$routeParams.id);
    }

    $scope.editMuseumController = function($scope){
     $scope.title_museum = "title_museum";
     $scope.theme_museum = "title_museum";
     $scope.adress_museum = "description_museum";
     $scope.information_museum = "museum_information";
     $scope.workers = [];
     $scope.museumInfo = MuseumService.get({id:$routeParams.id}, function (res, req){
      console.log(res)
      $scope.title_museum=res.name;
      $scope.theme_museum=res.theme;
      $scope.adress_museum = res.adress;
      $scope.information_museum = res.information;
      $scope.workers = res.authors;
      $scope.paints = res.paints;
      $scope.sculptures = res.sculptures;
      $scope.collectionsPictures = res.collectionsPictures;
      $scope.collectionsWorks = res.collectionsWorks;
    })
     $scope.edit = function ($form){
      if($form.$valid){
        var edit_museum = new MuseumService();
        edit_museum.name = $scope.title_museum;
        edit_museum.theme = $scope.theme_museum;
        edit_museum.adress = $scope.adress_museum;
        edit_museum.information = $scope.information_museum;
        edit_museum.authors = $scope.workers;
        edit_museum.paints = $scope.paints;
        edit_museum.sculptures = $scope.sculptures;
        edit_museum.collectionsPictures = $scope.collectionsPictures;
        edit_museum.collectionsWorks = $scope.collectionsWorks;
        edit_museum.id = $routeParams.id;
        MuseumService.put({id:$routeParams.id}, edit_museum, function(res, rep){});
      }
      $location.path('/museum/'+$routeParams.id);
    }
  }

}]);


////////////////////////////////////////////////////////////////////////
// HOME
////////////////////////////////////////////////////////////////////////
appControllers.controller('homeController', ['$scope', '$resource', '$routeParams', '$route', 'MuseumService', '$location',
  function($scope, $resource, $routeParams, $route, MuseumService, $location) {
    console.log('homeController');

    $scope.museums_list = MuseumService.query()

  }]);


////////////////////////////////////////////////////////////////////////
// SEARCH
////////////////////////////////////////////////////////////////////////
appControllers.controller('searchController', ['$scope','$routeParams', '$route', '$location',
  function($scope, $resource, $routeParams, $route, $location) {
    console.log('searchController');
  }]);



////////////////////////////////////////////////////////////////////////
// WORK
////////////////////////////////////////////////////////////////////////

appControllers.controller('workController', ['$scope','$resource', '$routeParams', '$route', '$sce','MuseumService', 'WorkService', 'AuthorService', '$location',
  function($scope, $resource, $routeParams, $route, $sce , MuseumService, WorkService, AuthorService, $location) {

    console.log('workController');

    $scope.initNewWorkCtrl = function($scope){
     $scope.museumInfo = MuseumService.get({id:$routeParams.id}, function (res, req){

      $scope.specialities = [];
      $scope.specialities_name ="";
      $scope.tags = [];

      $scope.typeWork =
      [
      {id: 1 , name :"paint"},
      {id: 2 , name :"sculpture"}/*,
      {id: 3 , name :"Photography"},
      {id: 4 , name :"Drawing"}*/
      ]
    })

      $scope.save = function($form){
        console.log("save")
       // if($form.$valid){
 
          var new_work = new WorkService();

          new_work.title = $scope.title_work;
          new_work.description = $scope.description_work;
          var myString = $scope.dimension_work.replace(/[^\d.x]/g,'');
          var reg = new RegExp("[x.]+", "g");
          var tab_dimension = myString.split(reg);
          new_work.dimension = tab_dimension; 
          new_work.resume = $scope.resume_work;
          new_work.date = $scope.date_work;
          new_work.pictures = [];
          new_work.type = $scope.typeWork_model;
          console.log(new_work.test);
          console.log($scope.test);

          console.log("new_work");
          console.log(new_work);

          WorkService.save(new_work, function(){});

          if($scope.typeWork_model == "paint"){
            $scope.museumInfo.paints.push(new_work);
          }
          if($scope.typeWork_model == "sculpture"){
            new_work.particularitiesSupport = [];
            $scope.museumInfo.sculptures.push(new_work);
          }

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
          //}
          $location.path('/museum/'+$routeParams.id);
      //  }
      }
    }


$scope.initViewWorkCtrl = function($scope, $compile, $http){
 $scope.work = WorkService.get({id:$routeParams.id2}, function (res, req){
   $scope.museumInfo = MuseumService.get({id:$routeParams.id1}, function (res, req){

    console.log($scope.work)

    $scope.author="";
    for(var i = 0; i < $scope.museumInfo.authors.length; i++){
      if($scope.work.type == "paint"){
     for(var j = 0; j < $scope.museumInfo.authors[i].paints.length; j++){
        if($scope.museumInfo.authors[i].paints[j].id == $scope.work.id){
         $scope.work.author= $scope.museumInfo.authors[i].name;
       }
     }
   }
     if($scope.work.type == "sculpture"){
        for(var j = 0; j < $scope.museumInfo.authors[i].sculptures.length; j++){
         if($scope.museumInfo.authors[i].sculptures[j].id == $scope.work.id){
         $scope.work.author= $scope.museumInfo.authors[i].name;
       }
    }
  }
}
console.log($scope.work.dimension);
var dimension_to_string = "";
for(var i=0;i<$scope.work.dimension.length;i++){
  if(i==0){
    dimension_to_string = $scope.work.dimension[i];
  }else{
    dimension_to_string = dimension_to_string +" x "+$scope.work.dimension[i];
  }
}
$scope.work.dimension = dimension_to_string;

var date = new Date($scope.work.date);
var yyyy = date.getFullYear().toString();
var mm = (date.getMonth()+1).toString(); 
var dd  = (date.getDate()).toString();
date =  yyyy +"/"+ (mm[1]?mm:"0"+mm[0]) +"/"+ (dd[1]?dd:"0"+dd[0]); 
$scope.work.date = date;

console.log($scope.museumInfo);
console.log($scope.work);
console.log($scope.work.id);


})
})
$scope.delete = function(){
  WorkService.remove({id:$scope.worker.id2}, function (res, req){})
}

}


$scope.editWorkCtrl= function($scope){
  $scope.work = WorkService.get({id:$routeParams.id2}, function (res, req){
    $scope.museumInfo = MuseumService.get({id:$routeParams.id1}, function (res, req){
/*
    $scope.typeWork =
    [
    {id: 1 , name :"Painting"},
    {id: 2 , name :"Sculpture"},
    {id: 3 , name :"Photography"},
    {id: 4 , name :"Drawing"}
    ]
    */
    console.log($scope.work);
    console.log($scope.work.resume);

    $scope.title_work = $scope.work.title;
    $scope.description_work = $scope.work.description;
    var dimension_to_string = "";
    for(var i=0;i<$scope.work.dimension.length;i++){
      if(i==0){
        dimension_to_string = $scope.work.dimension[i];
      }else{
        dimension_to_string = dimension_to_string +" x "+$scope.work.dimension[i];
      }
    }
    $scope.dimension_work = dimension_to_string;
    $scope.resume_work = $scope.work.resume;
    var date = new Date($scope.work.date);
    var yyyy = date.getFullYear().toString();
    var mm = (date.getMonth()+1).toString(); 
    var dd  = (date.getDate()).toString();
    date =  yyyy +"-"+ (mm[1]?mm:"0"+mm[0]) +"-"+ (dd[1]?dd:"0"+dd[0]); 
    $scope.date_work = date;
    //$scope.typeWork = $scope.typeWork;
    //$scope.actual_type = $scope.work.type;

/*
 $scope.addTag = function () {
  $scope.tags.push({
    url: ""
  })}

  $scope.removeTag = function () {
    $scope.tags.splice(-1,1)
  }*/


  $scope.edit = function ($form){


    if($form.$valid){
      var edit_work = new WorkService();
      edit_work.id = $routeParams.id2;
      edit_work.title = $scope.title_work;
      edit_work.description = $scope.description_work;


      if(typeof($scope.author_work) != "undefined"){
        var author = new AuthorService();
        author = AuthorService.get({id:$scope.author_work}, function(res, req){
          if($scope.work.type == "paint"){
            author.paints.push(edit_work);
          }
          if($scope.work.type == "sculpture"){
            author.sculptures.push(edit_work);
          }
          AuthorService.put({id:$scope.author_work}, author, function(res, req){});
        }); 
      } 

      var myString = $scope.dimension_work.replace(/[^\d.x]/g,'');
      var reg = new RegExp("[x.]+", "g");
      var tab_dimension = myString.split(reg);
      edit_work.dimension = tab_dimension;
      edit_work.resume = $scope.resume_work;
      edit_work.date = $scope.date_work;
      edit_work.pictures = [];
      edit_work.type = $scope.typeWork_model;

      if($scope.work.type == "sculpture"){
        edit_work.particularitiesSupport = [];
      }

      WorkService.put(edit_work, function(res, req){});

      alert("Work edited ! "+$routeParams.id2);
    }
    $location.path('/museum/'+$routeParams.id1);
  }
})
})
}


$scope.viewPreviousPicture = function(){
  alert("viewPreviousPicture");
}
$scope.viewNextPicture = function(){
  alert("viewNextPicture");
}

}]);


////////////////////////////////////////////////////////////////////////
// WORKER
////////////////////////////////////////////////////////////////////////
appControllers.controller('workerController', ['$scope', '$resource', '$routeParams', '$route', 'MuseumService', 'AuthorService', '$location',
  function($scope, $resource, $routeParams, $route, MuseumService, AuthorService, $location) {

    console.log('workerController');

    $scope.initNewWorkerCtrl = function($scope){
      $scope.museumInfo = MuseumService.get({id:$routeParams.id}, function (res, req){})


      $scope.save = function($form){
        if($form.$valid){

          var new_worker = new AuthorService();
          new_worker.name = $scope.name_worker;
          new_worker.adress = $scope.adress_worker;
          AuthorService.save(new_worker, function(){})
          $scope.museumInfo.authors.push(new_worker); 


        // Pas forcement besoin
        //$scope.museumInfo = MuseumService.get({id:$routeParams.id}, function (res, req){})
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
        console.log(newMuseum)
        console.log($scope.museumInfo)
        MuseumService.put({id:$routeParams.id}, newMuseum, function(res, req){});
      }
      $location.path('/museum/'+$routeParams.id);
    }
  }

  $scope.initViewWorkerCtrl = function($scope){
    $scope.worker = AuthorService.get({id:$routeParams.id}, function (res, req){})
    $scope.delete = function(){
      AuthorService.remove({id:$scope.worker.id}, function (res, req){})
    }
    $scope.editWorkerController = function($scope){
      console.log("workerEdit")
      console.log($scope.worker)
      $scope.name_worker = "name_worker";
      $scope.adress_worker = "adress_worker";
      console.log($routeParams.id)

      $scope.name_worker = $scope.worker.name;
      $scope.adress_worker = $scope.worker.adress;      
      $scope.edit = function ($form){
        if($form.$valid){
          var edit_worker = new AuthorService();
          edit_worker.name = $scope.name_worker;
          edit_worker.adress = $scope.adress_worker;
          edit_worker.id = $scope.worker.id;
        // TODO  : add paint and sculpture refs 
        AuthorService.put({id:$routeParams.id}, edit_worker, function(res, req){})
        console.log(edit_worker);
      }
    }
    $location.path('/museum/'+$routeParams.id);
  }
}

}]);


////////////////////////////////////////////////////////////////////////
// COLLECTION
////////////////////////////////////////////////////////////////////////
appControllers.controller('collectionController', ['$scope', '$resource', '$routeParams', '$route', 'MuseumService', 'CollectionService', 'WorkService', 'PictureService', '$location',
  function($scope, $resource, $routeParams, $route, MuseumService, CollectionService, WorkService, PictureService, $location) {
    console.log('collectionController');

    $scope.initViewCollection = function($scope){
      $scope.museum = {id:null};
      $scope.museum.id = $routeParams.id1;
      $scope.collection = {
        id:0,
        title:null,
        description:null,
        comment:null,
        tags:null,
        refWork:[],
        refPicture:[],
        type:null
      }
      CollectionService.get({id:$routeParams.id2}, function (res, req){
        $scope.collection.id = res.id;
        $scope.collection.title = res.title;
        $scope.collection.description = res.description;
        $scope.collection.comment = res.comment;
        $scope.collection.tags = res.tags;
        $scope.collection.type = res.type;
        if(res.type == "collectionWork"){
          for(var i = 0; i < res.refPaint.length; i++)
            $scope.collection.refWork.push(res.refPaint[i]);
          for(var i = 0; i < res.refSculpture.length; i++)
            $scope.collection.refWork.push(res.refSculpture[i]);
        }
        else{
          for(var i = 0; i < res.refPicture.length; i++)
            $scope.collection.refPicture.push(res.refPicture[i]);
        }
        console.log("$scope.collection");        
        console.log($scope.collection);
        console.log(res);
        return res;
      })
      $scope.delete = function(){
        console.log($routeParams.id2);
        console.log($scope.collection.type);
        CollectionService.remove({id:$routeParams.id2, type:$scope.collection.type}, function (res, req){})
        $location.path('/museum/'+$scope.museum.id);
      }

      $scope.editCollectionController = function($scope){

        $scope.edit = function ($form){
          if($form.$valid){
          var new_collection = new CollectionService();
          new_collection.id = $scope.collection.id;
          new_collection.title = $scope.collection.title;
          new_collection.description = $scope.collection.description;
          new_collection.comment = $scope.collection.comment;
          new_collection.tags =  $scope.collection.tags;
          new_collection.type = $scope.collection.type;

          CollectionService.put({id:new_collection.id}, new_collection, function (res, req){})
            $location.path('/museum/'+$scope.museum.id);
          }
        }
      }
    }

    $scope.initNewCollectionController = function($scope){
      $scope.museum = MuseumService.get({id:$routeParams.id}, function (res, req){});
      $scope.collection = {
        id:0,
        title:null,
        description:null,
        comment:null,
        tags:null,
        refWork:[],
        refPicture:[],
        type:null
      }
      $scope.save = function($form){
        if($form.$valid){
          var new_collection = new CollectionService();
          new_collection.title = $scope.collection.title;
          new_collection.description = $scope.collection.description;
          new_collection.comment = $scope.collection.comment;
          new_collection.tags =  $scope.collection.tags;
          new_collection.type = $scope.collection.type;
          CollectionService.save(new_collection, function (res, req){});
          
          if($scope.collection.type == "collectionWork"){
            $scope.museum.collectionsWorks.push(new_collection);
          }
          else{
            $scope.museum.collectionsPictures.push(new_collection);
          }
          var newMuseum = new MuseumService();
          newMuseum.id = $scope.museum.id;
          newMuseum.name = $scope.museum.name;
          newMuseum.theme = $scope.museum.theme;
          newMuseum.adress = $scope.museum.adress;
          newMuseum.information = $scope.museum.information;
          newMuseum.collectionsPictures = $scope.museum.collectionsPictures;
          newMuseum.collectionsWorks = $scope.museum.collectionsWorks;
          newMuseum.pictures = $scope.museum.pictures;
          newMuseum.authors = $scope.museum.authors;
          newMuseum.sculptures = $scope.museum.sculptures;
          newMuseum.paints = $scope.museum.paints;
          console.log(newMuseum)
          console.log($scope.museum)
          MuseumService.put({id:$scope.museum.id}, newMuseum, function(res, req){});
        }
        $location.path('/museum/'+$scope.museum.id);
      }
    }
}]);

////////////////////////////////////////////////////////////////////////
// PICTURE
////////////////////////////////////////////////////////////////////////
appControllers.controller('pictureController', ['$scope','$routeParams', '$route', '$upload',
  function($scope, $resource, $routeParams, $route, $upload) {

    $scope.newPictureController = function($scope, $upload){
      $scope.$watch('files', function() {
        $scope.upload = $upload.upload({
          url: 'server/upload/url',
          data: {myObj: $scope.myModelObj},
          file: $scope.files
        }).progress(function(evt) {
          console.log('progress: ' + parseInt(100.0 * evt.loaded / evt.total) + '% file :'+ evt.config.file.name);
        }).success(function(data, status, headers, config) {
          console.log('file ' + config.file.name + 'is uploaded successfully. Response: ' + data);
        });
      })
    }
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
  $location.path('/museum/'+$routeParams.id);
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
  $location.path('/museum/'+$routeParams.id);
}

}]);

