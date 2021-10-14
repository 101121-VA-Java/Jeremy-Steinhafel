package objectsexercise;

public class Dictionary{
    //2 states
    public String partOfSpeech;
    public String language;

    //1 behavior
    public Dictionary(){}

    public Dictionary(String partOfSpeech, String language){
        this.partOfSpeech = partOfSpeech;
        this.language = language;
    }
}