let token = sessionStorage.getItem("token");

feather.replace()

if (token) {
  let tokenArr = token.split(":");
  let id = tokenArr[0];
  let role = tokenArr[1];

//   if (role == 1) {
//     window.location.href = "../index.html";
//   }
//   if (role == 2) {
//     window.location.href = "managerHome.html";
//   }

  console.log(tokenArr);
} else {
  window.location.href = "login.html";
}

function logout() {
  sessionStorage.clear();
  window.location.href = "login.html";
}

function pendingManagerRequests() {
  window.location.href = "pendingManagerRequests.html";
}

function requestHistory() {
  window.location.href = "requestHistory.html";
}

function viewAllEmployees() {
  window.location.href = "allUsers.html";
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

dragElement(document.getElementById("manager-menu"));
dragElement(document.getElementById("employee-menu"));

function dragElement(elmnt) {
  var menuheader = elmnt.getElementsByClassName("menu-header")[0];
  var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
  if (menuheader) {
    // if present, the header is where you move the DIV from:
    menuheader.onmousedown = dragMouseDown;
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
    console.log(elmnt.id);
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

function collapseManagerMenu(){
  list2 = document.getElementById("manager-list-2");
  list3 = document.getElementById("manager-list-3");
  list4 = document.getElementById("manager-list-4");
  if(list2.style.display == "block"){
    list2.style.display = "none";
    list3.style.display = "none";
    list4.style.display = "none";
  }else {
    list2.style.display = "block";
    list3.style.display = "block";
    list4.style.display = "block";
  }
}


function collapseEmployeeMenu(){
  list2 = document.getElementById("employee-list-2");
  list3 = document.getElementById("employee-list-3");
  list4 = document.getElementById("employee-list-4");
  if(list2.style.display == "block"){
    list2.style.display = "none";
    list3.style.display = "none";
    list4.style.display = "none";
    
  } else {
    list2.style.display = "block";
    list3.style.display = "block";
    list4.style.display = "block";
  }
}