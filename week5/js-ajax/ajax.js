// document.getElementById('getData').onclick = getData;
document.getElementById("getData").addEventListener("click", getData);

let apiUrl = "https://pokeapi.co/api/v2/pokemon";

function getData() {
  //retrieve user input for Pokemon id
  let userInput = document.getElementById("dataInput").value;

  // 1: create XMLHttpRequest object
  let xhr = new XMLHttpRequest();

  //2: set a callback function to the readystate change event of that object
  xhr.onreadystatechange = receiveData;

  //3: parameterize / open the request object (set request information)
  xhr.open("GET", `${apiUrl}/${userInput}`);

  //4: send the request
  xhr.send();

  function receiveData() {
    // what to be done once the data is ready
    if (xhr.readyState === 4) {
      let dataSpan = document.getElementById("data");
      dataSpan.innerHTML = "";
      if (xhr.status >= 200 && xhr.status < 300) {
        let response = xhr.response;

        // Converting JSON data to JS object
        response = JSON.parse(response);

        // data processing behavior
        populateData(response);
      } else {
        dataSpan.innerHTML = `<img src="https://lh3.googleusercontent.com/proxy/PwTFjS3gKK23XkUpAs0ZPWDjjABchQ9pjEOdmbPJujuZbJd1t6tZYLwCG1R4vRFMoQgTXdehLcWyWY0hVWNDzC9TwnR1VgMNLNkE4QopBJhst4ZFvKVNoCLZqSom97GfoAAH3q3tBihVMw">`;
      }
    }
  }
}

function populateData(response) {

  console.log(response);
  let pokemonData = response;
  let pokemonName = response.name;
  let pokemonType1 = response.types[0].type.name;
  let pokemonType2 = response.types[1].type.name;
  let pokemonHeight = response.height;
  let pokemonWeight = response.weight;
  let pokemonAbilities = response.abilities[0].ability.name;
  let pokemonMoves = response.moves;


  let display = 
    `<h1>${response.name}</h1>
    <h3>Type: ${pokemonType1} , ${pokemonType2} </h3>
    <h3>Height: ${pokemonHeight}</h3>
    <h3>Weight: ${pokemonWeight}</h3>
    <h3>Abilities: ${pokemonAbilities}</h3>
    <h3>Moves: ${pokemonMoves}</h3>
    `;
  document.getElementById("pokemon-data").innerHTML = display;



  // Using DOM manipulation, display information about pokemon to the webpage
 
}
