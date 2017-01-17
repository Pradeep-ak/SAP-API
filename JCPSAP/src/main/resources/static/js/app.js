var taskManagerModule = angular.module('teamManagerApp', ['ngAnimate']);

taskManagerModule.controller('teamManagerController', function ($scope,$http) {
	
	var urlBase="";
	$scope.toggle=true;
	$scope.selection = [];
	$scope.statuses=['ACTIVE','COMPLETED'];
	$scope.priorities=['HIGH','LOW','MEDIUM'];
	$http.defaults.headers.post["Content-Type"] = "application/json";

    function findAllTasks() {
        //get all tasks and display initially
        $http.get(urlBase + './team/all').
            success(function (response) {
                    $scope.tasks = response;
            });
    }

    findAllTasks();

	//add a new task
	$scope.addTask = function addTask() {
		if($scope.firstName=="" || $scope.lastName=="" || $scope.emailId == "" || $scope.phoneNumber == ""){
			alert("Insufficient Data! Please provide values for task name, description, priortiy and status");
		}
		else{
		 $http.put(urlBase + './team/member/add', {
			 id: 12,
             firstName: $scope.firstName,
             lastName: $scope.lastName,
             emailId: $scope.emailId,
             phoneNumber: $scope.phoneNumber,
			 location: $scope.location
         }).
		  success(function(data, status, headers) {
			 alert("Task added");
             var newTaskUri = headers()["location"];
             console.log("Might be good to GET " + newTaskUri + " and append the task.");
             // Refetching EVERYTHING every time can get expensive over time
             // Better solution would be to $http.get(headers()["location"]) and add it to the list
             findAllTasks();
		    });
		}
	};
	
});