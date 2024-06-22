package WEEK_9_ASSIGNMENTS;

import java.util.*;

public class TextAnalysisTool {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. User Input
        System.out.println("Enter a paragraph or lengthy text:");
        String text = scanner.nextLine();

        // 2. Character Count
        int charCount = text.length();
        System.out.println("Total number of characters: " + charCount);

        // 3. Word Count
        String[] words = text.split("\\s+");
        int wordCount = words.length;
        System.out.println("Total number of words: " + wordCount);

        // 4. Most Common Character
        Map<Character, Integer> charFrequency = new HashMap<>();
        for (char c : text.toLowerCase().toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
            }
        }
        char mostCommonChar = Collections.max(charFrequency.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("Most common character: " + mostCommonChar);

        // 5. Character Frequency
        System.out.println("Enter a character to check its frequency:");
        char charToCheck = scanner.next().toLowerCase().charAt(0);
        int charFreq = charFrequency.getOrDefault(charToCheck, 0);
        System.out.println("Frequency of '" + charToCheck + "': " + charFreq);

        // 6. Word Frequency
        System.out.println("Enter a word to check its frequency:");
        scanner.nextLine(); // Consume newline
        String wordToCheck = scanner.nextLine().toLowerCase();
        int wordFreq = 0;
        for (String word : words) {
            if (word.toLowerCase().equals(wordToCheck)) {
                wordFreq++;
            }
        }
        System.out.println("Frequency of '" + wordToCheck + "': " + wordFreq);

        // 7. Unique Words
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
        System.out.println("Number of unique words: " + uniqueWords.size());

        scanner.close();
    }
}