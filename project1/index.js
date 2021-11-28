let token = sessionStorage.getItem("token");

if (token) {
    let tokenArr = token.split(':');
    let id = tokenArr[0];
    let role = tokenArr[1];
 
    console.log(tokenArr);
 } else {
    window.location.href= "login.html";
 }

function logout(){
    sessionStorage.clear();
    window.location.href = "login.html";
 }

 function newRequest(){
     window.location.href = "newRequest.html";
 }

 function viewPending(){
    window.location.href = "viewPending.html";
}

function viewResolved(){
    window.location.href = "viewResolved.html";
}

function employeeInfo(){
    window.location.href = "employeeInfo.html";
}