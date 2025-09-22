angular.module('myApp', []).controller('personCtrl', function($scope) {
    $scope.firstName = "홍",
    $scope.lastName = "길동",
    $scope.fullName = function() {
        return $scope.firstName + " " + $scope.lastName;
    }
});
