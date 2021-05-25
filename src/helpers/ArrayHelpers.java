package helpers;

public class ArrayHelpers {
    public static long[] addToArray(long[] array, long element) {
        long[] newArr = new long[array.length +1];
        int i = 0;
        while(i < array.length) {
            newArr[i] = array[i];
            i++;
        }
        newArr[i] = element;

        return newArr;
    }

    private static <T> boolean arrayContains(T[] array, T element, int startingIndex) {
        for(int i = startingIndex; i < array.length; i++) {
            if (array[i].equals(element))
                return true;
        }

        return false;
    }
}
