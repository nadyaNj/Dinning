package am.ucom.dinning.web.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Bean class for search representation.
 * @author arthur
 *
 */
public class ProductSearchCriteriaBean extends BaseBean {

	/**
	 * Default UUID for serialization.
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String priceMin;
	
	private String priceMax;
	
	private String description;
	
	private String code;
	
	private boolean hidden;
	
	private String[] typeIdes;
	
	private List<String> deleteAllByIds;
	
	private String searchFilterIds;
	
	/**
	 * Public constructor for instantiation object instance. 
	 */
    public ProductSearchCriteriaBean() {
    }  
    
    /**
     * Initialize object which call the method
     * @param request - HttpServletRequest
     */
    public void initIstance(HttpServletRequest request) {
    	name = request.getParameter("name");
        priceMin = request.getParameter("priceMin");
        priceMax = request.getParameter("priceMax");
        code = request.getParameter("code");
        description = request.getParameter("description");
        String hiddenStr = request.getParameter("hidden");
        hidden = hiddenStr != null;
        typeIdes = request.getParameterValues("type");
    }
    
    /**
	 * @return the typeIdes
	 */
	public String[] getTypeIdes() {
		return typeIdes;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the priceMin
	 */
	public String getPriceMin() {
		return priceMin;
	}

	/**
	 * @return the priceMax
	 */
	public String getPriceMax() {
		return priceMax;
	}

	/**
	 * @param priceMax the priceMax to set
	 */
	public void setPriceMax(String priceMax) {
		this.priceMax = priceMax;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the hidden
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * @return the deleteAllByIds
	 */
	public List<String> getDeleteAllByIds() {
		return deleteAllByIds;
	}

	/**
	 * @return the searchFilterIds
	 */
	public String getSearchFilterIds() {
		return searchFilterIds;
	}

	/**
	 * @param searchFilterIds the searchFilterIds to set
	 */
	public void setSearchFilterIds(String searchFilterIds) {
		this.searchFilterIds = searchFilterIds;
	}
}