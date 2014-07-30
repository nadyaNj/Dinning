/**
 * This method for create company name
 */
function createCompanyName(){
        $(function(){
                var companyId = document.getElementById('hid').value;
                var        flag = document.getElementById('flag').value;
                var companyName = document.getElementById("companyName").value;
                var reset = document.getElementById("hid").getAttribute('name');
                $.post("actionServlet", {pageFlag:flag,companyName:companyName,id:companyId}, function(data) {
                           $('div#replaceWith').html(data);           
                           var error = document.getElementById("flag").getAttribute("name");
                           if(flag == "updateCompanyName" && error == "Invalid name or unique"){
                                   document.getElementById("companyName").value = companyName;
                           }
                   });
        });
}

/**
 * This method for delete company name.
 * @param id
 * @returns {Boolean}
 */
function deleteCompanyName(id) {
        if (!confirm('Are you sure you want delete company name ?')){
                return false;
        } else {
                $(function() {
                        $.post("actionServlet", {pageFlag: "deleteCompanyName", deleteCompanyNameId: id}, function(data) {
                                   $('div#replaceWith').html(data);
                           });
                });
        }
}

/**
 * This method for edit company name.
 * @param itemID
 * @param itemName
 * @param pageFlag
 * @param title
 */
function editCompanyName(itemID, itemName,pageFlag, title) {
         $(function(){   
                 document.getElementById("companyName").value = itemName;
                 document.getElementById("hid").value = itemID;
                 document.getElementById("flag").value = pageFlag;
                 $("h1#title").empty().text(title);
                 $("span#update").empty().text("Update");
                 $("a#reset").remove();
         });
        }

/**
 * This method for reset company name.
 */
function companyNameReset(){
      document.getElementById("companyName").value = "";
}