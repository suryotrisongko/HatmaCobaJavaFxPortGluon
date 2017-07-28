/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cobalogin;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hatma
 */
@Entity
@Table(name = "TABELHATMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tabelhatma.findAll", query = "SELECT t FROM Tabelhatma t")
    , @NamedQuery(name = "Tabelhatma.findByNama", query = "SELECT t FROM Tabelhatma t WHERE t.nama = :nama")})
public class Tabelhatma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NAMA")
    private String nama;

    public Tabelhatma() {
    }

    public Tabelhatma(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nama != null ? nama.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tabelhatma)) {
            return false;
        }
        Tabelhatma other = (Tabelhatma) object;
        if ((this.nama == null && other.nama != null) || (this.nama != null && !this.nama.equals(other.nama))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cobalogin.Tabelhatma[ nama=" + nama + " ]";
    }
    
}
