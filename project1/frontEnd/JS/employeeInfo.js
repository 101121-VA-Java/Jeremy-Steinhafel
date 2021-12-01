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
  let api1 = "http://localhost:8080/employee/" + token.split(":")[0];

  let response = await fetch(api1, {
    headers: {
      Authorization: token,
    },
  });

  let employeeInformation = await response.json();

  console.log(employeeInformation);

  let tableBody = document.getElementById("employee-tbody");

  tableBody.innerHTML = "";

  let row = document.createElement("tr");

  let usernameTd = document.createElement("td");
  usernameTd.innerHTML =
    "<input type='checkbox' id='username'></input>" +
    employeeInformation.username;

  let firstNameTd = document.createElement("td");
  firstNameTd.innerHTML =
    "<input type='checkbox' id='first-name'></input>" +
    employeeInformation.firstName;

  let lastNameTd = document.createElement("td");
  lastNameTd.innerHTML =
    "<input type='checkbox' id='last-name'></input>" +
    employeeInformation.lastName;

  let emailTd = document.createElement("td");
  emailTd.innerHTML =
    "<input type='checkbox' id='email'></input>" + employeeInformation.email;

  row.appendChild(usernameTd);
  row.appendChild(firstNameTd);
  row.appendChild(lastNameTd);
  row.appendChild(emailTd);

  tableBody.appendChild(row);
}

function updateReveal() {
  let anythingChecked = false;
  let updateForm = "";
  if (document.getElementById("username").checked) {
    updateForm += `
        <label>Username:</label>
        <input type="text" id="update-username" name='username' required>
        `;
    anythingChecked = true;
  }
  if (document.getElementById("first-name").checked) {
    updateForm += `
        <label>First Name:</label>
        <input type="text" id="update-first-name" name='firstName' required>
        `;
    anythingChecked = true;
  }
  if (document.getElementById("last-name").checked) {
    updateForm += `
        <label>Last Name:</label>
        <input type="text" id="update-last-name"  name='lastName' required>
        `;
    anythingChecked = true;
  }
  if (document.getElementById("email").checked) {
    updateForm += `
        <label>Email:</label>
        <input type="text" id="update-email" name='email' required>
        `;
    anythingChecked = true;
  }

  if (anythingChecked) {
    updateForm += `<button type='button' onclick='submitEmployeeUpdate()'>Submit Changes</button>`;
    updateForm = '<form id="update-form-form">' + updateForm + "</form>";
  }

  document.getElementById("update-form").innerHTML = updateForm;
}

async function submitEmployeeUpdate() {
  const form = document.getElementById("update-form-form");
  if (!form) return;

  const formData = new FormData(form);
  const employeeId = token.split(":")[0];

  let response = await fetch(`http://localhost:8080/employee/${employeeId}`, {
    method: "PUT",
    headers: {
      Authorization: token,
    },
    body: JSON.stringify(Object.fromEntries(formData)),
  });

  if (response.status == 200) {
    window.location.reload();
  } else {
    document.getElementById("error-div").innerHTML =
      "Unable to update your information.";
  }
}
