package am.ucom.dinning.persistence.domain;

import java.util.List;

/**
 * Class for search result
 *
 * @author aram
 */
public class ProductSearchResult<T> {

    private List<T> domainList;

    private Integer count;

    private Integer pageNumber;

    /**
     * default constructor
     */
    public ProductSearchResult() {
    }

    /**
     * @return the prodDomainList
     */
    public List<T> getDomainList() {
        return domainList;
    }

    /**
     * @param prodDomainList the prodDomainList to set
     */
    public void setDomainList(List<T> domainList) {
        this.domainList = domainList;
    }

    /**
     * @return the allProductCount
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param allProductCount the allProductCount to set
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return the pageNumber
     */
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * @param pageNumber the pageNumber to set
     */
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

}
