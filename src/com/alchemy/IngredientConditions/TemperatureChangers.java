package com.alchemy.IngredientConditions;

import java.util.Random;



public abstract class TemperatureChangers extends Device {

    private final int deviation;
    private final boolean isCooler;

    protected Temperature temperature;

    public TemperatureChangers (Temperature temperature, int deviation, boolean isCooler){
        this.deviation = deviation;
        this.temperature = temperature;
        this.isCooler = isCooler;
    }

    @Override
    public void react() throws NotInLaboratoryException{
        if(!isInLaboratory()){
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
