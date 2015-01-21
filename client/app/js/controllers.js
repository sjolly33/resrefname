
////////////////////////////////////////////////////////////////////////
// MUSEUM
////////////////////////////////////////////////////////////////////////
app.controller('museumController', ['$scope', '$routeParams','$route', 
  function($scope, $resource, $routeParams, $route) {
    console.log('museumController');


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

}]);


////////////////////////////////////////////////////////////////////////
// HOME
////////////////////////////////////////////////////////////////////////
app.controller('homeController', ['$scope','$routeParams', '$route', 
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

      $scope.pictures = [];
      $scope.tags = [];

      $scope.addPicture = function () {
        $scope.pictures.push({
          url: ""
        })}

        $scope.removePicture = function () {
          $scope.pictures.splice(-1,1)
        }

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

}]);