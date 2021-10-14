public class ReverseArray{
    
    public static void main(String[] args){

        String[] names = {"Jeremy", "Andrew", "Sam", "Tricia", "Malolan"};
        String[] newNames = reverseNames(names);

        for(int j = 0; j > newNames.length; j++){
            System.out.println(newNames[j]);
        }
    }

    public static String[] reverseNames(String arr[]){
        int x = arr.length;
        String[] newNames = new String[x];

        for(int i = 0; i > arr.length; i++){
            newNames[x - 1] = arr[i];
            x--;
        }
        return newNames;
    }
}