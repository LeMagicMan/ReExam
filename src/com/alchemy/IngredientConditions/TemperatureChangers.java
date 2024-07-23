package com.alchemy.IngredientConditions;

import be.kuleuven.cs.som.annotate.Raw;

import java.util.Random;

/**********************************************************
 * A class representing a way to change temperatures with a device
 * defensively programmed
 *
 * @author BenDeMets
 * @version 1.0
 **********************************************************/

public abstract class TemperatureChangers extends Device {

    /**********************************************************
     * Variables
     **********************************************************/

    /**
     * variable to keep track of the amount the temperature in the device can deviate
     */
    private final int deviation;
    /**
     * variable to keep track if a device cools(true) ingredients or heats(false)
     */
    private final boolean isCooler;

    /**
     * variable to keep track of the temperature in the device
     */
    protected Temperature temperature;

    /**********************************************************
     * Constructors
     **********************************************************/

    /**
     * constructor to make a device be able to change temperature
     * @param temperature the temperature in the device
     * @param deviation the amount of deviation the device has
     * @param isCooler boolean true if the device cools false if it doesn't
     */
    @Raw
    public TemperatureChangers (Temperature temperature, int deviation, boolean isCooler){
        this.deviation = deviation;
        this.temperature = temperature;
        this.isCooler = isCooler;
    }

    /**********************************************************
     * Methods
     **********************************************************/

    /**
     * a function to start the reaction
     * @pre device must be in a laboratory
     * @throws NotInLaboratoryException when the device isn't in a laboratory
     */
    @Override
    public void react() throws NotInLaboratoryException{
        if(isNotInLaboratory()){
            throw new NotInLaboratoryException("Oven not in Laboratory");
        }
        if (ingredient != null){
            Temperature ingredientTemperature= ingredient.getTemperature();
            Float ingredientHotness = ingredientTemperature.getHotness();
            Float ingredientColdness = ingredientTemperature.getColdness();
            Float hotness = temperature.getHotness();
            Float coldness = temperature.getColdness();

            Random rand = new Random();
            int randomDeviation = rand.nextInt((2 * deviation) + 1) - deviation;

            if (isCooler && !ingredientTemperature.isColderThan(temperature)){
                ingredientTemperature.cool(ingredientHotness-hotness+coldness-ingredientColdness + randomDeviation);

            } else if ( !isCooler && !(ingredientHotness > hotness | ingredientColdness< coldness | (ingredientHotness.equals(hotness) && ingredientColdness.equals(coldness))) ){
                ingredientTemperature.heat(ingredientColdness - coldness + hotness - ingredientHotness + randomDeviation);
            }
        }
    }
}
