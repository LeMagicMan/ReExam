package com.alchemy.IngredientConditions;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**********************************************************
 * Class made to define the states of the ingredient
 * defensively programmed
 *
 * @author MiroVanHoef
 * @author BenDeMets
 * @author SimonVandeputte
 * @version 1.0
 **********************************************************/


public class IngredientState {

    /**********************************************************
     * Variables
     **********************************************************/

    /**
     * Variable referencing the state of the ingredient
     */
    private State state;


    /**********************************************************
     * Constructors
     **********************************************************/

    /**
     * constructor to make a state
     * @param state the state of the ingredient
     */
    @Raw
    public IngredientState(State state){
        this.state = state;
    }


    /**
     * enum to keep track of the state of the ingredient
     */
    public enum State {
        Powder("solid"),
        Liquid("liquid");

        /**
         * variable referencing a states name
         */
        private final String stateName;

        /**
         * constructor to m ake a new state
         * @param stateName the name of the state
         */
        State(String stateName) {
            this.stateName = stateName;
        }

        /**
         * a getter for the name of a state
         * @return the name of the state
         */
        @Basic
        public String getStateName(){
            return this.stateName;
        }

        /**
         * A check to see if a state is solid
         * @return true if state is solid false otherwise
         */
        public boolean isSolid() {
            return this.equals(State.Powder);
        }

        /**
         * AA check to see if a state is a liquid
         * @return true if state is a liquid false otherwise
         */
        public boolean isLiquid() {
            return this.equals(State.Liquid);
        }

    }

    /**********************************************************
     * Getters and Setters
     **********************************************************/

    /**
     * method to get the state of the ingredient
     * @return the state of the ingredient
     */
    @Basic
    public State getState(){return this.state;}

    /**
     * method to check if the ingredient is solid
     * @return whether the ingredient is solid
     */
    @Basic
    public boolean isSolid(){return state.isSolid();}

    /**
     * method to check if the ingredient is solid
     * @return whether the ingredient is solid
     */
    @Basic
    public boolean isLiquid(){return state.isLiquid();}


    /**********************************************************
     * Mutators
     **********************************************************/

    /**
     * method to change the state to the other one (change from fluid to solid or the other way)
     * @pre given switchstate must be a valid state
     * @param switchstate the new state of the ingredient
     * @effect state is changed.
     */
    void switchState(State switchstate) throws Exception {
        IngredientState.State[] states = IngredientState.State.values();
        for (IngredientState.State state : states) {
            if (state == switchstate) {
                this.state = switchstate;
                return;
            }
        }
    }
}




