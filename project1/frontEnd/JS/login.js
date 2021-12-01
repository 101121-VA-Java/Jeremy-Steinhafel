function login(){

    let api1 = "http://localhost:8080/login";

    // resetting error div
    document.getElementById("error-div").innerHTML = "";
    
    //retrieving user credentials
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;


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


            console.log(authToken);
            // navigate to a different view (ie: homepage)
            //window.location.href="../index.html";

            let tokenArr = authToken.split(':');
            let role = tokenArr[1];
            
            if(role == 1){
               window.location.href="../HTML/index.html"; 
            }
            if(role == 2){
                window.location.href="../HTML/managerHome.html";
            }

        } else if (xhr.readyState === 4){
            // provide user with feedback of failure to login
            document.getElementById("error-div").innerHTML = "Invalid Username Or Password!";
        }
    } 

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    

    let requestBody = `username=${username}&password=${password}`;

    xhr.send(requestBody);

    


}