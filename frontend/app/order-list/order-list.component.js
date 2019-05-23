angular.
	module('orderList').
	component('orderList', {
		templateUrl: 'order-list/order-list.template.html',
		controller: ['$scope', '$http', 'REST_URL', 'fetchSizes', 'fetchColors', 'getTextByValue',
			function OrderAddController($scope, $http, REST_URL, fetchSizes, fetchColors, getTextByValue) {
				var self = this;

				self.orders = [];

				$scope.loadList = function(){
					$http.get(REST_URL + "/orders").then(v => self.orders = v.data).then(v => console.log(v.data));
				}
				$scope.loadList();				

				self.colors = [];
				fetchColors.then(v => self.colors = v.data).then(v => console.log(v));

				self.sizes = [];
				fetchSizes.then(v => self.sizes = v.data).then(v => console.log(v));

				$scope.getTextByValue = getTextByValue;
			}
		]
	})
