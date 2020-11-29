package com.facebook.bitEncoderDecoder;

public class Stage2 {

    public String send(String input){
        InputValidator.validateInput(input);
        String[] triplets = prepareSegmentedInput(input);
        if (triplets.length == 0){
            return "";
        }
        StringBuilder target = new StringBuilder();
        for (String triplet : triplets){
            String distorted = generateNoise(triplet);
            target.append(distorted);
        }

        return target.toString();
    }

    private String generateNoise(String triplet) {
        int randomPosition = Utils.getRandomInRange(0, 2);

        char[] letters = triplet.toCharArray();
        char charToChange = letters[randomPosition];
        char distorted = Utils.randomizeChar(charToChange);
        letters[randomPosition] = distorted;
        return String.valueOf(letters);
    }


    private String[] prepareSegmentedInput(String input){
        if (input.isBlank()){
            return new String[0];
        }

        int numberOfTriplets = (int) Math.round(input.length() / 3.0);
        String[] target = new String[numberOfTriplets];

        StringBuilder source = new StringBuilder(input);
        int counter = 0;
        while (source.length() >= 3){
            target[counter++] = source.substring(0, 3);
            source.delete(0, 3);
        }
        return target;
    }


}
