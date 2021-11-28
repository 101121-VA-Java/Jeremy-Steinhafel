let token = sessionStorage.getItem("token");

if (token) {
    let tokenArr = token.split(':');
    let id = tokenArr[0];
    let role = tokenArr[1];
 
    console.log(tokenArr);
    viewPending();
 } else {
    window.location.href= "login.html";
 }

//  if(window.location.href="viewPending.html"){
//     viewPending();
//  }

 async function viewPending(){
    let api1 = "http://localhost:8080/reimbursementRequests/viewPending";

    let response = await fetch(api1, {
        headers: {
            'Authorization': token
        }
    });

    let pendingRequests = await response.json();

    console.log(pendingRequests);

    let tableBody = document.getElementById('pending-tbody');

    tableBody.innerHTML = '';

    for (request of pendingRequests) {
        console.log(request);
        let row = document.createElement('tr');

        let amountTd = document.createElement('td');
        amountTd.innerHTML = request.amount;

        let submittedTd = document.createElement('td');
        let date = new Date(request.submitted);
        submittedTd.innerHTML = "Date: " + (date.getMonth()+1) +
            "/"+(date.getDate())+
            "/"+date.getFullYear()+
            " "+date.getHours()+
            ":"+date.getMinutes()+
            ":"+date.getSeconds();

        let descriptionTd = document.createElement('td');
        descriptionTd.innerHTML = request.description;

        let statusTd = document.createElement('td');
        if(request.statusID == 1){
            statusTd.innerHTML = "Pending";
        }
        
        let typeTd = document.createElement('td');
        if(request.typeID == 2){
            typeTd.innerHTML = "Lodging";
        }
       
        row.appendChild(amountTd);
        row.appendChild(submittedTd);
        row.appendChild(descriptionTd);
        row.appendChild(statusTd);
        row.appendChild(typeTd);

        tableBody.appendChild(row);
    }

 }