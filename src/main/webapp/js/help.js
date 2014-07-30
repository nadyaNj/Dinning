$(document).ready(function() {
	
	/**
	 * drop down menu hover
	 */
	$('#topmenu li.sublnk').hover(
		function() {
			$(this).addClass("selected");
			$(this).find('ul').stop(true, true);
			$(this).find('ul').show('fast');
		},
		function() {
			$(this).find('ul').hide('fast');
			$(this).removeClass("selected");
		}
	);
	
	/**
	 * for hide product
	 */
	$("#hiddenFlag").live('click', function() {
		if($(this).is(':checked')) {
			$(this).attr('name', 'hiddenTrue');
		} else {
			$(this).attr('name', 'hiddenFalse');
		}
	});
	
	/**
	 * go to in menu 
	 */
	$("a#menuLink").click(function() {
		$("a#menuLink").removeClass();
		$(this).addClass('current');
		var flag = $(this).attr('name');
		$.post("actionServlet", {pageFlag: flag}, function(data) {
   			$('div#replaceWith').html(data);   			
   		});
	});
    
	/**
	 * pagination where page count < 4
	 */
	$("a[name=pagingAction]").live('click', function() {
		var goPgnum = $(this).attr('title');
		var flagSession = document.getElementById('pageFlag').value;
		var flagPagination = document.getElementById('pagingFlag').value
		if(flagPagination == "viewMenuPaging") {
		var filterIds = requestMenuProducts('hidden');
		} else {
			filterIds = "";
		}
		$.post("actionServlet", {searchFlag: flagSession,pageFlag: flagPagination, pageNumber: goPgnum, searchFilter: filterIds }, function(data) {
   			$('div#productTen').html(data);
   		});		
	});
	
	/**
	 * pagination where page count > 3
	 */
	$("a#paging").live('click', function() {
		var gonum = $(this).attr('title');
		var pageEnd = document.getElementById('pageEnd').value;
		var flagSession = document.getElementById('pageFlag').value;
		var flagPagination = document.getElementById('pagingFlag').value
		
		if(flagPagination == "viewMenuPaging") {
			var filterIds = requestMenuProducts('hidden');
		} else {
			filterIds = "";
		}
		if(gonum > pageEnd) {
			gonum = pageEnd;
		} else if(gonum < 1) {
			gonum = 1;
		}
		$.post("actionServlet", {searchFlag: flagSession, pageFlag: flagPagination, pageNumber: gonum, searchFilter: filterIds }, function(data) {
   			$('div#productTen').html(data);
   		});
	});	
});

/**
 * reseted search page
 */
function resetSearchPage() {
	$(function(){
		$('#searchForm')[0].reset();
	});
}


/**
 * check - uncheck
 */
function checkboxChecked(){
	 $(function(){    
		 if($('#all').is(':checked')){
			 $('.case').attr('checked', 'checked');
		 } else {
			 $('.case').removeAttr("checked");
		 }
	 });
}

/**
 * if click enter call method by pageFlag
 * @param event - Window
 */
function enterKeyPress(event) {
	$(function() {
		var pgFlag = $("input[name=pageFlag]").attr("value")
		if(event.keyCode == 13) {
			if(pgFlag == "loginPage") {
				submitForm("loginForm");
			} else if(pgFlag == "createPage") {
				submitForm("createProductForm");
			} else if(pgFlag == "updatePage") {
				submitForm("createProductForm");
			} else if(pgFlag == "searchPage") {
				searchProduct();
			}
		}
	});
}

function createUser() {
	$(function() {
		var form = $('#userManageForm').serialize();
		$.ajax({type: 'post', url: '/dinning-web/actionServlet', data: form, success: function(data) {
				$("div#replaceWith").html(data);
			}
		});
	});
}




/**
 * submited form by id
 * @param formId
 */
function submitForm(formId) {
	document.getElementById(formId).submit();
}

/**
 * the method reset form by id
 * @param formName
 */
function resetForm(formName) {
	document.getElementById(formName).reset();	
}

function createPage() {
	document.getElementById("pageFlagCreate").value = "createPage";
	document.getElementById("createForm").submit();
}

function resetCreatePage() {
	document.getElementById("pageFlagCreate").value = "createPageView";
	document.getElementById("createForm").submit();
}


/*****type/measure manipulation***/

