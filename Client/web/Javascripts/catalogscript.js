/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function switchLike(product_id) {
    if (document.getElementById(product_id+"_likebut").innerHTML === "LIKE") {
        document.getElementById(product_id+"_likebut").innerHTML = "LIKED";
        if (!document.getElementById(product_id+"_likebut").classList.contains("liked")) {
            document.getElementById(product_id+"_likebut").classList.add("liked");
        }
    } else {
        document.getElementById(product_id+"_likebut").innerHTML = "LIKE";
        if (document.getElementById(product_id+"_likebut").classList.contains("liked")) {
            document.getElementById(product_id+"_likebut").classList.remove("liked");
        }
    }
}

function increaseLike(product_id, user_id) {
	var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById(product_id+"_like").innerHTML = this.responseText;
            switchLike(product_id);
        }
    };
    var act;
    xmlhttp.open("GET", "IncreaseLike?product_id=" + product_id + '&user_id=' + user_id, true);
    xmlhttp.send();
		
    //document.getElementById(product_id+"_like").innerHTML = "product id" + product_id + "user_id" + user_id;
}
