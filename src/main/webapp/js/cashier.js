$(document).ready(function() {
	
	/**
	 * 
	 */
	$('a#tabType').live('click', function() {
		$('li.ui-state-active').removeClass('ui-state-active');
		$('li.ui-tabs-selected').removeClass('ui-tabs-selected');
		$(this).parent().addClass('ui-state-active');
		$(this).parent().addClass('ui-tabs-selected');
		var typeId = $(this).attr('name');		
		$.post("actionServlet", {pageFlag: "searchProductByType", searchTypeId: typeId}, function(data) {
            $('div#tab').replaceWith(data);
            focus();
            $('h1#messageType').text($('li.ui-tabs-selected').children().text());
		});
	});	
});

/**
 * 
 */
function showTodayMenuInCashierPage() {
	$(function() {
		$('li.ui-state-active').removeClass('ui-state-active');
		$('li.ui-tabs-selected').removeClass('ui-tabs-selected');
		$('a#tabTodayMenu').parent().addClass('ui-state-active');
		$('a#tabTodayMenu').parent().addClass('ui-tabs-selected');
		$.post("actionServlet", {pageFlag: "showTodayMenuCashier"}, function(data) {
            $('div#tab').replaceWith(data);
		});
		focus();
	});	
}

/**
 * if click enter input code
 * @param event - Window
 */
function enterKeyPressCode(event) {
	$(function() {
		var	prCode = document.getElementById('codeCashier').value;
		if(event.keyCode == 13) {
			$.post("actionServlet", {pageFlag:"addProductByCode",code:prCode}, function(data) {
				$("table#basket tr:first").after(data)
				
				var userId = $("input#userName").attr("name");
				var userName = $("input#userName").attr("value");
				var currentPrice = $("input#addItemByCode").attr("value");
				var discountPrice = $("input#addItemByCode").attr("title");
				var prId = $("input#addItemByCode").attr("name");
				
				if(prId != undefined && prId != "" && userId == ""){
					$("input#addItemByCode").remove();
					var allPrice = parseInt($("input#allPrice").attr("name")) + parseInt(currentPrice);
					var allDisPrice = parseInt($("input#allPrice").attr("value")) + parseInt(discountPrice);
					$("input#allPrice").attr("name",allPrice);
					$("input#allPrice").attr("value",allDisPrice);
					$("table#basket td#total").text(allPrice).css('font-weight','bold');
					$('p#error').text('');
					
				} else if(userId != undefined && userId != "" && prId == undefined){
					userDescoutPrice();
					$("p#userName").text(userName);
					$('p#error').text('');
					$("input#employeePay").removeAttr("disabled");    
				} else if(prId != undefined && prId != "" && userId != "") {
					$("input#addItemByCode").remove();
					var allDisPrice = parseInt($("input#allPrice").attr("value")) + parseInt(discountPrice);
					var allPrice = parseInt($("input#allPrice").attr("name")) + parseInt(currentPrice);
					$("input#allPrice").attr("value",allDisPrice);
					$("input#allPrice").attr("name",allPrice);
					$("table#basket td#total").text(allDisPrice).css('font-weight','bold');
					$("table#basket tr:eq(1) td#disPrice").text(discountPrice);
					$('p#error').text('');
				} else {
					$('p#error').text('Code is wrong');
				}
			});
			document.getElementById('codeCashier').value = "";
			focus();
		}
	});
}

/**
 * if cursor on <tr> color light
 * @param tableRow
 * @param highLight
 */
function ChangeColor(tableRow, highLight) {
    if (highLight) {
      tableRow.style.backgroundColor = '#dcfac9';
    } else {
      tableRow.style.backgroundColor = 'white';
    }
}

/**
 * Add item in basket
 * @param itemName
 * @param price
 * @param id
 * @param isHalf
 */