function typePaging(number,pageFlag){

	  $(function(){
	    	 
	    	 $("a#typepaging").removeClass( "current" ) ;
	    	 totalPages = Math.ceil($("div#typepaging").attr("count")/10); 
	    	 currentPage = number;
	    	 if(parseInt(currentPage)!=0){
	    	 pageCount=number-1
	    	 $.post("actionServlet",{pageFlag:pageFlag, typePageNumber:pageCount},function(data) {
	    		 var content = $( data ).find ("div#list");
	    		 $("div#list").empty().append( content );                   
	    	 }); 
	    	 
	    	 $("div#typepaging").empty();$("div#typepaging").empty();
	    	 if (totalPages >= 1){
	    		 if ( totalPages >  1 ) {
	        		 $("div#typepaging").append (  "<a href='#' onclick=javascript:typePaging(this.getAttribute(\"number\"),\'"+pageFlag+"\') id='typepaging' page='page1'  number='1'> 1  </a>");
	  
	        	 }
	        	 if ( (parseInt(currentPage) > 2)  && (parseInt(currentPage) != totalPages) ){
	        		 $("div#typepaging").append (  "<a href='#' onclick=javascript:typePaging(this.getAttribute(\"number\"),\'"+pageFlag+"\')   id='typepaging' page='page1'  number='"+(parseInt(currentPage)-2)+"'> ...  </a>");
	    			 $("div#typepaging").append (  "<a href='#' onclick=javascript:typePaging(this.getAttribute(\"number\"),\'"+pageFlag+"\')   id='typepaging' page='page2'  number='"+(parseInt(currentPage)-1)+"'>"+(parseInt(currentPage)-1)+"  </a>");
	        	}
	        	 if ( parseInt(currentPage) == totalPages && totalPages ==3 ){
	        	
	     
	        		 $("div#typepaging").append (  "<a href='#' onclick=javascript:typePaging(this.getAttribute(\"number\"),\'"+pageFlag+"\')  id='typepaging' page='page2'  number='"+(parseInt(currentPage)-1)+"'>"+(parseInt(currentPage)-1)+"  </a>");
	        		 $("a[number='"+currentPage+ "']").addClass("current");
	             }
	        	 if ( parseInt(currentPage) == totalPages && totalPages >3 ){
	        		 $("div#typepaging").append (  "<a href='#' onclick=javascript:typePaging(this.getAttribute(\"number\"),\'"+pageFlag+"\')    id='typepaging' page='page1'  number='"+(parseInt(currentPage)-1)+"'> ...  </a>");
	        		 $("div#typepaging").append (  "<a href='#' onclick=javascript:typePaging(this.getAttribute(\"number\"),\'"+pageFlag+"\')   id='typepaging' page='page2'  number="+(parseInt(currentPage)-2)+"'>"+(parseInt(currentPage)-2)+"  </a>");
	        		 $("div#typepaging").append (  "<a href='#' onclick=javascript:typePaging(this.getAttribute(\"number\"),\'"+pageFlag+"\')  id='typepaging' page='page2'  number='"+(parseInt(currentPage)-1)+"'>"+(parseInt(currentPage)-1)+"  </a>");
	        		 $("a[number='"+currentPage+ "']").addClass("current");
	             }
	        	 if ( parseInt(currentPage) != 1 && parseInt(currentPage) != totalPages ) {
	        		 $("div#typepaging").append (  "<a href='#' onclick=javascript:typePaging(this.getAttribute(\"number\"),\'"+pageFlag+"\')    id='typepaging' page='page2' class='current' number='"+parseInt(currentPage)+"'>"+parseInt(currentPage)+" </a>");
	        	 }
	        	 if ( parseInt(currentPage) < (totalPages - 1) )
	      	    	 $("div#typepaging").append (  "<a href='#' onclick=javascript:typePaging(this.getAttribute(\"number\"),\'"+pageFlag+"\')  id='typepaging' page='page2'  number='"+(parseInt(currentPage)+1)+"'>"+(parseInt(currentPage)+1)+" </a>");
	        	 if ( parseInt(currentPage) == 1 && totalPages > 3 ){
	        		 $("div#typepaging").append (  "<a href='#' onclick=javascript:typePaging(this.getAttribute(\"number\"),\'"+pageFlag+"\')    id='typepaging' page='page2'  number='"+(parseInt(currentPage)+2)+"'>"+(parseInt(currentPage)+2)+" </a>");
	        		 $("a[number='1']").addClass("current");
	      		 }     
	      	   	 if ( parseInt(currentPage) < (parseInt(totalPages)-1) && totalPages != 3  ){
	      	   		 $("div#typepaging").append (  "<a href='#' onclick=javascript:typePaging(this.getAttribute(\"number\"),\'"+pageFlag+"\')   id='typepaging' page='page1'  number='"+(parseInt(currentPage)+2)+"'> ... </a>");
	      	   	 }
	      	   	 $("div#typepaging").append (  "<a href='#' onclick=javascript:typePaging(this.getAttribute(\"number\"),\'"+pageFlag+"\')    id='typepaging' page='page1'  number='"+totalPages+ "'>"+totalPages+ " </a>");
	      	   	 if ( parseInt(currentPage) == totalPages ){
	        		 $("a[number='"+currentPage+ "']").addClass("current");
	      	   	 }
	         }
	    	 }
	     });	
}

