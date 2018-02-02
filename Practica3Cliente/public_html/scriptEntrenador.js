angular.module("appEntrenador", [])
    .constant("baseUrl", "http://localhost:8080/Practica3Servidor/webresources/futbol")
    .controller("controladorEntrenador", function ($scope, $http, baseUrl) { // Inyectamos recursos

    $scope.displayMode = "identificacion";
    
    $scope.entrar = function(id){
        $scope.displayMode = "acceso";
        $scope.id = id;
        
    }
    $scope.mostrarJugadoresLibres = function(id){
        $http.get(baseUrl+"/jugadores").success(function(data){
            $scope.displayMode = "addJugadoresLibres";
            $scope.id = id;
            $scope.jugadoresLibres = data;
        });
    }
    
    $scope.add = function(idUsuario,idJugador){
        $http({
            method: "POST",
            url: baseUrl+"/equipo/"+idUsuario+"/",
            data: idJugador
            
        }).success(function () {
            $scope.mostrarJugadoresLibres(idUsuario);
       });
    }
    
    $scope.borrar = function(idUsuario){
       $http.get(baseUrl+"/equipo/"+idUsuario).success(function(data){
            $scope.displayMode = "borrar";
            $scope.id = idUsuario;
            $scope.jugadoresEquipo = data;
        });
        
    }
    
    $scope.borrarJugador = function(idUsuario,idJugador){
        $http({
            method: "DELETE",
            url: baseUrl+"/equipo/"+idUsuario+"/"+idJugador            
        }).success(function () {
            $scope.borrar(idUsuario);
       });
    }
    
    $scope.volver = function(){
        $scope.displayMode = "identificacion";
    }
    
});


