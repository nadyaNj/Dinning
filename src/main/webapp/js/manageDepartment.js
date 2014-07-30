/**
 * This method for create departament.
 */
function createDepartament(){
        $(function(){
                var departamentId = document.getElementById('hid').value;
                var        flag = document.getElementById('flag').value;
                var depName = document.getElementById("departamentName").value;
                var companyId = $("#company option:selected").val();
                var reset = document.getElementById("hid").getAttribute('name');
                var resetCheck = document.getElementById("hid").getAttribute('title');
                $.post("actionServlet", {pageFlag:flag,departamentName:depName,id:companyId,depId:departamentId}, function(data) {
                           $('div#replaceWith').html(data);
                           var error = document.getElementById("flag").getAttribute("name");
                           if(error == "Invalid name or unique"){
                                   document.getElementById('hid').value = departamentId;
                                   document.getElementById("departamentName").value = depName;
                            $("input#hid").attr("name",reset);
                            $("input#hid").attr("title",resetCheck);
                            $("#company option[value="+companyId+"]").attr("selected", "selected");
                           }
                   });
        });
}

/**
 * This method for delete department
 * @param id
 * @returns {Boolean}
 */
function deleteDepartament(id) {
        if (!confirm('Are you sure you want delete departament ?')){
                return false;
        } else {
                $(function() {
                        $.post("actionServlet", {pageFlag: "deleteDepartament",deleteDepartamentId: id}, function(data) {
                                   $('div#replaceWith').html(data);
                           });
                });
        }
}

/**
 * This method for edit department
 * @param itemID
 * @param itemName
 * @param pageFlag
 * @param title
 * @param companyId
 */
function editDepartament(itemID, itemName,pageFlag, title,companyId) {
         $(function(){   
                 document.getElementById("departamentName").value = itemName;
                 document.getElementById("hid").value = itemID;
                 document.getElementById("flag").value = pageFlag;
                 $("h1#title").empty().text(title);
                 $("span#update").empty().text("Update");
                 $("input#hid").attr("name",itemName);
                 $("input#hid").attr("title",companyId);
                 $("a#depReset").remove();
                 $("#company option[value="+companyId+"]").attr("selected", "selected");
         });
        }

/**
 * This method for reset department
 */
function departamentReset(){
        document.getElementById("departamentName").value = "";
}