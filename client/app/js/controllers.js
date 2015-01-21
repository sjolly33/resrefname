
////////////////////////////////////////////////////////////////////////
// MUSEUM
////////////////////////////////////////////////////////////////////////
app.controller('museumController', ['$scope', '$resource', '$routeParams','$route', 'MuseumService',
  function($scope, $resource, $routeParams, $route, MuseumService) {
    console.log('museumController');

    console.log(MuseumService.query())

    // JSON STATIC A CHANGER
    $scope.works_list = [
    {'id' : '2' ,'title' : "La joconde"},
    {'id' : '10' ,'title' : 'Un tableau moche'},
    {'id' : '27'  ,'title' : 'Une statuette'},
    ];

    $scope.workers_list = [
    {'id' : '8' ,'name' : "Zidane"},
    {'id' : '22' ,'name' : 'Picasso'},
    {'id' : '11'  ,'name' : 'Victor Dupin'},
    ];

    $scope.collections_list = [
    {'id' : '28' ,'name' : "Les plus grand tableau du XXe siecle"},
    {'id' : '10' ,'name' : 'Jambon beurre et petit diarée'},
    {'id' : '6'  ,'name' : 'Victor Dupin Collection'},
    ];

    $scope.save = function ($form) {
      if($form.$valid){
    // Recuperaton des donnees du formulaire :
    var new_museum = {};
    new_museum.name = $scope.title_museum;
    new_museum.theme = $scope.theme_museum;
    new_museum.adress = $scope.adress_museum;
    new_museum.information = $scope.information_museum;
    
    console.log(new_museum);
    // Puis sauvegarde :
    alert("save museum in museumController");
  }
}

$scope.editMuseumController = function($scope){

 // MuseumFactory -> recuperer picture en fonction de l'id puis :
 $scope.title_museum = "title_museum";
 $scope.theme_museum = "title_museum";
 $scope.adress_museum = "description_museum";
 $scope.information_museum = "museum_information";

 $scope.edit = function ($form){
  if($form.$valid){
    // Recuperaton des donnees du formulaire :
    var edit_museum = {};
    edit_museum.title = $scope.title_museum;
    edit_museum.theme = $scope.theme_museum;
    edit_museum.adress = $scope.adress_museum;
    edit_museum.information = $scope.information_museum;

    // Puis sauvegarde : 
    console.log(edit_museum);
    alert("edit museum in museumController");
  }
}
}

}]);


////////////////////////////////////////////////////////////////////////
// HOME
////////////////////////////////////////////////////////////////////////
app.controller('homeController', ['$scope', '$resource', '$routeParams', '$route',
  function($scope, $resource, $routeParams, $route) {
    console.log('homeController');

    $scope.museums_list = [
    {'id' : '14' ,'name' : "Musée d'aquitaine"},
    {'id' : '25' ,'name' : 'Le Louvre'},
    {'id' : '2' ,'name' : 'La Chapelle Sixtine'},
    {'id' : '45' ,'name' : 'Musée Burgonde'}
    ];
  }]);


////////////////////////////////////////////////////////////////////////
// SEARCH
////////////////////////////////////////////////////////////////////////
app.controller('searchController', ['$scope','$routeParams', '$route', 
  function($scope, $resource, $routeParams, $route) {
    console.log('searchController');
  }]);



////////////////////////////////////////////////////////////////////////
// WORK
////////////////////////////////////////////////////////////////////////
app.controller('workController', ['$scope','$routeParams', '$route', '$sce',
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
app.controller('workerController', ['$scope','$routeParams', '$route', 
  function($scope, $resource, $routeParams, $route) {
    console.log('workerController');

    $scope.save = function($form){
      if($form.$valid){
    // Recuperaton des donnees du formulaire :
    var new_worker = {};
    new_worker.name = $scope.name_worker;
    new_worker.adress = $scope.adress_worker;

    // Puis sauvegarde : 
    console.log(new_worker);
    alert("save worker in workerController");
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
app.controller('collectionController', ['$scope','$routeParams', '$route', 
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
app.controller('pictureController', ['$scope','$routeParams', '$route', 
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