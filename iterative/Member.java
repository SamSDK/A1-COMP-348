package iterative;

public class Member {
    /**
     * string object that is either 1 or 0 or label of colony
     */
    private String value;
    /**
     * string object that labels colony
     */
    private String colony;
    /**
     * boolean value that tells program if it is labelled. I.e if it is a 1 or 0 or part of a colony
     */
    private boolean isLabelled;

    // constructors
    /**
     * default constructor
     */
    public Member(){
        value = "0";
        colony = "0";
        isLabelled = false;
    }
    /**
     * parametrized constructor
     * @param value
     */
    public Member(String value) {
        this.value = value;
        colony = null;
        isLabelled = false;
    }

    
    /** 
     * sets value of member
     * @param value String value
     */
    // setter functions
    public void setValue(String value) {
        this.value = value;
    }

    
    /** 
     * sets colony of member
     * @param colony String colony
     */
    public void setColony(String colony) {
        this.colony = colony;
    }

    
    /** 
     * sets label of member
     * @param val boolean value
     */
    public void setLabelled(boolean val) {
        this.isLabelled = val;
    }

    
    /** 
     * gets value
     * @return String value
     */
    // getter methods
    public String getValue() {
        return value;
    }

    
    /** 
     * gets colony
     * @return String colony
     */
    public String getColony() {
        return colony;
    }

    
    /** 
     * gets isLabelled value
     * @return boolean isLabelled
     */
    public boolean returnIsLabelled() {
        return isLabelled;
    }

    
    /** 
     * to string method
     * @return String toString gets value
     */
    // to String method
    public String toString() {
        return this.getValue();
    }
}
