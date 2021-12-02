let token = sessionStorage.getItem("token");
reimbursementIDArray = [];

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
  let api1 = "http://localhost:8080/manager/viewAllPending";

  let response = await fetch(api1, {
    headers: {
      Authorization: token,
    },
  });

  let pendingRequests = await response.json();

  console.log(pendingRequests);

  let tableBody = document.getElementById("pending-tbody");

  tableBody.innerHTML = "";
  let checkboxCounter = 0;

  for (request of pendingRequests) {
    checkboxCounter++;
    let checkboxID = "checkbox" + checkboxCounter;

    let reimbursementID = "reimbursement" + checkboxCounter;

    console.log(request);
    let row = document.createElement("tr");

    let selectReimbursement = document.createElement("td");
    selectReimbursement.innerHTML = `<input type="checkbox" class="check-box" id="${checkboxID}" name="checkbox" onclick="showApproveOrDeny(this, ${request.reimbursementID})"></input>`;

    let reimbursementIDTd = document.createElement("td");
    reimbursementIDTd.innerHTML = request.reimbursementID;
    reimbursementIDTd.id = reimbursementID;

    let authorTd = document.createElement("td");
    authorTd.innerHTML = request.author;

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

    let descriptionTd = document.createElement("td");
    descriptionTd.innerHTML = request.description;

    let statusTd = document.createElement("td");
    if (request.statusID == 1) {
      statusTd.innerHTML = "Pending";
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

    row.appendChild(selectReimbursement);
    row.appendChild(reimbursementIDTd);
    row.appendChild(authorTd);
    row.appendChild(amountTd);
    row.appendChild(submittedTd);
    row.appendChild(descriptionTd);
    row.appendChild(statusTd);
    row.appendChild(typeTd);

    tableBody.appendChild(row);
  }
}

function showApproveOrDeny(checkbox, reimbursementID) {
  console.log(checkbox);
  
  if (checkbox.checked) {
    reimbursementIDArray.push(reimbursementID);
    console.log(reimbursementIDArray);
  } else {
    for (let i = 0; i < reimbursementIDArray.length; i++) {
      if (reimbursementID == reimbursementIDArray[i]) {
        reimbursementIDArray.splice(i, 1);
      }
    }
  }
  if (reimbursementIDArray.length > 0) {
    document.getElementById(
      "accept-or-reject"
    ).innerHTML = `<button onclick="approve()" style="width:200px; margin-right: 20px;">Approve</button><button style="width:200px;" onclick="deny()">Deny</button>`;
  } else {
    document.getElementById("accept-or-reject").innerHTML = "";
  }
}

async function approve() {
  console.log(reimbursementIDArray);

  if (reimbursementIDArray.length == 0) return;

  const body = reimbursementIDArray;

  let response = await fetch(
    `http://localhost:8080/manager/viewAllPending/Approve`,
    {
      method: "PUT",
      headers: {
        Authorization: token,
      },
      body: body,
    }
  );

  if (response.status == 200) {
    window.location.reload();
  } else {
    document.getElementById("error-div").innerHTML =
      "Unable to approve requests!";
  }
}

async function deny() {
  console.log(reimbursementIDArray);

  if (reimbursementIDArray.length == 0) return;

  const body = reimbursementIDArray;

  let response = await fetch(
    `http://localhost:8080/manager/viewAllPending/Deny`,
    {
      method: "PUT",
      headers: {
        Authorization: token,
      },
      body: body,
    }
  );

  if (response.status == 200) {
    window.location.reload();
  } else {
    document.getElementById("error-div").innerHTML = "Unable to deny requests!";
  }
}