function itemEdit(itemID, itemName,pageFlag, title) {
	 $(function(){
   	
   
   	 $("input#text").attr("value",itemName);
   	 $("input#text").attr("editid",itemID);
   	 $("input#text").attr("pageFlag",pageFlag);
   	 $("h1#title").empty().text(title);
   	
      			 
	 });
	}
function itemSave(){
	$(function(){
    var index=null;
	    var typen = $("input#text").attr("value");
	    index = $("input#text").attr("editid");
	    var pagefl = $("input#text").attr("pageFlag");
	    		 
	    $.post("actionServlet",{pageFlag:pagefl, type:typen, id:index},function(data) {
	    	 var content = $( data ).find ("div#content");
			 $("div#outer-wrapper").empty().append( content );
	
 });
});
}

function itemReset(pageFlag){
	$(function(){
		
      	  $("input#text").attr("value","");
  		  $("input#text").attr("editid","");
  		  $("input#text").attr("pageFlag",pageFlag);
	  });   
	    
	}

function itemDelete(itemID, pageFlag){
	  $(function(){
			if(confirm("Are you sure you want to delete?")){
			
	             $.post("actionServlet",{pageFlag:pageFlag, typeId:itemID},function(data) {
		        	 var content = $( data ).find ("div#content");
		    		 $("div#outer-wrapper").empty().append( content );
		         });
			
			}
	    });
}


////////////== user management===

function PrintElem(elem)
{
    Popup($(elem).text());
}

function Popup(data) 
{
    var mywindow = window.open('', 'my div', 'height=400,width=600');
    mywindow.document.write('<html><head><title>my div</title>');
    /*optional stylesheet*/ //mywindow.document.write('<link rel="stylesheet" href="main.css" type="text/css" />');
    mywindow.document.write('</head><body >');
    mywindow.document.write(data);
    mywindow.document.write('<input type="button" value="Print" onclick="window.print();" ></body></html>');
    mywindow.document.close();
   
    return true;
}

function userDelete(itemID, pageFlag){
	  $(function(){
			
			
	             $.post("actionServlet",{pageFlag:pageFlag, typeId:itemID},function(data) {
		        	 var content = $( data ).find ("div#content");
		    		 $("div#outer-wrapper").empty().append( content );
		         });
			
			
	    });
}
function isValidEmail(){
	 $(function(){
	  validRegExp = /^[^@]+@[^@]+.[a-z]{2,}$/i;
	  strEmail = $("input#email").attr("value");

	   // search email text for regular exp matches
	    if (strEmail.search(validRegExp) == -1) 
	   {
	      alert('A valid e-mail address is required.');
	      return false;
	    } 
	    return true; 
	    });
	}

function userSave(){
	
	$(function(){
		 validRegExp = /^[^@]+@[^@]+.[a-z]{2,}$/i;
		 var email = $("input#email").attr("value");
		   // search email text for regular exp matches
		    if (email!="" && email.search(validRegExp) == -1) 
		   {
		      alert('A valid e-mail address is required.');
		   
		    } else
		    { 
		  
	    var index=null;
	    var username = $("input#username").attr("value");
	    
	    
	    var dicountCode = $("input#dicountcode").attr("value");
	    var position = $("input#position").attr("value");
	    var pageFlag = $("a#save").attr("pageFlag");
	    var index = $("a#save").attr("editid");
	    var depid = $("select#departments").val();
	    		 
	    $.post("actionServlet",{pageFlag:pageFlag, username:username, userEmail:email, dicountCode:dicountCode, userPosition:position, id:index,depid:depid },function(data) {
	    	 var content = $( data ).find ("div#content");
			 $("div#outer-wrapper").empty().append( content );
			 
			 if($("p#error").text() != " Invalid name and name unique " && pageFlag=="createUserPage"){
			
			 window.open('pages/printpassword.jsp','window','width=400,height=200'); 
			 }
			 
	  
	 });
		    }
	
	});
	
	}

