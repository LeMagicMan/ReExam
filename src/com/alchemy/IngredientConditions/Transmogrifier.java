package com.alchemy.IngredientConditions;

import com.alchemy.IngredientContainer;
import com.alchemy.quantity.FluidUnit;
import com.alchemy.quantity.PowderUnit;

/**********************************************************
 * A class representing a transmogrifier, used to change the state of an ingredient
 * defensively programmed
 *
 * @author MiroVanHoef
 * @author BenDeMets
 * @author SimonVandeputte
 * @version 1.0
**********************************************************/
public class Transmogrifier extends Device {
    
    /**********************************************************
     * Constructors
     **********************************************************/
    
    /**
     * creates an empty Transmogrifier
     */
    public Transmogrifier(){}
    
    
    /**********************************************************
     * Methods
     **********************************************************/

    //TODO
    /**
     * method to start the reaction
     * @pre device must be in laboratory
     * @throws NotInLaboratoryException if the transmogrifier isn't in a Laboratory
     */
    @Override
    public void react() throws NotInLaboratoryException {
        if(!isInLaboratory()){
            throw new NotInLaboratoryException("transmogrifier not in a Laboratory");
        }
    }

    /**
     * method to start the reaction
     * @pre device must be in laboratory
     * @throws NotInLaboratoryException if the transmogrifier isn't in a Laboratory
     */
    public void react(IngredientState.State state) throws NotInLaboratoryException {
        if(!isInLaboratory()){
            throw new NotInLaboratoryException("transmogrifier not in a Laboratory");
        }
        try {
            ingredient.getState().switchState(state);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        switch(state) {
            case Powder:
                if(ingredient.getState().isSolid()) {
                    ingredient.getQuantity().convertToPowderUnit(PowderUnit.PINCH);
                }
                break;
            case Liquid:
                if(ingredient.getState().isLiquid()) {
                    ingredient.getQuantity().convertToFluidUnit(FluidUnit.DROP);
                }
                break;
        }
    }

    /**
     * method to add the contents of a container to the transmogrifier, destroying the container in the process
     * @pre device must be empty
     * @param container the container containing the ingredient that has to be added to the device
     * @throws DeviceFullException if there already is an ingredient in the transmogrifier
     */
    @Override
    public void addIngredient(IngredientContainer container) throws DeviceFullException{
        if (ingredient != null){
            throw new DeviceFullException("transmogrifier Full");
        }
        else{
            super.addIngredient(container);
        }

    }
}
