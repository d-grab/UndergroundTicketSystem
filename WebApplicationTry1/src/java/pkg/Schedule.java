/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author danlo
 */
@Entity
@Table(name = "schedule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Schedule.findAll", query = "SELECT s FROM Schedule s"),
    @NamedQuery(name = "Schedule.findByScheduleid", query = "SELECT s FROM Schedule s WHERE s.scheduleid = :scheduleid"),
    @NamedQuery(name = "Schedule.findByArrivaltime", query = "SELECT s FROM Schedule s WHERE s.arrivaltime = :arrivaltime")})
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "scheduleid")
    private Integer scheduleid;
    @Basic(optional = false)
    @Column(name = "arrivaltime")
    @Temporal(TemporalType.TIME)
    private Date arrivaltime;
    @JoinColumn(name = "fkstationid", referencedColumnName = "stationid")
    @ManyToOne(optional = false)
    private Stations fkstationid;

    public Schedule() {
    }

    public Schedule(Integer scheduleid) {
        this.scheduleid = scheduleid;
    }

    public Schedule(Integer scheduleid, Date arrivaltime) {
        this.scheduleid = scheduleid;
        this.arrivaltime = arrivaltime;
    }

    public Integer getScheduleid() {
        return scheduleid;
    }

    public void setScheduleid(Integer scheduleid) {
        this.scheduleid = scheduleid;
    }

    public Date getArrivaltime() {
        return arrivaltime;
    }

    public void setArrivaltime(Date arrivaltime) {
        this.arrivaltime = arrivaltime;
    }

    public Stations getFkstationid() {
        return fkstationid;
    }

    public void setFkstationid(Stations fkstationid) {
        this.fkstationid = fkstationid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scheduleid != null ? scheduleid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schedule)) {
            return false;
        }
        Schedule other = (Schedule) object;
        if ((this.scheduleid == null && other.scheduleid != null) || (this.scheduleid != null && !this.scheduleid.equals(other.scheduleid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pkg.Schedule[ scheduleid=" + scheduleid + " ]";
    }
    
}
