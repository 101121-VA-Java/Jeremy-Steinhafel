let token = sessionStorage.getItem("token");

if (token) {
    let tokenArr = token.split(':');
    let id = tokenArr[0];
    let role = tokenArr[1];
 
    console.log(tokenArr);
 } else {
    window.location.href= "login.html";
 }

function submitReimbursement(){
    let api1 = "http://localhost:8080/reimbursementRequests/submitRequest";
    // resetting error div
    document.getElementById("error-div").innerHTML = "";
    
    //retrieving user credentials
    let amount = document.getElementById("amount").value;
    let reimbursementType = document.getElementById("reimbursementType").value;
    let description = document.getElementById("description").value;


    let xhr = new XMLHttpRequest();
    
    xhr.open("POST", api1);

    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            let authToken = xhr.getResponseHeader("Authorization");
            /*
             storing authtoken in the session storage to be retrieved in different views
                - an item of key "token" and value authToken (Authorization token passed back from Javalin) is stored in the sessionStorage
             */
            sessionStorage.setItem("token", authToken);

            // navigate to a different view (ie: homepage)
            document.getElementById("error-div").innerHTML = "Request Successfully Submitted!";
            

        } else if (xhr.readyState === 4){
            // provide user with feedback of failure to login
            document.getElementById("error-div").innerHTML = "Unable to Submit Request.";
        }
    } 

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.setRequestHeader("Authorization", token);

    let requestBody = `amount=${amount}&reimbursementType=${reimbursementType}&description=${description}`;

    xhr.send(requestBody);
  
}