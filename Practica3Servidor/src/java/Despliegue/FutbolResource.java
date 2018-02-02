/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Despliegue;

import Dominio.Equipo;
import Dominio.Jugador;
import Persistencia.EquipoFacadeLocal;
import Persistencia.JugadorFacadeLocal;
import Persistencia.UsuarioFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;


/**
 * REST Web Service
 *
 * @author dcarr
 */
@Path("futbol")
public class FutbolResource {
    UsuarioFacadeLocal usuarioFacade = lookupUsuarioFacadeLocal();
    JugadorFacadeLocal jugadorFacade = lookupJugadorFacadeLocal();
    EquipoFacadeLocal equipoFacade = lookupEquipoFacadeLocal();
    

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FutbolResource
     */
    public FutbolResource() {
    }

    @POST
    @Path("/jugadores")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addJugador(JsonObject jsonJugador){
        String strJugador = jsonJugador.toString();
        System.out.println(strJugador);
        
        ObjectMapper mapper = new ObjectMapper();
        Jugador jugador = null;
        try {
            jugador = mapper.readValue(strJugador,Jugador.class);
        } catch (IOException ex) {
            Logger.getLogger(FutbolResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        jugadorFacade.create(jugador);
    }
    
    @GET
    @Path("/jugadores")
    @Produces(MediaType.APPLICATION_JSON)
    public Jugador[] getJugadores() {
        List<Jugador> jugadoresEquipo =  jugadorFacade.findAll();
        List<Equipo> listaEquipos =  equipoFacade.findAll();
        List<Jugador> jugadoresLibres = new ArrayList<>();
        boolean jugadorEnEquipo = false;
        
            for(Jugador jugador : jugadoresEquipo){
                for(Equipo equipo: listaEquipos){
                    if(equipo.getJugId().getId().equals(jugador.getId())){
                        jugadorEnEquipo = true;
                    }
                }
                if(!jugadorEnEquipo){
                    jugadoresLibres.add(jugador);
                }
                jugadorEnEquipo = false;
            }
        Jugador[] arrayJugador = new Jugador[jugadoresLibres.size()];
        for(int i =0; i < arrayJugador.length; i++){
            arrayJugador[i] = jugadoresLibres.get(i);
        }
        return arrayJugador;
    }
    @POST
    @Path("/equipo/{idUsuario}")
    @Consumes( MediaType.APPLICATION_JSON)
    public void addJugadorEquipo(@PathParam("idUsuario") String idUsuario, int idJugador){
        Equipo equipo = new Equipo();
        equipo.setId(usuarioFacade.count()+1);
        equipo.setUsId(usuarioFacade.find(idUsuario));
        equipo.setJugId(jugadorFacade.find(idJugador));
        equipoFacade.create(equipo);
        
    }
            
            
    @GET
    @Path("/equipo/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Jugador[] getEquipo(@PathParam("idUsuario") String idUsuario) {
        List<Equipo> listaEquipos =  equipoFacade.findAll();
        List<Jugador> jugadoresEquipo = new ArrayList<Jugador>();
        for(Equipo equipo : listaEquipos){
            if(equipo.getUsId().getId().equals(idUsuario)){
                jugadoresEquipo.add(equipo.getJugId());
                
            }
        }
        Jugador[] arrayJugador = new Jugador[jugadoresEquipo.size()];
        for(int i =0; i < arrayJugador.length; i++){
            arrayJugador[i] = jugadoresEquipo.get(i);
        }
        return arrayJugador;
    }
    
    @DELETE
    @Path("/equipo/{idUsuario}/{jugador}")
    public void delJugador(@PathParam("idUsuario") String idUsuario, @PathParam("jugador") int jugador){
        List<Equipo> listaEquipos =  equipoFacade.findAll();
        for(Equipo equipo : listaEquipos){
            if( equipo.getJugId().getId() == jugador){
                    equipoFacade.remove(equipo);
                
            }
        }
        
        
    }

    private EquipoFacadeLocal lookupEquipoFacadeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (EquipoFacadeLocal) c.lookup("java:global/Practica3Servidor/EquipoFacade!Persistencia.EquipoFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private JugadorFacadeLocal lookupJugadorFacadeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (JugadorFacadeLocal) c.lookup("java:global/Practica3Servidor/JugadorFacade!Persistencia.JugadorFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private UsuarioFacadeLocal lookupUsuarioFacadeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (UsuarioFacadeLocal) c.lookup("java:global/Practica3Servidor/UsuarioFacade!Persistencia.UsuarioFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    
}
