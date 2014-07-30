package am.ucom.dinning.persistence.domain;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * Base class Domains implements Serializable
 *
 * @author arthur
 */
public class BaseDomain implements Serializable {

    /**
     * default constructor
     */
    protected BaseDomain() {
    }

    /**
     * Default UUID for serialization.
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private Timestamp createdAt;

    private Timestamp changedAt;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the createdAt
     */
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /**
     * @param timestamp the createdAt to set
     */
    public void setCreatedAt(Timestamp timestamp) {
        this.createdAt = timestamp;
    }

    /**
     * @return the changedAt
     */
    public Timestamp getChangedAt() {
        return changedAt;
    }

    /**
     * @param changedAt the changedAt to set
     */
    public void setChangedAt(Timestamp changedAt) {
        this.changedAt = changedAt;
    }

    /**
     * override version method equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof BaseDomain)) {
            return false;
        }
        BaseDomain base = (BaseDomain) obj;

        return this.id.longValue() == base.id.longValue();
    }

}
