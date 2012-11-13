/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jcandystore.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alexismp
 */
@Entity
@Table(name = "A_SEQUENCE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ASequence.findAll", query = "SELECT a FROM ASequence a"),
    @NamedQuery(name = "ASequence.findBySeqName", query = "SELECT a FROM ASequence a WHERE a.seqName = :seqName"),
    @NamedQuery(name = "ASequence.findByNextId", query = "SELECT a FROM ASequence a WHERE a.nextId = :nextId")})
public class ASequence implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SEQ_NAME", nullable = false, length = 30)
    private String seqName;
    @Basic(optional = false)
    @Column(name = "NEXT_ID", nullable = false)
    private int nextId;

    public ASequence() {
    }

    public ASequence(String seqName) {
        this.seqName = seqName;
    }

    public ASequence(String seqName, int nextId) {
        this.seqName = seqName;
        this.nextId = nextId;
    }

    public String getSeqName() {
        return seqName;
    }

    public void setSeqName(String seqName) {
        this.seqName = seqName;
    }

    public int getNextId() {
        return nextId;
    }

    public void setNextId(int nextId) {
        this.nextId = nextId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seqName != null ? seqName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ASequence)) {
            return false;
        }
        ASequence other = (ASequence) object;
        if ((this.seqName == null && other.seqName != null) || (this.seqName != null && !this.seqName.equals(other.seqName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.candyentities.ASequence[ seqName=" + seqName + " ]";
    }
    
}
