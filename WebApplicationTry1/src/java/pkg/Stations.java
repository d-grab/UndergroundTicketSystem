/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author danlo
 */
@Entity
@Table(name = "stations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stations.findAll", query = "SELECT s FROM Stations s"),
    @NamedQuery(name = "Stations.findByStationid", query = "SELECT s FROM Stations s WHERE s.stationid = :stationid"),
    @NamedQuery(name = "Stations.findByStationname", query = "SELECT s FROM Stations s WHERE s.stationname = :stationname"),
    @NamedQuery(name = "Stations.findByZone", query = "SELECT s FROM Stations s WHERE s.zone = :zone")})
public class Stations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "stationid")
    private Integer stationid;
    @Basic(optional = false)
    @Column(name = "stationname")
    private String stationname;
    @Basic(optional = false)
    @Column(name = "zone")
    private int zone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkstationid")
    private Collection<Schedule> scheduleCollection;

    public Stations() {
    }

    public Stations(Integer stationid) {
        this.stationid = stationid;
    }

    public Stations(Integer stationid, String stationname, int zone) {
        this.stationid = stationid;
        this.stationname = stationname;
        this.zone = zone;
    }

    public Integer getStationid() {
        return stationid;
    }

    public void setStationid(Integer stationid) {
        this.stationid = stationid;
    }

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    @XmlTransient
    public Collection<Schedule> getScheduleCollection() {
        return scheduleCollection;
    }

    public void setScheduleCollection(Collection<Schedule> scheduleCollection) {
        this.scheduleCollection = scheduleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stationid != null ? stationid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stations)) {
            return false;
        }
        Stations other = (Stations) object;
        if ((this.stationid == null && other.stationid != null) || (this.stationid != null && !this.stationid.equals(other.stationid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pkg.Stations[ stationid=" + stationid + " ]";
    }
    
}
