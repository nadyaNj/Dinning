/**
 * product functions
 */

/**
 * editing product
 * 
 * @param Integer - id
 */
function edit(id) {
	var flagSession = document.getElementById('pageFlag').value;
	$(function() {
		$.post("actionServlet", {searchFlag: flagSession, pageFlag: "editPage", editProductId: id}, function(data) {
   			$('div.content#rep').html(data);
   		});
	});
}

/**
 * deleted product by id
 * 
 * @param Integer - id
 * @returns {Boolean}
 */
function deletePr(id) {
	if (!confirm('Are you sure you want delete product ?')){
		return false;
	} else {
		var flagSession = document.getElementById('pageFlag').value;
		var pgnum = $('a#paging.current').attr('title');
		$(function() {
			$.post("actionServlet", {pageNumber: pgnum, searchFlag: flagSession, pageFlag: "delete", deleteProductId: id}, function(data) {
	   			$('div#productTen').html(data);
	   		});
		});
	}
}

/**
 * hided product by id
 * 
 * @param Integer - product id
 * @param Boolean - hide
 */
function hide(id, hide) {
	var flagSession = document.getElementById('pageFlag').value;
	var pgnum = $('a#paging.current').attr('title');
	$(function() {
		$.post("actionServlet", {pageNumber: pgnum, searchFlag: flagSession, pageFlag: "hideProduct", hideProductId:id,hidden:hide}, function(data) {
            $('div#productTen').html(data);
		});	
	});
}

/**
 * searched products
 */
function searchProduct() {
	$(function() {
		var m_method = $('#searchForm').attr('method');
		var m_action = $('#searchForm').attr('action');
		var pgnum = 0;
		document.getElementById("pageNumber").value = pgnum;
		if($('input#hidden').is(':checked')) {
	    	$('input#hidden').attr('value',true);
	    } else {
	    	$('input#hidden').attr('value',false);
	    }
		var m_data = $('#searchForm').serialize();
		$.ajax({type:m_method, url: m_action, data: m_data, success: function(data){
	    	$("div#productTen").html( data );
	    	}
	    }); 
	});
}

/**
 * reseted craete page
 */
function resetCreatePage() {
	$(function() {
		document.getElementById('name').value='';
		document.getElementById('price').value='';
		document.getElementById('code').value='';
		document.getElementById('description').value='';
		$('option').removeAttr('selected');
		$('option[value=0]').attr('selected','selected');
	});
}

/**
 * deleted all checks products
 * 
 * @returns {Boolean}
 */
function deleteAllClicked() {
	if($(".case:checked").length > 0){
		if (!confirm('Are you sure you want to delete selected items?')){
			return false;
		} else {
			var pgnum = $('a#paging.current').attr('title');
			var requestString = createRequestParamString();	
			$.post("actionServlet", {pageNumber:pgnum,pageFlag: "deleteAll",delProductIds:requestString}, function(data) {
		        $('div#productTen').html(data);
			});	
		}
	} else {
		alert('Please select item(s) you want to delete');
	}
}


/**
 * if no check product deleteAll button disabled  
 * @param checkbox
 */
function allCheckbox(checkbox) {
	if(checkbox.checked) {
		document.getElementById('deleteAllButton').disabled = "";
	} else {
		document.getElementById('deleteAllButton').disabled = "disabled";
	}
}

/**
 * unchecks - check, checks - uncheck
 */
function uncheck(){
	$(function(){
		if($(".case").length == $(".case:checked").length) {
		      $("#all").attr("checked", "checked");
		  } else {
		      $("#all").removeAttr("checked");
		  }
	});
}



/**
 * Creates String which contains all 
 * parameters which should be present in the ajax request.
 */
function createRequestParamString() {
	var form = document.forms[1];
    var els = form.elements;
    var requestString = '';                                
    for(var i=0; i < els.length; i++) {
    	var el = els[i];       
        if (el.type == 'checkbox' && el.checked == true ) {
        	requestString = requestString  + el.name + "," ;    
        }            
    }
    return requestString;
}

