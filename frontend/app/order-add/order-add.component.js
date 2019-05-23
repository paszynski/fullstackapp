angular.
	module('orderAdd').
	component('orderAdd', {
		templateUrl: 'order-add/order-add.template.html',
		controller: ['$scope', '$http', 'REST_URL', 'fetchSizes', 'fetchColors', 'getTextByValue',
			function OrderAddController($scope, $http, REST_URL, fetchSizes, fetchColors, getTextByValue) {
				var self = this;

				self.entries = [];

				self.colors = [];
				fetchColors.then(v => self.colors = v.data).then(v => console.log(v));

				self.sizes = [];
				fetchSizes.then(v => self.sizes = v.data).then(v => console.log(v));

				$scope.getTextByValue = getTextByValue;

				$scope.entry = {
					"color": undefined,
					"size": undefined
				}

				validateForm = function () {
					let result = true;

					if ($scope.orderForm.name.$invalid || $scope.orderForm.age.$invalid) {
						result = false;
					}

					if (self.entries.length == 0) {
						result = false;
					}

					return result;
				}

				validateEntry = function () {
					let result = true;

					if ($scope.entry.color === undefined) {
						result = false;
						$scope.orderForm.color.$setDirty();
					}

					if ($scope.entry.size === undefined) {
						result = false;
						$scope.orderForm.size.$setDirty();
					}

					return result;
				}

				clearEntry = function () {
					$scope.entry = {
						"color": undefined,
						"size": undefined
					}
					$scope.orderForm.color.$setPristine();
					$scope.orderForm.size.$setPristine();
				}

				clearForm = function () {
					clearEntry();
					self.entries = [];
					$scope.orderForm.$setPristine();
					$scope.order = {
						"name": undefined,
						"age": undefined
					}
				}

				$scope.saveEntry = function () {
					if (!validateEntry()) {
						return;
					}

					self.entries.push({
						"color": $scope.entry.color,
						"size": $scope.entry.size
					});

					clearEntry();
				}

				$scope.sendOrder = function () {
					if (!validateForm()) {
						return;
					}

					$http.post(REST_URL +'/orders', {
								"name": $scope.order.name,
								"age": $scope.order.age,
								"entries": self.entries
							},{
								"headers": {
									  'Content-Type': 'application/json'
								}
							}
						)
						.then(
							function successCallback(response) {
								console.log(response);
								clearForm();
								alert("Zamówienie zostało pomyślnie wysłane");
							},
							function errorCallback(response) {
								console.log(response);
								alert("Wystąpił błąd podczas składania zamówienia!");
							}
						);
				}
			}
		]
	})