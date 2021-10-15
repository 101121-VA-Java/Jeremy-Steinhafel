public class Calculator{
    public static void main (String[] args) {

        int a = 8;
        int b = 9;
        int sum = add(a,b);
        int difference = subtract(a,b);
        int times = multiply(a,b);
        int left = remainder(a,b);
        
        System.out.println(sum);
        System.out.println(difference);
        System.out.println(times);
        System.out.println(left);

        String day = "Wednesday";
        String whatDay = Weekday(day);
        System.out.println(whatDay);
        
        int downFrom = 10;
        int counter = countDown(downFrom);

        String someoneNew = "Tom";
        String helloStatement = greetings(someoneNew);
        System.out.println(helloStatement);
	}

    public static int add(int a, int b){
        return a + b;
    }

    public static int subtract(int a, int b){
        return a - b;
    }

    public static int multiply(int a, int b){
        return a * b;
    }

    public static int remainder(int a, int b){
        return a % b;
    }

    public static String Weekday(String day){
        String typeOfDay;
        switch(day) {
            case "Monday":
                typeOfDay = "The best day of the week!";
                break;
            case "Tuesday":
                typeOfDay = "What is there to say about Tuesday";
                break;
            case "Wednesday":
                typeOfDay ="It is Wednesday, my dudes!";
                break;
            case "Thursday":
                typeOfDay = "Remember in school, Thursdays were short.";
                break;
            case "Friday":
                typeOfDay ="Oh no! Friday!";
                break;
            default:
                typeOfDay = "It is not a Weekday. Relax.";
        }
        return typeOfDay;
    }

    public static int countDown(int startValue){
        while(startValue > 0){
            System.out.println(startValue);
            startValue--;
            if (startValue < 0) {
                System.out.println("Input must be positive or 0.");
            }
        }
        return startValue;
    }

    public static String greetings(String name){
        String helloStatement;
        helloStatement = "Hello, "+ name + ". It is nice to meet you!";
        return helloStatement;
    }

}