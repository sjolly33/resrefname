//Instanciate Controllers
'use strict';

var appControllers = angular.module('QCMControllers', []);

appControllers.controller('statsCtrl', ['$scope', '$routeParams', '$location', 'QCM', 
  function($scope, $routeParams, $location, QCM) {
    $scope.init = function(){
        $scope.answer = []
        $scope.qcm = QCM.get({id:$routeParams.id}, function (res, req){
        angular.forEach(res.questions, function (question, index) {
          var ans1 = 0;
          var ans2 = 0;
          var ans3 = 0;
          var noneAns = 0;
          var answer = [];  
          angular.forEach(question.Result, function (result, i) {
            if(result == 1)
              ans1+=1
            else if(result == 2)
              ans2+=1
            else if(result == 3)
              ans3+=1
            else
              noneAns+=1
          })
          answer.push(ans1);
          answer.push(ans2);
          answer.push(ans3);
          answer.push(noneAns);
          $scope.answer.push(answer);
       })
      })        
    }
  }]);

appControllers.controller('qcmCtrl', ['$scope', '$routeParams', '$location', 'QCM',
  function($scope, $routeParams, $location, QCM) {
    $scope.init = function()
    {
      $scope.qcm = QCM.get({id:$routeParams.id}, function (res, req){
        $scope.questions = res.questions;
        $scope.tmpResponse=[]
        angular.forEach($scope.questions, function(){
          $scope.tmpResponse.push(0);
        })
      })
	   }

    $scope.checkStatus=function(indexe, ans)
    {
      angular.forEach($scope.questions, function(question, index) {
        if(indexe == index){ 
         $scope.tmpResponse[index]=ans;
        }
      });
    }

    $scope.finish=function()
    {
      angular.forEach($scope.questions, function(question, index) { 
         question.Result.push($scope.tmpResponse[index]);
      });
      $scope.qcm.questions = $scope.questions;
      if($scope.qcm.users <= $scope.qcm.questions[0].Result.length){
        $scope.qcm.open = "no";
      }
      QCM.updateQCM({id:$routeParams.id}, $scope.qcm, function(res, rep){});
      $location.path("#/accueil")
    }
  }]);


appControllers.controller('accueil', ['$scope', '$resource', '$location', '$routeParams', 'QCM', 
  function($scope, $resource, $location, $routeParams, QCM) {
     $scope.init = function()
      {
        $scope.qcms = QCM.query();
        $scope.questions=[]
      }
     $scope.addQ = function(newQ)
     {
        newQ.Result = []
        $scope.questions.push(newQ);
     } 
     $scope.addQCM = function (newQCM) {
      $scope.res = new QCM();
      $scope.res.title = newQCM.title;
      $scope.res.desc = newQCM.desc;
      newQCM.open = "yes";
      $scope.res.open = newQCM.open;
      $scope.res.users = newQCM.users;
      $scope.res.questions = $scope.questions;
      $scope.qcms.push(newQCM);
      QCM.save($scope.res, function(res, rep){})
      $location.path("#/accueil")
    };
  }]);