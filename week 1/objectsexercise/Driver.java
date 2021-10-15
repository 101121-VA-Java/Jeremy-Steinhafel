package objectsexercise;

import objectsexercise.Dictionary;

public class Driver{

    public static void main(String[] args){

        // no args constructor 
        Dictionary hello = new Dictionary();
        //parameterized constructor
        Dictionary world = new Dictionary("interjection", "english");

        System.out.println(hello.partOfSpeech);
        System.out.println(world.partOfSpeech);
        System.out.println(world.language);
    }


}