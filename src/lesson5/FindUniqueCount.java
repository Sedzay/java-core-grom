package lesson5;

public class FindUniqueCount {
    public static void main(String[] args) {
        int[] array = {11,12,10,5,4,44,100,44,11,10,11};
        System.out.println(uniqueCount(array));

    }

    static int uniqueCount (int[] array) {
        int unique = 0;
        int count = 0;
        for (int i = 0; i<array.length; i++) {
            for (int j = i+1; j<array.length; j++) {
                if(array[i] == array[j]) {
                    count++;
                    break;
                }
            }
            unique++;
        }
        return unique - count;
    }
}
