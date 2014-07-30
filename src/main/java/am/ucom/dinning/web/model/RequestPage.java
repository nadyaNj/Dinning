package am.ucom.dinning.web.model;

import java.util.List;

/**
 * @author arthur
 */
public class RequestPage<T> {

    private Integer pageNumber;

    private String pageCount;

    private List<T> productList;

    /**
     * default constructor
     */
    public RequestPage() {
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

    /**
     * @return the pageCount
     */
    public String getPageCount() {
        return pageCount;
    }

    /**
     * @param pageCount the pageCount to set
     */
    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * @return the productList
     */
    public List<T> getProductList() {
        return productList;
    }

    /**
     * @param productList the productList to set
     */
    public void setProductList(List<T> productList) {
        this.productList = productList;
    }

}
