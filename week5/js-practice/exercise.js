// Problem 1: Create a function "isWeekday" which takes a single argument and returns true if the argument is a day during the work week. It should return false if it is not.

function isWeekday(input) {
    weekdayArray = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday"];
    for(let i = 0; i < weekdayArray.length; i++){
        if(input == weekdayArray[i]){
            console.log(input);
            console.log(weekdayArray[i]);
            return true;      
        } 
    }
    return false;
}

// Problem 2: Create a function "isEven" which takes a single argument and returns true if the argument is an even integer. It should return false if it is not

function isEven(input) {
    if(input % 2 == 0){
        return true;    
    } else{
        return false;
    }
    
}

// Problem 3: Write a JavaScript function which takes a single argument and returns the type

function findType(input) {
    
    return typeof input;
}

// Problem 4: Write a JavaScript function which takes a single argument and determines if it's prime. If it is, return true. If it's not return false.

function isPrime(input) {
    if(input == 2 ||  input == 3){
        return true;
    }
    if(input % 2 == 0 || input % 3 == 0){
        return false;
    }  else if( input == 1){
        return false;
    } else{
      return true;  
    }
    
}

// Problem 5: Write a JavaScript function which calculates the first number to the power of the second number. Do not do this recursively. Do not use the Math library.

function calculateExponent(base, exponent) {
    result = Math.pow(base, exponent);
    return result;
}