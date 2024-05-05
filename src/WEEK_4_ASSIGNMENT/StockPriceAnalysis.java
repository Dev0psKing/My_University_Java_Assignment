package WEEK_4_ASSIGNMENT;



import java.util.ArrayList;



public class StockPriceAnalysis {



    // Calculate the average stock price

    public static double calculateAveragePrice(float[] stockPrices) {

        double sum = 0;

        for (float price : stockPrices) {

            sum += price;

        }

        return sum / stockPrices.length;

    }



    // Find the maximum stock price

    public static float findMaximumPrice(float[] stockPrices) {

        float maxPrice = stockPrices[0];

        for (float price : stockPrices) {

            if (price > maxPrice) {

                maxPrice = price;

            }

        }

        return maxPrice;

    }



    // Determine the occurrence count of a specific price

    public static int countOccurrences(float[] stockPrices, float targetPrice) {

        int count = 0;

        for (float price : stockPrices) {

            if (price == targetPrice) {

                count++;

            }

        }

        return count;

    }



    // Compute the cumulative sum of stock prices

    public static ArrayList<Float> computeCumulativeSum(ArrayList<Float> stockPrices) {

        ArrayList<Float> cumulativeSum = new ArrayList<>();

        float sum = 0;

        for (float price : stockPrices) {

            sum += price;

            cumulativeSum.add(sum);

        }

        return cumulativeSum;

    }



    public static void main(String[] args) {

        // Example usage

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

}