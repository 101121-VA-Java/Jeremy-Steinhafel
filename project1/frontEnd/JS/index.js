let token = sessionStorage.getItem("token");

feather.replace()

if (token) {
    let tokenArr = token.split(':');
    let id = tokenArr[0];
    let role = tokenArr[1];
    
    // if(role == 2){
    //     document.getElementById("manager-home-display").style.display = "block";
    // } else{
    //     document.getElementById("manager-home-display").style.display = "none ";
    // }

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

dragElement(document.getElementById("menu"));

function dragElement(elmnt) {
  var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
  if (document.getElementById("menuheader")) {
    // if present, the header is where you move the DIV from:
    document.getElementById("menuheader").onmousedown = dragMouseDown;
  } else {
    // otherwise, move the DIV from anywhere inside the DIV:
    elmnt.onmousedown = dragMouseDown;
  }

  function dragMouseDown(e) {
    e = e || window.event;
    e.preventDefault();
    // get the mouse cursor position at startup:
    pos3 = e.clientX;
    pos4 = e.clientY;
    document.onmouseup = closeDragElement;
    // call a function whenever the cursor moves:
    document.onmousemove = elementDrag;
  }

  function elementDrag(e) {
    e = e || window.event;
    e.preventDefault();
    // calculate the new cursor position:
    pos1 = pos3 - e.clientX;
    pos2 = pos4 - e.clientY;
    pos3 = e.clientX;
    pos4 = e.clientY;
    // set the element's new position:
    elmnt.style.top = (elmnt.offsetTop - pos2) + "px";
    elmnt.style.left = (elmnt.offsetLeft - pos1) + "px";
  }

  function closeDragElement() {
    // stop moving when mouse button is released:
    document.onmouseup = null;
    document.onmousemove = null;
  }
}