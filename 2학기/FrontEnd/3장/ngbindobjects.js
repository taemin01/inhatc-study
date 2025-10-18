<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<body>
<div ng-app="" ng-init="person={firstName:'홍',lastName:'길동'}">
  <p>당신의 이름은 <span ng-bind="person.lastName"></span> 입니다.</p>
</div>
</body>
</html>
