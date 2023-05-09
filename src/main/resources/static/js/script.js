angular.module('app', [])
    .controller('failures', function($scope) {
        $scope.items = [
            { alert: 'Alert 1', status: 'Status 1', date: 'Data 1' },
            { alert: 'Alert 2', status: 'Status 2', date: 'Data 2' },
            { alert: 'Alert 3', status: 'Status 3', date: 'Data 3' }
        ];
    });
