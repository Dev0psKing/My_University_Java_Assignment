Stock Price Analysis

This Java program, StockPriceAnalysis, is designed to perform various analyses on a set of stock prices. It provides functionalities to calculate the average stock price, find the maximum stock price, determine the occurrence count of a specific price, and compute the cumulative sum of stock prices.

### Usage

To use this program, you need to have Java installed on your system. You can compile and run the program using your preferred Java development environment or command-line tools.

### Methods

1. calculateAveragePrice
    - Calculates the average stock price from an array of stock prices.
    - Parameters: float[] stockPrices - an array of stock prices.
    - Returns: double - the average stock price.

2. findMaximumPrice
    - Finds the maximum stock price from an array of stock prices.
    - Parameters: float[] stockPrices - an array of stock prices.
    - Returns: float - the maximum stock price.

3. countOccurrences
    - Determines the occurrence count of a specific price in an array of stock prices.
    - Parameters: float[] stockPrices - an array of stock prices, float targetPrice - the price to count occurrences for.
    - Returns: int - the occurrence count of the target price.

4. computeCumulativeSum
    - Computes the cumulative sum of stock prices and returns it as an ArrayList.
    - Parameters: ArrayList<Float> stockPrices - a list of stock prices.
    - Returns: ArrayList<Float> - the cumulative sum of stock prices.

### Example

```java
public static void main(String[] args) {
    float[] stockPrices = {100.0f, 105.5f, 98.0f, 103.2f, 102.8f, 101.5f, 104.7f, 99.8f, 102.1f, 103.4f};
    ArrayList<Float> stockPricesList = new ArrayList<>();
    for (float price : stockPrices) {
        stockPricesList.add(price);
    }

    System.out.println("Average stock price: " + calculateAveragePrice(stockPrices));
    System.out.println("Maximum stock price: " + findMaximumPrice(stockPrices));
    System.out.println("Occurrence count of 102.1: " + countOccurrences(stockPrices, 102.1f));
    System.out.println("Cumulative sum of stock prices: " + computeCumulativeSum(stockPricesList));
}
```