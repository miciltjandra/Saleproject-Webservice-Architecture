/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function valNumber(val, id, len, min) {
    var elmt = document.getElementById(id);

    if (elmt.value.length > len) {
        elmt.value = elmt.value.slice(0,len); 
    }

    if (elmt.value.length < min) {
        if (!elmt.classList.contains("invalid")) {
            elmt.classList.add("invalid");
        }
        return false;
    }
    else {

    	var regex = /^[0-9]+$/;

    	if(regex.test(val)) {
        	if (elmt.classList.contains("invalid")) {
        		elmt.classList.remove("invalid");
        	}
        	return true;
        }
        else {
        	if (!elmt.classList.contains("invalid")) {
        		elmt.classList.add("invalid");
        	}
        	return false;
        }
    }
}
function valNotEmpty(val, id) {
    var elmt = document.getElementById(id);
    // /^(?!\s*$).+/
    var regex = /^(?!\s*$).+/;

    if(regex.test(val)) {
        if (elmt.classList.contains("invalid")) {
            elmt.classList.remove("invalid");
        }
        return true;
    }
    else {
        if (!elmt.classList.contains("invalid")) {
            elmt.classList.add("invalid");
        }
        return false;
    }
}
function validateAdd() {
    var valName = document.getElementById("add_name").value;
    var valDesc = document.getElementById("add_desc").value;
    var valPrice = document.getElementById("add_price").value;
    
    return valNotEmpty(valName, "add_name") && valNotEmpty(valDesc, "add_desc") && valNumber(valPrice, "add_price", 15, 1);
}
function validateEdit() {
    var valName = document.getElementById("edit_name").value;
    var valDesc = document.getElementById("edit_desc").value;
    var valPrice = document.getElementById("edit_price").value;
    
    return valNotEmpty(valName, "edit_name") && valNotEmpty(valDesc, "edit_desc") && valNumber(valPrice, "edit_price", 15, 1);
}
function validatePurch() {
    var c = document.getElementById("credit").value;
    var v = document.getElementById("verify").value;
    var n = document.getElementById("phone").value;
    var p = document.getElementById("postal").value;


    if (valNumber(c, "credit", 12, 12) && valNumber(v, "verify", 3, 3) && valNumber(n, "phone", 12, 8) && valNumber(p, "postal", 5, 3) == true) {

        if(confirm(' Confirmation \n Make sure your purchase is correct.') == true) {
            return true;
        }
        else {
            return false;
        }
    }
    else {
        return false;
    }
}