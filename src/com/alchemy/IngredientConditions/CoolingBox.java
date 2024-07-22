package com.alchemy.IngredientConditions;

import be.kuleuven.cs.som.annotate.Raw;
import com.alchemy.IngredientContainer;

/**********************************************************
 * A class representing a coolingbox, used to cool an ingredient
 * defensively programmed
 *
 * @author MiroVanHoef
 * @author BenDeMets
 * @author SimonVandeputte
 * @version 1.0
**********************************************************/
public class CoolingBox extends TemperatureChangers {

    /**********************************************************
     * Constructors
     **********************************************************/

    /**
     * creates a CoolingBox with given coldness and hotness
     * @param coldness the coldness of the temperature of the CoolingBox
     * @param hotness the hotness of the temperature of the CoolingBox
     */
    @Raw
    public CoolingBox(Float coldness, Float hotness) {
        super(new Temperature(0F,0F),0, true);
        setTemperature(coldness, hotness);
    }

    /**
     * creates a coolingbox with temperature 0,20
     */
    @Raw
    public CoolingBox() {
        super(new Temperature(0F,20F),0, true);
    }


    /**********************************************************
     * Getters and Setters
     **********************************************************/

    /**
     * method to set the temperature of the coolingBox
     * @pre either hotness or coldness is 0
     * @param coldness the coldness the oven will be set to
     * @param hotness the hotness the coolingBox will be set to
     */
    public void setTemperature(Float coldness,Float hotness) {
        temperature.heat(temperature.getColdness());
        temperature.cool(temperature.getHotness());
        temperature.cool(coldness);
        temperature.heat(hotness);
    }

    /**
     * method to set the temperature of the coolingBox
     * @param temperature the temperature the coolingBox will be set to
     */
    public void setTemperature(Temperature temperature) {
        Float coldness = temperature.getColdness();
        Float hotness = temperature.getHotness();
        this.temperature.heat(temperature.getColdness());
        this.temperature.cool(temperature.getHotness());
        this.temperature.cool(coldness);
        this.temperature.heat(hotness);
    }


    /**********************************************************
     * Mutators
     **********************************************************/


    /**
     * method used to add an ingredient to the device
     * @pre device must be empty
     * @param container the container containing the ingredient that has to be added to the device
     * @throws DeviceFullException if the device is full, deviceFullException is thrown.
     */
    @Override
    public void addIngredient(IngredientContainer container) throws DeviceFullException {
        if (ingredient != null){
            throw new DeviceFullException("CoolingBox Full");
        }
        else{
            super.addIngredient(container);
        }

    }

    /**
     * method to cool cooling boxes own temperature by 10 degrees
     * @effect the temperature of cooling box will be lowered by 10 degrees
     */
    public void lowerOwnTemp() {
        temperature.cool(10F);
    }
}
