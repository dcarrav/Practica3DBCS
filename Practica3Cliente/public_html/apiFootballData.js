angular.module("appAdministrador", [])
    .constant("baseUrl", "https://www.football-data.org/v1/")
    .controller("controladorAdministrador", function ($scope, $http,$window, baseUrl) { // Inyectamos recursos

    $scope.displayMode = "competiciones"; // Variable que controla la vista
    $scope.getCompeticiones = function() { // Consume operacion GET en SW
        $http({
            method: "GET",
            url: baseUrl+"/competitions",
            
        }).success(function (data) {
            $scope.competiciones = data;
       });
   }  
    
    
    $scope.getEquipos = function (idCompeticion) { // Ejecuta operacion DELETE en SW
        $scope.idComp = idCompeticion;
        $http({
            method: "GET",
            url: baseUrl + "/competitions/"+idCompeticion+"/teams",
            
        }).success(function (data) {
            $scope.displayMode = "equipos";
            $scope.equipos = data.teams;
        });
    }
    
    $scope.getJugadores = function(enlace) { // Ejecuta operacion DELETE en SW
        
        $http({
            method: "GET",
            url: enlace
            
        }).success(function (data) {
            $scope.displayMode = "jugadores";
            $scope.jugadores = data.players;
        });
    }
    
    $scope.anadirJugadores = function(jugadores) { 
        for (var i = 0; i< jugadores.length; i++){
            $scope.createProduct(jugadores[i]);
        }
    }
    
    $scope.createProduct = function (jugador) { // Ejecuta operacion POST en SW
                
                $http({
                    method: "POST",
                    url: "http://localhost:8080/Practica3Servidor/webresources/futbol/jugadores",
                    data: jugador
            
                }).success(function () {
                    $scope.displayMode = "insercion";
                });
    }
    
    $scope.volver = function () { // Ejecuta operacion POST en SW
              $window.location.href = "index.html";
               
    }
    
    
    $scope.getCompeticiones();
});


