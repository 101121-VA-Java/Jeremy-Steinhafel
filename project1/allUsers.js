let token = sessionStorage.getItem("token");

if (token) {
  let tokenArr = token.split(":");
  let id = tokenArr[0];
  let role = tokenArr[1];

  console.log(tokenArr);
  viewInformation();
} else {
  window.location.href = "login.html";
}

async function viewInformation() {
  let api1 = "http://localhost:8080/manager/employees";

  let response = await fetch(api1, {
    headers: {
      Authorization: token,
    },
  });

  let employeeInformation = await response.json();

  console.log(employeeInformation);

  let tableBody = document.getElementById("employee-tbody");

  tableBody.innerHTML = "";

  for (employee of employeeInformation) {
    console.log(employee);
    let row = document.createElement("tr");

    let moreTd = document.createElement("td");
    moreTd.innerHTML = `<a onclick="showUserRequests(${employee.userID})">+</a>`;

    let userIDTd = document.createElement("td");
    userIDTd.innerHTML = employee.userID;

    let usernameTd = document.createElement("td");
    usernameTd.innerHTML = employee.username;

    let firstNameTd = document.createElement("td");
    firstNameTd.innerHTML = employee.firstName;

    let lastNameTd = document.createElement("td");
    lastNameTd.innerHTML = employee.lastName;

    let emailTd = document.createElement("td");
    emailTd.innerHTML = employee.email;

    let roleIDTd = document.createElement("td");
    if (employee.roleID == 1) {
      roleIDTd.innerHTML = "Employee";
    }
    if (employee.roleID == 2) {
      roleIDTd.innerHTML = "Manager";
    }

    row.appendChild(moreTd)
    row.appendChild(userIDTd);
    row.appendChild(usernameTd);
    row.appendChild(firstNameTd);
    row.appendChild(lastNameTd);
    row.appendChild(emailTd);
    row.appendChild(roleIDTd);

    tableBody.appendChild(row);
  }
}


async function showUserRequests(userID) {
  
  let api1 = "http://localhost:8080/manager/employees/" + userID;

  let response = await fetch(api1, {
    headers: {
      Authorization: token,
    },
  });

  let userRequests = await response.json();

  console.log(userRequests);

  let tableBody = document.getElementById("employee-tbody");

  let row = document.createElement("tr");

    let reimbursementIDTd = document.createElement("td");
    reimbursementIDTd.innerHTML = "Reimbursement ID";

    let amountTd = document.createElement("td");
    amountTd.innerHTML = "Amount";

    let submittedTd = document.createElement("td");
    submittedTd.innerHTML = "Date Submitted";

    let resolvedTd = document.createElement("td");
    resolvedTd.innerHTML = "Date Resolved";

    let descriptionTd = document.createElement("td");
    descriptionTd.innerHTML = "Description";

    let statusTd = document.createElement("td");
    statusTd.innerHTML = "Status";

    let typeTd = document.createElement("td");
    typeTd.innerHTML = "Type";

    let resolverTd = document.createElement("td");
    resolverTd.innerHTML = "Resolver";

    row.appendChild(reimbursementIDTd);
    row.appendChild(amountTd);
    row.appendChild(submittedTd);
    row.appendChild(resolvedTd);
    row.appendChild(descriptionTd);
    row.appendChild(statusTd);
    row.appendChild(typeTd);
    row.appendChild(resolverTd);

    tableBody.appendChild(row);

  for (request of userRequests) {
    console.log(request);

    let row = document.createElement("tr");

    let reimbursementIDTd = document.createElement("td");
    reimbursementIDTd.innerHTML = request.reimbursementID;

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
