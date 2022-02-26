package Models;


import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Configuration {

    @Expose
    ArrayList<Integer> pieceNumbers;

    public Configuration(int[] numbers) {
        pieceNumbers = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            pieceNumbers.add(numbers[i]);
        }
    }

    public int getPieceNumber(int index) {

        return pieceNumbers.get(index);
    }

    public Configuration getClone() {
        int[] numbers = new int[pieceNumbers.size()];
        for (int i = 0; i < pieceNumbers.size(); i++) {
            numbers[i] = pieceNumbers.get(i);

        }
        Configuration clone = new Configuration(numbers);
        return clone;
    }

    void swap(int i,int j){
        Collections.swap(pieceNumbers,i,j);
    }

    public ArrayList<Integer> getPieceNumbers() {
        return pieceNumbers;
    }

    @Override
    public String toString() {
        return pieceNumbers.toString();
    }
}
