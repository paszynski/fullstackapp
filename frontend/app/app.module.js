var app = angular.
	module('frontendApp', [
		'ngRoute',
		'orderList',
		'orderAdd'
	]).
	constant("REST_URL", "http://localhost:9000").
	service('fetchSizes', ['$http','REST_URL', function ($http, REST_URL) {
		return $http.get(REST_URL + "/sizes");
	}]
	).
	service('fetchColors', ['$http','REST_URL', function ($http, REST_URL) {
		return $http.get(REST_URL + "/colors");
	}]
	).
	service('getTextByValue', function(){
		return function(arr, val){
			return (arr.find(function(e){ return e.value == val; }) || { text: "nieznany"}).text;
		}
	});