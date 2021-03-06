package isu.engine;

/**
 * Gets the stock price and bonus values of a chain
 */
public class PriceChart {


    public static int getStockPrice(HotelChainCategory  chainCategory, int chainSize){
        int price;

        if(chainSize < 0){
            throw new IllegalArgumentException("Chain size cannot be negative.");
        }

        if (chainSize < 2){
            price = 0;
        } else if (chainSize <= 5){
            price = 100 * chainSize;
        } else if (chainSize <= 10){
            price = 600;
        } else if (chainSize <= 20){
            price = 700;
        } else if (chainSize <= 30){
            price = 800;
        } else if (chainSize <= 40){
            price = 900;
        } else {
            price = 1000;
        }

        if (chainCategory == HotelChainCategory.AVERAGE){
            price += 100;
        } else if (chainCategory == HotelChainCategory.EXPENSIVE){
            price += 200;
        }

        return price;
    }

    public static int getFirstBonus(HotelChainCategory chainCategory, int chainSize){
        if(chainSize < 0){
            throw new IllegalArgumentException("Chain size cannot be negative.");
        }
        return getStockPrice(chainCategory, chainSize) * 10;
    }

    public static int getSecondBonus(HotelChainCategory chainCategory, int chainSize){
        if(chainSize < 0){
            throw new IllegalArgumentException("Chain size cannot be negative.");
        }
        return getStockPrice(chainCategory, chainSize) * 5;
    }
}
