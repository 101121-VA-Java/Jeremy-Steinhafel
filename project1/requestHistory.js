let token = sessionStorage.getItem("token");

if (token) {
  let tokenArr = token.split(":");
  let id = tokenArr[0];
  let role = tokenArr[1];

  console.log(tokenArr);
  viewPending();
} else {
  window.location.href = "login.html";
}

async function viewPending() {
  let api1 = "http://localhost:8080/manager/viewHistory";

  let response = await fetch(api1, {
    headers: {
      Authorization: token,
    },
  });

  let requestHistory = await response.json();

  console.log(requestHistory);

  let tableBody = document.getElementById("history-tbody");

  tableBody.innerHTML = "";

  for (request of requestHistory) {
    console.log(request);
    let row = document.createElement("tr");

    let reimbursementIDTd = document.createElement("td");
    reimbursementIDTd.innerHTML = request.reimbursementID;

    let authorTd = document.createElement("td");
    authorTd.innerHTML = request.author;

    let amountTd = document.createElement("td");
    amountTd.innerHTML = request.amount;

    let submittedTd = document.createElement("td");
    let date1 = new Date(request.submitted);
    submittedTd.innerHTML =
      "Date: " +
      (date1.getMonth() + 1) +
      "/" +
      date1.getDate() +
      "/" +
      date1.getFullYear() +
      " " +
      date1.getHours() +
      ":" +
      date1.getMinutes() +
      ":" +
      date1.getSeconds();

    let resolvedTd = document.createElement("td");
    let date2 = new Date(request.resolved);
    resolvedTd.innerHTML =
      "Date: " +
      (date2.getMonth() + 1) +
      "/" +
      date2.getDate() +
      "/" +
      date2.getFullYear() +
      " " +
      date2.getHours() +
      ":" +
      date2.getMinutes() +
      ":" +
      date2.getSeconds();

    let descriptionTd = document.createElement("td");
    descriptionTd.innerHTML = request.description;

    let statusTd = document.createElement("td");
    if (request.statusID == 2) {
      statusTd.innerHTML = "Approved";
    }
    if (request.statusID == 3) {
      statusTd.innerHTML = "Denied";
    }

    let typeTd = document.createElement("td");
    if (request.typeID == 1) {
      typeTd.innerHTML = "Lodging";
    }
    if (request.typeID == 2) {
      typeTd.innerHTML = "Travel";
    }
    if (request.typeID == 3) {
      typeTd.innerHTML = "Food";
    }
    if (request.typeID == 4) {
      typeTd.innerHTML = "Other";
    }

    let resolverTd = document.createElement("td");
    resolverTd.innerHTML = request.resolver;

    row.appendChild(reimbursementIDTd);
    row.appendChild(authorTd);
    row.appendChild(amountTd);
    row.appendChild(submittedTd);
    row.appendChild(resolvedTd);
    row.appendChild(descriptionTd);
    row.appendChild(statusTd);
    row.appendChild(typeTd);
    row.appendChild(resolverTd);

    tableBody.appendChild(row);
  }
}
