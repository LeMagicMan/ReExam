package com.alchemy.IngredientConditions;
import be.kuleuven.cs.som.annotate.Raw;
import com.alchemy.IngredientContainer;

import static com.alchemy.IngredientConditions.TemperatureChangers.TemperatureChangerType.Heater;


/**********************************************************
 * A class representing an Oven, used to heat up an ingredient
 * defensively programmed
 *
 * @author MiroVanHoef
 * @author BenDeMets
 * @author SimonVandeputte
 * @version 1.0
**********************************************************/
public class Oven extends TemperatureChangers {

    /**********************************************************
     * Constructors
     **********************************************************/

    /**
     * Creates a new oven with a temperature with coldness and hotness as given
     * @param coldness the coldness of the temperature of the oven
     * @param hotness the hotness of the temperature of the oven
     */
    @Raw
    public Oven(Float coldness, Float hotness){
        super(new Temperature(0F,0F),5, Heater);
        setTemperature(coldness,hotness);
    }

    /**
     * creates an oven with temperature [0,20]
     */
    @Raw
    public Oven(){
        super(new Temperature(0F,20F),5, Heater);
    }


    /**********************************************************
     * Methods
     **********************************************************/

    /**
     * method for adding an ingredient to the oven
     * @pre device must be empty
     * @param container the container containing the ingredient that has to be added to the device
     * @throws DeviceFullException if there already is an ingredient in the oven, the device is considered full
     */
    @Override
    public void addIngredient(IngredientContainer container) throws DeviceFullException {
        if (ingredient != null){
            throw new DeviceFullException("Oven Full");
        }
        else{
            super.addIngredient(container);
        }
    }

    /**
     * method to higher ovens own temperature by 10 degrees
     * @effect the temperature of oven will be heated by 10 degrees
     */
    public void heatOwnTemperature() {
        temperature.heat(10F);
    }
}
