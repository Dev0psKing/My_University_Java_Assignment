package WEEK_9_ASSIGNMENTS;



import java.util.*;



public class TextAnalysisTool {



    public static void main(String[] args) {



        // Initialize scanner for user input

        Scanner scanner = new Scanner(System.in);



        // Get user input text

        System.out.println("Enter a paragraph or lengthy text:");

        String text = scanner.nextLine();



        // Ensure input is not empty

        while (text.trim().isEmpty()) {

            System.out.println("Input cannot be empty. Please enter a paragraph or lengthy text:");

            text = scanner.nextLine();

        }



        // Calculate and display character count

        int charCount = text.length();

        System.out.println("Total number of characters: " + charCount);



        // Calculate and display word count

        String[] words = text.split("\\s+");

        int wordCount = words.length;

        System.out.println("Total number of words: " + wordCount);



        // Calculate character frequency

        Map<Character, Integer> charFrequency = new HashMap<>();

        for (char c : text.toLowerCase().toCharArray()) {

            if (Character.isLetterOrDigit(c)) {

                charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);

            }

        }



        // Find and display the most common character

        if (charFrequency.isEmpty()) {

            System.out.println("No alphanumeric characters found.");

        } else {

            char mostCommonChar = Collections.max(charFrequency.entrySet(), Map.Entry.comparingByValue()).getKey();

            System.out.println("Most common character: " + mostCommonChar);

        }



        // Get character from user to check its frequency

        System.out.println("Enter a character to check its frequency:");

        String charInput = scanner.next().toLowerCase();

        while (charInput.length() != 1 || !Character.isLetterOrDigit(charInput.charAt(0))) {

            System.out.println("Invalid input. Enter a single letter or digit:");

            charInput = scanner.next().toLowerCase();

        }

        char charToCheck = charInput.charAt(0);

        int charFreq = charFrequency.getOrDefault(charToCheck, 0);

        System.out.println("Frequency of '" + charToCheck + "': " + charFreq);



        // Get word from user to check its frequency

        System.out.println("Enter a word to check its frequency:");

        scanner.nextLine(); // Consume newline

        String wordToCheck = scanner.nextLine().toLowerCase();

        while (wordToCheck.trim().isEmpty()) {

            System.out.println("Input cannot be empty. Enter a word to check its frequency:");

            wordToCheck = scanner.nextLine().toLowerCase();

        }



        // Calculate and display word frequency

        int wordFreq = 0;

        for (String word : words) {

            if (word.toLowerCase().equals(wordToCheck)) {

                wordFreq++;

            }

        }

        System.out.println("Frequency of '" + wordToCheck + "': " + wordFreq);



        // Calculate and display number of unique words

        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));

        System.out.println("Number of unique words: " + uniqueWords.size());



        // Close the scanner

        scanner.close();

    }

}


