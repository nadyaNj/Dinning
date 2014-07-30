package am.ucom.dinning.persistence.domain;

import java.util.List;

/**
 * The class have information about product
 *
 * @author arthur
 */
public class ProductDomain extends BaseDomain {

    /**
     * default constructor
     */
    public ProductDomain() {
    }

    /**
     * Default UUID for serialization.
     */
    private static final long serialVersionUID = 1L;

    private String name;

    private Integer price;

    private String description;

    private String imgUrl;

    private String code;

    private Long measurementId;

    private List<ProductTypeDomain> productTypeList;

    private boolean hidden;
    
    private Integer discountPrice;

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * @return the about
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param about the about to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the imgUrl
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * @param imgUrl the imgUrl to set
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the measurmentId
     */
    public Long getMeasurementId() {
        return measurementId;
    }

    /**
     * @param measurmentId the measurmentId to set
     */
    public void setMeasurementId(Long measurmentId) {
        this.measurementId = measurmentId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the productTypeList
     */
    public List<ProductTypeDomain> getProductTypeList() {
        return productTypeList;
    }

    /**
     * @param productTypeList the productTypeList to set
     */
    public void setProductTypeList(List<ProductTypeDomain> productTypeList) {
        this.productTypeList = productTypeList;
    }

    /**
     * @return the hidden
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * @param hidden the hidden to set
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

	/**
	 * @return the discountPrice
	 */
	public Integer getDiscountPrice() {
		return discountPrice;
	}

	/**
	 * @param discountPrice the discountPrice to set
	 */
	public void setDiscountPrice(Integer discountPrice) {
		this.discountPrice = discountPrice;
	}    

}