function userPrint(pagefl){
	
	$(function(){
		 

	    var text = $("div#password").html();
	    var mywindow = window.open('', 'my div', 'height=400,width=600');
	    mywindow.document.write('<html><head><title>my div</title>');
	    /*optional stylesheet*/ //mywindow.document.write('<link rel="stylesheet" href="main.css" type="text/css" />');
	    mywindow.document.write('</head><body onload="window.print()">');
        mywindow.document.write(text);
        mywindow.document.write('</body></html>');
        mywindow.document.close();

	    $.post("actionServlet",{pageFlag:pagefl },function(data) {
	    	 var content = $( data ).find ("div#content");
			 $("div#outer-wrapper").empty().append( content );
	  
	 });
	 });
}
	
	


function userEdit(userID, username, email, position, dicountCode, depId, pageFlag,title) {
	 $(function(){
		 $("a#reset").removeClass("button");
		 $("a#reset").css("display","none");
		 getdepartmentbyId(depId);
  	  	 $("input#username").attr("value",username);
	  	 $("input#email").attr("value",email);
	  	 $("input#position").attr("value",position);
	  	 $("input#dicountcode").attr("value",dicountCode);
	  	
	  	 
	  	 $("a#save").attr("pageFlag",pageFlag);
	  	 $("a#save").attr("editid",userID);
	  	
	  	 $("h1#title").empty().text(title);	
     			 
	 });
}



function changepassword(id, username, email, pagefl){
	 $(function(){
		// var password $(this).val();
		// alert(password);
			
	             $.post("actionServlet",{pageFlag:pagefl, userid:id, username:username, userEmail:email},function(data) {
	            	 var content = $( data ).find ("div#content");
	    			 $("div#outer-wrapper").empty().append( content );
	    			 
	    			 if($("p#error").text() != " Invalid name and name unique " && pageFlag=="createUserPage"){
	    			
	    			 window.open('pages/printpassword.jsp','window','width=400,height=200'); 
	    			 }
		         });
		
	    });



}

function getdepartment(){
	 $(function(){
		 var compid  = $("select#company").val();
	
	             $.post("actionServlet",{pageFlag:"showUserPage", compid:compid},function(data) {
		        	 var content = $( data ).find ("div#departments");
		    		 $("div#departments").empty().append( content );
		    		 
		         });
		
	    });
}

function getdepartmentbyId(depid){
	 $(function(){
	
	
	             $.post("actionServlet",{pageFlag:"showUserPage", depid:depid},function(data) {
		        	 var content = $( data ).find ("div#departments");
		    		 $("div#departments").empty().append( content );
		    		 var companyId =  $("input#hidcompid").attr("value");
		    		 $("#company option[value="+companyId+"]").attr("selected", "selected");
		         });
		
	    });
}

/********************Manage Menu *****************************/
function menuViewEdit (menuId) {
	var today = document.getElementById("todayDate").value;
	var date = document.getElementById(""+menuId+"").name;
	var menuDate = Date.parse(date);
	var todayDate = Date.parse(today)
	if (menuDate < todayDate) {
		viewThisMenu(menuId);
	} else {
		editThisMenu(menuId);
	}
}

function viewThisMenu(menuId) {
	$(function() {
		$.post("actionServlet", {pageFlag: "viewMenuFlag", menuId: menuId}, function(data){
			$('div#replaceWith').html(data);
		});
	});
}

function editThisMenu(menuId) {
	$(function() {
		$.post("actionServlet", {pageFlag: "editMenuFlag", menuId: menuId},function(data){
			$('div#replaceWith').html(data);
		});
	});
}

function searchProductForMenu() {
	$(function() {
		var m_method = $('#searchForm').attr('method');
		var m_action = $('#searchForm').attr('action');
		var pgnum = 0;
		document.getElementById("pageNumber").value = pgnum;
		var filter = requestMenuProducts('hidden');
		var m_data = $('#searchForm').serialize();
		var dat = "searchFilter="+filter +"&"+ ""+m_data+"";
		$.ajax({type:m_method, url: m_action, data: dat, success: function(data){
	    	$("div#productTen").html( data );
	    	}
	    }); 
	});
}

function addAllToMenu() {
	if($(".case:checked").length > 0){
		
			var pgnum = $('a#paging.current').attr('title');
			var requestString = requestStringAddSelected();	
			$.post("actionServlet", {pageNumber: pgnum, pageFlag: "addAllToMenu", menuProdIds:requestString}, function(data) {
				$(""+data+"").appendTo($("table#newMenu"));
				searchProductForMenu();
			});	
		
	} else {
		alert('Please select item(s) you want to add to menu');
	}
}


function addPrToMenu(prodId){
	$(function(){
		if(productNoRepeat('hidden', prodId)==false){
			return false;
		}
		
		$.post("actionServlet", {pageFlag: "addToMenuFlag", addProductId: prodId}, function(data) {
			$("#searchProduct tr#"+prodId+"").remove();
			$(""+data+"[id="+prodId+"]").appendTo($("table#newMenu"));
			searchProductForMenu();
			
		});
	});
}