function addBasket(itemName, price, id, discountPrice, isHalf){
	$(function(){
		var currentPrice =  parseInt(price);
		var disCurrentPrice =  parseInt(discountPrice);
		if(isHalf != null) {
			currentPrice =  parseInt(price)/2;
			disCurrentPrice = parseInt(discountPrice)/2
		}
		
		var userId = $("input#userName").attr("name");
		var allDiscountPrice = parseInt($("input#allPrice").attr("value")) + parseInt(disCurrentPrice);
		var allPrice = parseInt($("input#allPrice").attr("name")) + parseInt(currentPrice);
		$("input#allPrice").attr("value",allDiscountPrice);
		$("input#allPrice").attr("name",allPrice);
		if(userId != undefined && userId != ""){
			$("table#basket tr:first").after($('<tr price="'+currentPrice+'" name="'+disCurrentPrice+'" onclick="removeBasketItem(this);" title="'+currentPrice+'" id="'+id+'"><td>'+
					itemName+'</td><td id="disPrice">'+disCurrentPrice+'</td><td>'+
					'<a class="delete"  href="javascript: void(0)" >  </a> </td>'));
			
			$("table#basket td#total").text(allDiscountPrice).css('font-weight','bold');
			$('p#error').text('');
			document.getElementById('codeCashier').value = "";
		} else {
			$("table#basket tr:first").after($('<tr name="'+disCurrentPrice+'" onclick="removeBasketItem(this);" title="'+currentPrice+'" id="'+id+'"><td>'+
					itemName+'</td><td id="disPrice">'+currentPrice+'</td><td>'+
					'<a class="delete"  href="javascript: void(0)" >  </a> </td>'));
			
			$("table#basket td#total").text(allPrice).css('font-weight','bold');
			$('p#error').text('');
			document.getElementById('codeCashier').value = "";
		}
		focus();
	});
}

/**
 * Remove item from basket.
 * @param id
 */
function removeBasketItem(id){
	$(function(){
		var userId = $("input#userName").attr("name");
		var prId = $(id).attr("id");
		var currentPrice = $(id).attr("title");
		var disCurrentPrice = $(id).attr("name");
		var allPrice = parseInt($("input#allPrice").attr("name")) - parseInt(currentPrice);
		var allDiscountPrice = parseInt($("input#allPrice").attr("value")) - parseInt(disCurrentPrice);
		$("input#allPrice").attr("value",allDiscountPrice);
		$("input#allPrice").attr("name",allPrice);
		$(id).remove();
		
		if(userId != undefined && userId != ""){
			$("table#basket td#total").text(allDiscountPrice).css('font-weight','bold');
		} else {
			$("table#basket td#total").text(allPrice).css('font-weight','bold');
		}	
		focus();
	});
}

/**
 * In action on load cursor in input .
 */
function focus(){
	document.getElementById('codeCashier').focus();
}

function pay(employee) {
	if (!confirm('Are you sure you want to pay ?')) {
		return false;
	} else {
		$(function(){
			var ids = new Array();
			var prices = new Array();
			var cashierId = $("input#us").attr("name");
			var trs = $('#basket tr');
			var total = $("table#basket td#total").text();
			var disPrices = new Array();
			var userId = $("input#userName").attr("name");
			
			if(userId != undefined && userId != "") {
				trs.each(function(i) {
				    var next = trs.eq(i+1);
					if(next.attr("id") != undefined) {
						ids[i] = next.attr("id");
						disPrices[i] = next.attr('title');
						prices[i] = next.find("td#disPrice").text();
					}
				});
				$.post("actionServlet", {pageFlag:"saveEmployeeBasket",'cashierId':cashierId,'total':total,'ids[]':ids,'prices[]':prices,'disPrices[]':disPrices,'userId':userId,'paymentTypeCode':employee});
			} else {
				trs.each(function(i) {
				    var next = trs.eq(i+1);
					if(next.attr("id") != undefined) {
						ids[i] = next.attr("id");
						prices[i] = next.find("td#disPrice").text();
						disPrices[i] = next.attr("name");
					}
				});
				$.post("actionServlet", {pageFlag:"saveSharedBasket",'cashierId':cashierId,'total':total,'ids[]':ids,'prices[]':prices});
			}
			trs.each(function(i) {
			    var next = trs.eq(i+1);
				if(next.attr("id") != undefined) {
					next.remove();
				}
			});
			$("table#basket td#total").text("0")
			$("input#allPrice").attr("value","0");
			$("input#allPrice").attr("name","0");
			$("input#userName").remove();
			$("p#userName").text("");
			focus();
			$("input#employeePay").attr("disabled","disabled");    
		});
	}
}

/**
 * This method for get discount price.
 */
function userDescoutPrice() {
	$(function() {
			var trs = $('#basket tr');
			trs.each(function(i) {
			    var next = trs.eq(i+1);
				if(next.attr("id") != undefined) {
					$(next).find("td#disPrice").text($(next).attr("name"));
				}
		   });
			$("table#basket td#total").text($("input#allPrice").attr("value")).css('font-weight','bold');
	   });
	}
