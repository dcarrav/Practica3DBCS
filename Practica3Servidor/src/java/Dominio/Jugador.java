/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dcarr
 */
@Entity
@Table(name = "JUGADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jugador.findAll", query = "SELECT j FROM Jugador j"),
    @NamedQuery(name = "Jugador.findById", query = "SELECT j FROM Jugador j WHERE j.id = :id"),
    @NamedQuery(name = "Jugador.findByName", query = "SELECT j FROM Jugador j WHERE j.name = :name"),
    @NamedQuery(name = "Jugador.findByPosition", query = "SELECT j FROM Jugador j WHERE j.position = :position"),
    @NamedQuery(name = "Jugador.findByJerseyNumber", query = "SELECT j FROM Jugador j WHERE j.jerseyNumber = :jerseyNumber"),
    @NamedQuery(name = "Jugador.findByDateOfBirth", query = "SELECT j FROM Jugador j WHERE j.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "Jugador.findByNationality", query = "SELECT j FROM Jugador j WHERE j.nationality = :nationality"),
    @NamedQuery(name = "Jugador.findByContractUntil", query = "SELECT j FROM Jugador j WHERE j.contractUntil = :contractUntil"),
    @NamedQuery(name = "Jugador.findByMarketValue", query = "SELECT j FROM Jugador j WHERE j.marketValue = :marketValue")})
public class Jugador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @Size(max = 20)
    @Column(name = "POSITION")
    private String position;
    @Column(name = "JERSEY_NUMBER")
    private Integer jerseyNumber;
    @Size(max = 20)
    @Column(name = "DATE_OF_BIRTH")
    private String dateOfBirth;
    @Size(max = 20)
    @Column(name = "NATIONALITY")
    private String nationality;
    @Size(max = 20)
    @Column(name = "CONTRACT_UNTIL")
    private String contractUntil;
    @Size(max = 20)
    @Column(name = "MARKET_VALUE")
    private String marketValue;
    @OneToMany(mappedBy = "jugId")
    private Collection<Equipo> equipoCollection;

    public Jugador() {
    }

    public Jugador(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(Integer jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getContractUntil() {
        return contractUntil;
    }

    public void setContractUntil(String contractUntil) {
        this.contractUntil = contractUntil;
    }

    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }

    @XmlTransient
    public Collection<Equipo> getEquipoCollection() {
        return equipoCollection;
    }

    public void setEquipoCollection(Collection<Equipo> equipoCollection) {
        this.equipoCollection = equipoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jugador)) {
            return false;
        }
        Jugador other = (Jugador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dominio.Jugador[ id=" + id + " ]";
    }
    
}
