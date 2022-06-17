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
@Table(name = "fares")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fares.findAll", query = "SELECT f FROM Fares f"),
    @NamedQuery(name = "Fares.findByFareid", query = "SELECT f FROM Fares f WHERE f.fareid = :fareid"),
    @NamedQuery(name = "Fares.findByFromtime", query = "SELECT f FROM Fares f WHERE f.fromtime = :fromtime"),
    @NamedQuery(name = "Fares.findByTotime", query = "SELECT f FROM Fares f WHERE f.totime = :totime"),
    @NamedQuery(name = "Fares.findByPrice", query = "SELECT f FROM Fares f WHERE f.price = :price")})
public class Fares implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fareid")
    private Integer fareid;
    @Basic(optional = false)
    @Column(name = "fromtime")
    @Temporal(TemporalType.TIME)
    private Date fromtime;
    @Basic(optional = false)
    @Column(name = "totime")
    @Temporal(TemporalType.TIME)
    private Date totime;
    @Basic(optional = false)
    @Column(name = "price")
    private float price;

    public Fares() {
    }

    public Fares(Integer fareid) {
        this.fareid = fareid;
    }

    public Fares(Integer fareid, Date fromtime, Date totime, float price) {
        this.fareid = fareid;
        this.fromtime = fromtime;
        this.totime = totime;
        this.price = price;
    }

    public Integer getFareid() {
        return fareid;
    }

    public void setFareid(Integer fareid) {
        this.fareid = fareid;
    }

    public Date getFromtime() {
        return fromtime;
    }

    public void setFromtime(Date fromtime) {
        this.fromtime = fromtime;
    }

    public Date getTotime() {
        return totime;
    }

    public void setTotime(Date totime) {
        this.totime = totime;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fareid != null ? fareid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fares)) {
            return false;
        }
        Fares other = (Fares) object;
        if ((this.fareid == null && other.fareid != null) || (this.fareid != null && !this.fareid.equals(other.fareid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pkg.Fares[ fareid=" + fareid + " ]";
    }
    
}
