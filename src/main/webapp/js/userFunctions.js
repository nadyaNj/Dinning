/**
 * the js for user
 */


/**
 * new user parameters requested in server
 */
function createUserFunction() {
	$(function() {
		var form = $('#userManageForm').serialize();
		$.ajax({type: 'post', url: '/dinning-web/actionServlet', data: form, success: function(data) {
				$("div#showUsers").html(data);
				var error = $('#errorRed').text();
				if(error.trim() != "") {
					$('#errorRed').text('');
					$('#errorMessageRed').text(error);
				} else if($("#printNow").attr("value") == "printNow") {
					$("div#userEdit").html(data);
					$("div#showUsers").empty();
				} else {
					resetForm('userManageForm');
					$('h3#errorMessageRed').text('');
				}				
			}
		});		
	});
}

/**
 * updated user by id
 */
function updateUserFunction() {
	$(function() {
		var form = $('#userManageForm').serialize();
		$.ajax({type: 'post', url: '/dinning-web/actionServlet', data: form, success: function(data) {
				$("div#replaceWith").html(data);
				var error = $('#errorRed').text();
				if(error.trim() != "") {
					$('#errorRed').text('');
					$('#errorMessageRed').text(error);
				} else {
					resetForm('userManageForm');
				}				
			}
		});		
	});
}

/**
 * set parameters in company by departament
 */
function showDepartamentsByCompany() {
	$(function() {
		var compId = $('#company option:selected').val();
		$.post("actionServlet", {pageFlag: "showDepartamenByCompany", companyId: compId}, function(data) {
			$('select#dep').html(data);	
		});
	});
}

/**
 * delete user by id
 * @param userId
 */
function inActiveUser(userId) {
	$(function() {
		 $.post("actionServlet", {pageFlag: "changeUserStatus", id: userId}, function(data) {
			 $('td[id='+userId+']').html('InActive');
			 $('a[id='+userId+']').html('active');
			 $('a[id='+userId+']').attr('onclick', 'activateUser('+userId+');');
		 });
	});
}

/**
 * user status pending by id
 * @param userId
 */
function activateUser(userId) {
	$(function() {
		 $.post("actionServlet", {pageFlag: "changeUserStatus", id: userId}, function(data) {
			 $('td[id='+userId+']').html('active');
			 $('a[id='+userId+']').html('inActive');
			 $('a[id='+userId+']').attr('onclick', 'inActiveUser('+userId+');');
		 });
	});
}

/**
 * get user by id
 * @param userId
 */
function editUser(userId) {
	$(function() {
		 $.post("actionServlet", {pageFlag: "editUser", id: userId}, function(data) {
			 $('div#userEdit.content').html(data);
		 });
	});
}

/**
 * delete user by id
 * @param userId
 */
function deleteUser(userId) {
	$(function() {
		if (!confirm('Are you sure you want to delete this user?')){
			return false;
		} else {
			var pgnum = $('a.current#paging').attr('title');
			$.post("actionServlet", {pageFlag: "deleteUserFlag", id: userId, pageNumber: pgnum}, function(data) {
				 $('div#showUsers').html(data);
			 });
		}		
	});
}

/**
 * set new user password
 * @param userId
 */
function setNewPassword(userId) {
	if (!confirm('Change user password?')){
		return false;
	} else {
		$.post("actionServlet", {pageFlag: "setNewUserPassword", id: userId},function(data){
			 $('div#showUsers').html(data);
		if($("#printNow").attr("value") == "printNow") {
			$("div#userEdit").html(data);
			$("div#showUsers").empty();
		}
	});
  }
}