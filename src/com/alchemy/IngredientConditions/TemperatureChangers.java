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
     * variable that defines the type of TemperatureChanger
     */
    private final TemperatureChangerType type;
    /**
     * variable to keep track of the amount the temperature in the device can deviate
     */
    private final int deviation;


    /**
     * enum to keep track if a device cools ingredients or heats
     */
    public enum TemperatureChangerType {
        Heater("Heater"),
        Cooler("cooler");

        private final String typeName;

        TemperatureChangerType(String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }
    }

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
     */
    @Raw
    public TemperatureChangers (Temperature temperature, int deviation, TemperatureChangerType type){
        this.deviation = deviation;
        this.temperature = temperature;
        this.type = type;
    }

    /**********************************************************
     * Getters and Setters
     **********************************************************/

    /**
     * method to set the temperature of a device
     * @pre either hotness or coldness is 0
     * @param coldness the coldness the device will be set to
     * @param hotness the hotness the device will be set to
     */
    public void setTemperature(Float coldness,Float hotness) {
        temperature.heat(temperature.getColdness());
        temperature.cool(temperature.getHotness());
        temperature.cool(coldness);
        temperature.heat(hotness);
    }

    /**
     * method to set the temperature of the device
     * @param temperature the temperature the device will be set to
     */
    public void setTemperature(Temperature temperature) {
        float coldness = temperature.getColdness();
        float hotness = temperature.getHotness();
        this.temperature.heat(this.temperature.getColdness());
        this.temperature.cool(this.temperature.getHotness());
        this.temperature.cool(coldness);
        this.temperature.heat(hotness);
    }

    /**
     * a getter for the temperature of a device
     * @return the Temperature of the device
     */
    public Temperature getTemperature() {
        return this.temperature;
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

            switch (this.type){
                case Cooler:
                    if(!ingredientTemperature.isColderThan(temperature)) {
                        ingredientTemperature.cool(ingredientHotness - hotness + coldness - ingredientColdness + randomDeviation);
                    }
                    break;
                case Heater:
                    if(!ingredientTemperature.isHotterThan(temperature)) {
                        ingredientTemperature.heat(ingredientColdness - coldness + hotness - ingredientHotness + randomDeviation);
                    }
                    break;
            }
        }
    }
}