function removePrFromMenu(prodId){
	$(function(){
		$("#newMenu tr#"+prodId+"").remove();
		searchProductForMenu();
	});
}

function showDatePicker(){
	$(function(){
		$("#datepicker").datepicker({
			showOn: "button",
			buttonImage: "images/calendar.gif",
			buttonImageOnly: true ,
			dateFormat: 'yy-mm-dd' ,
	        inline: true,
	        minDate: new Date(),
	        maxDate: new Date(2020, 12 - 1, 31)
	
		});    
	
	});
}

function requestStringAddSelected() {
	 var form = document.forms[2];
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

function requestMenuProducts(type){
	 var form = document.forms[0];
     var els = form.elements;

     var requestString = '';                                
     for(var i=1; i < els.length; i++) {
    	 var el = els[i];       
         if (el.type == type) {
        	 requestString = requestString  + el.name + "," ;    
         }
            
     }
     return requestString; 
}

function is_CountValid(value){ 
	   for (i = 0 ; i < value.length ; i++) { 
	      if ((value.charAt(i) < '0') || (value.charAt(i) > '9')) return false 
	    } 
	   return true; 
	}



function requestMenuProductCounts(type){
	var form = document.forms[0];
    var els = form.elements;

    var requestCountString = '';                                
    for(var i=1; i < els.length; i++) {
   	 var el = els[i];       
        if (el.type == type) {
        	if (!is_CountValid($.trim(el.value)) || $.trim(el.value)==""){
        		$('#error-count').replaceWith('<p align="center" style="color: red;" id="error-count">There are invalid or empty Count</p>');
        		return false;
        	}
        	
        	requestCountString = requestCountString  + el.value + "," ;    
        }
           
    }
    return requestCountString; 
}

function tvar(pgFlag) {
	
	$(function() {
		var date = document.getElementById("datepicker").value;	
		if (date ==""){
			$('#error-date').replaceWith('<p align="center" style="color: red;" id="error-date">Please select Date </p>');
			return false;
		} else {
			$('#error-date').replaceWith('<p align="center" style="color: red;" id="error-date"></p>');
		}
		var requestString = requestMenuProducts('hidden');
		if (requestString == ""){
			$('#error-prod').replaceWith('<p align="center" style="color: red;" id="error-prod">Please add products to menu</p>');
			return false;
		} else {
			$('#error-prod').replaceWith('<p align="center" style="color: red;" id="error-prod"></p>');
		}
	//var requestCountString = requestMenuProductCounts('text');
		$.post("actionServlet", {pageFlag: pgFlag,searchFilter: requestString, menuProdIds: requestString, menuDate: date},function(data){
			$('div#replaceWith').html(data);
		});	
	 });
}

function saveThisMenu(pgFlag) {
	$(function(){
		var date = document.getElementById('date').value;
		
		var prods = requestMenuProducts('hidden');
		if (prods =="") {
			$('#error-prod').replaceWith('<p align="center" style="color: red;" id="error-prod">Please add products to menu</p>');
			return false;
		} else {
			$('#error-prod').replaceWith('<p align="center" style="color: red;" id="error-prod"></p>');
		}
		//var counts = requestMenuProductCounts('hidden');
		$.post("actionServlet", {pageFlag: pgFlag, menuProdIds: prods, menuDate: date},function(data){
			$('div#replaceWith').html(data);
		});	
	});
}

function cancelSaveMenu() {
	$(function(){
		var date = document.getElementById('date').value;
		var filter = requestMenuProducts('hidden');
		var prods = requestMenuProducts('hidden');
		
		$.post("actionServlet", {pageFlag: "cancelMenuFlag",searchFilter: filter , menuProdIds: prods, menuDate: date},function(data){
			$('div#replaceWith').html(data);
		});	
	});
}

function productNoRepeat(type, prId) {
	 var form = document.forms[0];
     var els = form.elements;

     var requestString = '';                                
     for(var i=1; i < els.length; i++) {
    	 var el = els[i];       
         if (el.type == type) {
        	 if (el.name == prId){
        		 alert("This product already added to this menu");
        		 return false;
        	}
         }
            
     }     
}
















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
		var requestCountString = requestSurplusMenuProductCounts('text')
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

function editSurplus(id) {
	$(function() {
		$.post("actionServlet", {pageFlag: "productsSurplus", editProductId: id}, function(data) {
			$('div#replaceWith').html(data);
   		});
	});
}
