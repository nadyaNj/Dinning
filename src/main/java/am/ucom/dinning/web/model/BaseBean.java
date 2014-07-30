package am.ucom.dinning.web.model;

import java.io.Serializable;
import java.util.Date;


public class BaseBean implements Serializable {

    /**
     * default constructor
     */
    public BaseBean() {
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private Date createDate;

    private Date changeDate;

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
     * @return the createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the changeDate
     */
    public Date getChangeDate() {
        return changeDate;
    }

    /**
     * @param changeDate the changeDate to set
     */
    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

}
