<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<body>

<div ng-app="" ng-init="firstName='홍길동'">
  <p>입력 상자에 다음을 입력하십시오.</p>
  <p>이름 : <input type="text" ng-model="firstName"></p>
  <p>당신이 작성한 이름은 다음과 같습니다. : {{ firstName }}</p>
</div>

</body>
</html>
