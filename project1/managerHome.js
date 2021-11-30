let token = sessionStorage.getItem("token");

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
