package am.ucom.dinning.web.model;

/**
 * Bean class for search representation.
 *
 * @author aram
 */
public class ProductsBean extends BaseBean {

    /**
     * Default UUID for serialization.
     */
    private static final long serialVersionUID = 1L;

    private String name;

    private String price;

    private String imgUrl;

    private String description;

    private String code;

    private String measurementId;

    private boolean hidden;

    private String[] productTypes;
    
    private String productTypesString;
    
    private String discountPrice;

    /**
     * Default constructor for instantiation object instance.
     */
    public ProductsBean() {
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return the measurementId
     */
    public String getMeasurementId() {
        return measurementId;
    }

    /**
     * @param measurementId the measurementId to set
     */
    public void setMeasurementId(String measurementId) {
        this.measurementId = measurementId;
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
     * @return the productTypes
     */
    public String[] getProductTypes() {
        return productTypes;
    }

    /**
     * @param productTypes the productTypes to set
     */
    public void setProductTypes(String[] productTypes) {
        this.productTypes = productTypes;
    }

	/**
	 * @return the productTypesString
	 */
	public String getProductTypesString() {
		return productTypesString;
	}

	/**
	 * @param productTypesString the productTypesString to set
	 */
	public void setProductTypesString(String productTypesString) {
		this.productTypesString = productTypesString;
	}

	/**
	 * @return the discountPrice
	 */
	public String getDiscountPrice() {
		return discountPrice;
	}

	/**
	 * @param discountPrice the discountPrice to set
	 */
	public void setDiscountPrice(String discountPrice) {
		this.discountPrice = discountPrice;
	}  	
	
}