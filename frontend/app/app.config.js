app.config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
    $routeProvider
    .when('/orders/new', {
        template: '<order-add></order-add>'
    })
    .when('/orders', {
        template: '<order-list></order-list>'
    })
    .otherwise({redirectTo: '/orders'});
  }]);