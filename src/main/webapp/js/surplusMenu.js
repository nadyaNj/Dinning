/**
 * function for save surplus menu
 */
function save() {
	$(function() {
		var requestString = requestSurplusMenu('text');
		if (requestString == ""){
			$('#error-prod').replaceWith('<p align="center" style="color: red;" id="error-prod">Please add products to surple menu</p>');
			return false;
		} else {
			$('#error-prod').replaceWith('<p align="center" style="color: red;" id="error-prod"></p>');
		}
		var requestCountString = requestSurplusMenuProductCounts('text');
		$.post("actionServlet", {pageFlag: "surplusMenuList", menuProdIds: requestString, menuProdCounts: requestCountString},function(data){
			$('div#replaceWith').html(data);
		});	
	});
}

/**
 * return products ids list
 * 
 * @param type
 * @returns {String}
 */
function requestSurplusMenu(type){
	var form = document.forms[0];
    var els = form.elements;

    var requestString = '';                                
    for(var i=0; i < els.length; i++) {
   	 var el = els[i];       
        if (el.type == type) {
       	 requestString = requestString  + el.name + "," ;    
        }
           
    }
    return requestString; 
}

/**
 * validation of count
 * 
 * @param value
 * @returns {Boolean}
 */
function is_InputCountValid(value){
	for (i = 0 ; i < value.length ; i++) { 
		if ((value.charAt(i) < '0') || (value.charAt(i) > '9')) {
			return false; 
		}
    } 
    return true; 
}

/**
 * return products counts list
 * 
 * @param type
 * @returns
 */
function requestSurplusMenuProductCounts(type){
	var form = document.forms[0];
 var els = form.elements;

 var requestCountString = '';                                
 for(var i=0; i < els.length; i++) {
	 var el = els[i];       
     if (el.type == type) {
    	 if($.trim(el.value)== "") {
    		 el.value = 0;
    	 }
     	if (!is_InputCountValid($.trim(el.value))){
     		$('#error-count').replaceWith('<p align="center" style="color: red;" id="error-count">There are invalid or empty Count</p>');
     		return false;
     	}
     	
     	requestCountString = requestCountString  + el.value + "," ;    
     }
        
 }
 return requestCountString; 
}