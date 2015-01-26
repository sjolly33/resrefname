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

appControllers.controller('workController', ['$scope','$resource', '$routeParams', '$route', '$sce','MuseumService', 'PaintService', 'AuthorService', 'SculptureService',
  function($scope, $resource, $routeParams, $route, $sce , MuseumService, PaintService, AuthorService, SculptureService) {

    console.log('workController');

    $scope.initNewWorkCtrl = function($scope){
     $scope.museumInfo = MuseumService.get({id:$routeParams.id}, function (res, req){

      $scope.specialities = [];
      $scope.specialities_name ="";
      $scope.tags = [];

      $scope.typeWork =
      [
      {id: 1 , name :"Painting"},
      {id: 2 , name :"Sculpture"}/*,
      {id: 3 , name :"Photography"},
      {id: 4 , name :"Drawing"}*/
      ]

/*
      $scope.addTag = function () {
        $scope.tags.push({
          url: ""
        })}

        $scope.removeTag = function () {
          $scope.tags.splice(-1,1)
        }
        */

/*
        $scope.painting = function(){
          $scope.specialities_name = "Painting";
          $scope.specialities =
          [
          {
            name: "Technical work",
            options: [
            {id: 1 , name :"Watercolor"},
            {id: 2 , name :"Gouache"},
            {id: 3 , name :"Oil paints"},
            {id: 4 , name :"Acrylic"},
            {id: 5 , name :"Other"}
            ]
          },
          {
            name: "Sheld work",
            options: [
            {id: 1 , name :"None"},
            {id: 2 , name :"Flax"},
            {id: 3 , name :"Cotton"},
            {id: 4 , name :"Cardboard"},
            {id: 5 , name :"Paper"},
            {id: 6 , name :"Wood"},
            {id: 7 , name :"Other"}
            ]
          }
          ];
        }

        $scope.sculture = function(){
          $scope.specialities_name = "Sculture";
          $scope.specialities =
          [
          {
            name: "Material Work",
            options: [
            {id: 1 , name :"Wax"},
            {id: 2 , name :"Clay"},
            {id: 3 , name :"Plasticine"},
            {id: 4 , name :"Plaster"},
            {id: 5 , name :"Cement"},
            {id: 6 , name :"Stone"},
            {id: 7 , name :"Wood"},
            {id: 8 , name :"Other"}

            ]
          }
          ];
        }

        $scope.photography = function(){
          $scope.specialities_name = "Photography";
          $scope.specialities =
          [
          {
            name: "Material Work",
            options: [
            {id: 1 , name :"None"},
            {id: 2 , name :"Clay"},
            {id: 3 , name :"Aluminium"},
            {id: 4 , name :"Wood"},
            {id: 5 , name :"Cardboard"},
            {id: 6 , name :"Other"}
            ]
          }
          ];
        }

        $scope.drawing = function(){
          $scope.specialities_name = "";
          alert("Function coming soon");
        }

        */


        $scope.save = function($form){

          if($form.$valid){
            if($scope.typeWork_model == "Painting"){
              var new_work = new PaintService();
            }
            if($scope.typeWork_model == "Sculpture"){
              var new_work = new SculptureService();
            }

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
             /*
              if($scope.specialities_name == "Painting" ){
              new_work.particularityTech = "";
              new_work.particularitySupport = "";
            }*/
              //new_work.specialities = $scope.specialities;
              //new_work.tags = $scope.tags;

              console.log(new_work);
              if($scope.typeWork_model == "Painting"){
                PaintService.save(new_work, function(){});
                $scope.museumInfo.paints.push(new_work);
              }

              if($scope.typeWork_model == "Sculpture"){
                new_work.particularitiesSupport = [];
                SculptureService.save(new_work, function(){});
                $scope.museumInfo.sculptures.push(new_work);
              }
              
              
              //

              /*
              var author = new AuthorService();
              author = AuthorService.get({id:$scope.author_work}, function(res, req){
              author.paints.push(new_work);
              AuthorService.put({id:$scope.author_work}, author, function(res, req){});
            }); */

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
alert("PAUSE")
MuseumService.put({id:$routeParams.id}, newMuseum, function(res, req){});

alert("save work in workController");
}

}
})
}



$scope.initViewWorkCtrl = function($scope,$compile, $http){
  // ???????????????????????? COMMENT SAVOIR AVEC QUEL SERVICE CHARGER LE WORK !!!!!! 
 //$scope.work = SculptureService.get({id:$routeParams.id2}, function (res, req){})
 $scope.work = PaintService.get({id:$routeParams.id2}, function (res, req){})
 $scope.museumInfo = MuseumService.get({id:$routeParams.id1}, function (res, req){

  
    $scope.author="";
    for(var i = 0; i < $scope.museumInfo.authors.length; i++){
     for(var j = 0; j < $scope.museumInfo.authors[i].paints.length; j++){
      if($scope.museumInfo.authors[i].paints[j].id == $scope.work.id){
       $scope.work.author= $scope.museumInfo.authors[i].name;
     }
   }
 }

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

 $scope.delete = function(){
  SculptureService.remove({id:$scope.worker.id2}, function (res, req){})
}

}


$scope.editWorkCtrl= function($scope){
  // ???????????????????????? COMMENT SAVOIR AVEC QUEL SERVICE CHARGER LE WORK !!!!!! 
  $scope.work = PaintService.get({id:$routeParams.id2}, function (res, req){})
  $scope.museumInfo = MuseumService.get({id:$routeParams.id1}, function (res, req){

    $scope.typeWork =
    [
    {id: 1 , name :"Painting"},
    {id: 2 , name :"Sculpture"},
    {id: 3 , name :"Photography"},
    {id: 4 , name :"Drawing"}
    ]

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

    alert($scope.author_work);

    if($form.$valid){
      var edit_work = new PaintService();
      edit_work.id = $routeParams.id2;
      edit_work.title = $scope.title_work;
      edit_work.description = $scope.description_work;


      if(typeof($scope.author_work) != "undefined"){
        var author = new AuthorService();
        author = AuthorService.get({id:$scope.author_work}, function(res, req){
          author.paints.push(edit_work);
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
      PaintService.put(edit_work, function(res, req){});

      alert("edit work in editWorkController "+$routeParams.id2);
    }
  }
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
appControllers.controller('workerController', ['$scope', '$resource', '$routeParams', '$route', 'MuseumService', 'AuthorService',
  function($scope, $resource, $routeParams, $route, MuseumService, AuthorService) {

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