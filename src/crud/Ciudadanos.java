/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crud;

import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author chirispotter
 */
@Entity
@Table(name = "ciudadanos")
@NamedQueries({@NamedQuery(name = "Ciudadanos.findAll", query = "SELECT c FROM Ciudadanos c"), @NamedQuery(name = "Ciudadanos.findById", query = "SELECT c FROM Ciudadanos c WHERE c.id = :id"), @NamedQuery(name = "Ciudadanos.findByDni", query = "SELECT c FROM Ciudadanos c WHERE c.dni = :dni"), @NamedQuery(name = "Ciudadanos.findByNombre", query = "SELECT c FROM Ciudadanos c WHERE c.nombre = :nombre"), @NamedQuery(name = "Ciudadanos.findByApellido", query = "SELECT c FROM Ciudadanos c WHERE c.apellido = :apellido"), @NamedQuery(name = "Ciudadanos.findByFecNac", query = "SELECT c FROM Ciudadanos c WHERE c.fecNac = :fecNac"), @NamedQuery(name = "Ciudadanos.findByTel", query = "SELECT c FROM Ciudadanos c WHERE c.tel = :tel"), @NamedQuery(name = "Ciudadanos.findByCel", query = "SELECT c FROM Ciudadanos c WHERE c.cel = :cel"), @NamedQuery(name = "Ciudadanos.findByEmail", query = "SELECT c FROM Ciudadanos c WHERE c.email = :email"), @NamedQuery(name = "Ciudadanos.findByZona", query = "SELECT c FROM Ciudadanos c WHERE c.zona = :zona"), @NamedQuery(name = "Ciudadanos.findByBarrio", query = "SELECT c FROM Ciudadanos c WHERE c.barrio = :barrio"), @NamedQuery(name = "Ciudadanos.findByCalle", query = "SELECT c FROM Ciudadanos c WHERE c.calle = :calle"), @NamedQuery(name = "Ciudadanos.findByNumero", query = "SELECT c FROM Ciudadanos c WHERE c.numero = :numero"), @NamedQuery(name = "Ciudadanos.findByDpto", query = "SELECT c FROM Ciudadanos c WHERE c.dpto = :dpto"), @NamedQuery(name = "Ciudadanos.findByPiso", query = "SELECT c FROM Ciudadanos c WHERE c.piso = :piso"), @NamedQuery(name = "Ciudadanos.findByCp", query = "SELECT c FROM Ciudadanos c WHERE c.cp = :cp")})
public class Ciudadanos implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "dni")
    private String dni;
    @Lob
    @Column(name = "hdpulgar")
    private byte[] hdpulgar;
    @Lob
    @Column(name = "hdindice")
    private byte[] hdindice;
    @Lob
    @Column(name = "hdmayor")
    private byte[] hdmayor;
    @Lob
    @Column(name = "hdanular")
    private byte[] hdanular;
    @Lob
    @Column(name = "hdmenor")
    private byte[] hdmenor;
    @Lob
    @Column(name = "hipulgar")
    private byte[] hipulgar;
    @Lob
    @Column(name = "hiidice")
    private byte[] hiidice;
    @Lob
    @Column(name = "himayor")
    private byte[] himayor;
    @Lob
    @Column(name = "hianular")
    private byte[] hianular;
    @Lob
    @Column(name = "himenor")
    private byte[] himenor;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "fec_nac")
    @Temporal(TemporalType.DATE)
    private Date fecNac;
    @Column(name = "tel")
    private String tel;
    @Column(name = "cel")
    private String cel;
    @Column(name = "email")
    private String email;
    @Column(name = "zona")
    private String zona;
    @Column(name = "barrio")
    private String barrio;
    @Column(name = "calle")
    private String calle;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "dpto")
    private String dpto;
    @Column(name = "piso")
    private Short piso;
    @Column(name = "cp")
    private String cp;
    @Lob
    @Column(name = "huella")
    private byte[] huella;

    public Ciudadanos() {
    }

    public Ciudadanos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public byte[] getHdpulgar() {
        return hdpulgar;
    }

    public void setHdpulgar(byte[] hdpulgar) {
        this.hdpulgar = hdpulgar;
    }

    public byte[] getHdindice() {
        return hdindice;
    }

    public void setHdindice(byte[] hdindice) {
        this.hdindice = hdindice;
    }

    public byte[] getHdmayor() {
        return hdmayor;
    }

    public void setHdmayor(byte[] hdmayor) {
        this.hdmayor = hdmayor;
    }

    public byte[] getHdanular() {
        return hdanular;
    }

    public void setHdanular(byte[] hdanular) {
        this.hdanular = hdanular;
    }

    public byte[] getHdmenor() {
        return hdmenor;
    }

    public void setHdmenor(byte[] hdmenor) {
        this.hdmenor = hdmenor;
    }

    public byte[] getHipulgar() {
        return hipulgar;
    }

    public void setHipulgar(byte[] hipulgar) {
        this.hipulgar = hipulgar;
    }

    public byte[] getHiidice() {
        return hiidice;
    }

    public void setHiidice(byte[] hiidice) {
        this.hiidice = hiidice;
    }

    public byte[] getHimayor() {
        return himayor;
    }

    public void setHimayor(byte[] himayor) {
        this.himayor = himayor;
    }

    public byte[] getHianular() {
        return hianular;
    }

    public void setHianular(byte[] hianular) {
        this.hianular = hianular;
    }

    public byte[] getHimenor() {
        return himenor;
    }

    public void setHimenor(byte[] himenor) {
        this.himenor = himenor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFecNac() {
        return fecNac;
    }

    public void setFecNac(Date fecNac) {
        this.fecNac = fecNac;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getDpto() {
        return dpto;
    }

    public void setDpto(String dpto) {
        this.dpto = dpto;
    }

    public Short getPiso() {
        return piso;
    }

    public void setPiso(Short piso) {
        this.piso = piso;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
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
        if (!(object instanceof Ciudadanos)) {
            return false;
        }
        Ciudadanos other = (Ciudadanos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "crud.Ciudadanos[id=" + id + "]";
    }
   public byte[] getHuella() {
        return huella;
    }

    public void setHuella(byte[] huella) {
        byte[] oldHuella = this.huella;
        this.huella = huella;
        changeSupport.firePropertyChange("huella", oldHuella, huella);
    }

}
