let token = sessionStorage.getItem("token");

if (token) {
  let tokenArr = token.split(":");
  let id = tokenArr[0];
  let role = tokenArr[1];

  console.log(tokenArr);
  viewResolved();
} else {
  window.location.href = "login.html";
}

//  if(window.location.href="viewPending.html"){
//     viewPending();
//  }

async function viewResolved() {
  let api1 = "http://localhost:8080/reimbursementRequests/viewResolved";

  let response = await fetch(api1, {
    headers: {
      Authorization: token,
    },
  });

  let resolvedRequests = await response.json();

  console.log(resolvedRequests);

  let tableBody = document.getElementById("resolved-tbody");

  tableBody.innerHTML = "";

  for (request of resolvedRequests) {
    console.log(request);
    let row = document.createElement("tr");

    let amountTd = document.createElement("td");
    amountTd.innerHTML = request.amount;

    let submittedTd = document.createElement("td");
    let date = new Date(request.submitted);
    submittedTd.innerHTML =
      "Date: " +
      (date.getMonth() + 1) +
      "/" +
      date.getDate() +
      "/" +
      date.getFullYear() +
      " " +
      date.getHours() +
      ":" +
      date.getMinutes() +
      ":" +
      date.getSeconds();

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
    if (request.statusID == 1) {
      statusTd.innerHTML = "Pending";
    }
    if (request.statusID == 2) {
      statusTd.innerHTML = "Approved";
    }
    if (request.statusID == 3) {
      statusTd.innerHTML = "Denied";
    }

    let typeTd = document.createElement("td");
    if (request.typeID == 1) {
      typeTd.innerHTML = "Travel";
    }
    if (request.typeID == 2) {
      typeTd.innerHTML = "Lodging";
    }
    if (request.typeID == 3) {
      typeTd.innerHTML = "Food";
    }
    if (request.typeID == 4) {
      typeTd.innerHTML = "Other";
    }

    let resolverTd = document.createElement("td");
    resolverTd.innerHTML = request.resolver;

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
