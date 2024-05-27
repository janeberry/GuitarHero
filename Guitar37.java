package GuitarHero;

// skeleton version of the class

/**
 * Guitar37
 * track of a musical instrument with multiple strings
 *
 * @author Gaeun Kim
 * @version 04 March 2024
 * CS122, 10AM
 *
 */

public class Guitar37 implements Guitar {
    public static final String KEYBOARD =
            "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
    private GuitarString[] pianoString; //GuitarString array
    private int length = KEYBOARD.length(); //37 instruments
    private int time = 0;

    /**
     * Constructor
     *
     * There are 37 instruments (KEYBOARD), constructor with initializing by creating array strings.
     */
    public Guitar37(){
        //create array box with 37 values (there are 37 keys)
        pianoString = new GuitarString[length];

        //initialize with given frequency for each array
        for (int i=0; i<37; i++){
            double frequency = 440 * Math.pow(2.0, (double) (i - 24)/12);
            pianoString[i] = new GuitarString(frequency);
        }
    }

    /**
     * playNote(int pitch)
     *
     * @param pitch, specific integer value to play Note
     */
    public void playNote(int pitch){
        int index = pitch + 24;
        pianoString[index].pluck();
    }

    /**
     * hasString(char key)
     *
     * verifying a particular character has a corresponding string for this guitar
     *
     * @param key, a char from the KEYBOARD
     * @return true if the key is in the KEYBOARD
     * @return false if the key isn't in the KEYBOARD
     */
    public boolean hasString(char key){
        return KEYBOARD.indexOf(key) != -1;
    }

    /**
     * pluck(char key)
     *
     * pluck with the given key
     * throw an IllegalArgumentException if the key is not one of the 37 keys it is designed to play
     *
     * @param key, a designed key to play
     */
    public void pluck(char key){
        if(!hasString(key)){
            throw new IllegalArgumentException();
        }
        pianoString[KEYBOARD.indexOf(key)].pluck();
    }

    /**
     * sample()
     *
     * @return the sum of all 37 samples
     */
    public double sample(){
        double sum = 0.0;
        for (int i=0; i<length; i++){
            sum += pianoString[i].sample();
        }
        return sum;
    }

    /**
     * tic()
     *
     * apply the Karplus-Strong update for each array
     */
    public void tic(){
        for (int i=0; i<length; i++){
            pianoString[i].tic();
        }
        time++;
    }


    /**
     * time()
     *
     * @return the number of times tic has been used
     */
    //optional
    //should be implemented in Guitar37 class
    public int time(){
        if (time == 0){
            return -1;
        }
        return time;
    }

}
