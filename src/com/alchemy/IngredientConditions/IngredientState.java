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
     * constructor to make a state based on a boolean
     * @param solid whether the state is solid
     */
    //@Raw
    //public IngredientState(boolean solid){
        //if(solid){
            //state = State.Powder;
        //}
        //else{
            //state = State.Liquid;
        //}
    //}

    /**
     * constructor to make a state
     * @param state whether the state is solid
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

        private final String state;
        State(String state) {
            this.state = state;
        }

        public String getStateName(){
            return this.state;
        }
        public boolean isSolid() {
            return this.equals(State.Powder);
        }
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

    public boolean isLiquid(){return state.isLiquid();}
    //TODO

    /**********************************************************
     * Mutators
     **********************************************************/

    /**
     * method to change the state to the other one (change from fluid to solid or the other way)
     * @effect state is changed.
     *///TODO
    void switchState(State switchstate) throws Exception {
        IngredientState.State[] states = IngredientState.State.values();
        for (IngredientState.State state : states) {
            if (state == switchstate) {
                this.state = switchstate;
                return;
            }
        }
        throw new Exception("Invalid state: " + switchstate);
    }
        //IngredientState.State state = IngredientState.State.Liquid;

        //if(state.isSolid()){
            //state = State.Liquid;
        //}
        //else{
            //state = State.Powder;
        //}
   //}
}




