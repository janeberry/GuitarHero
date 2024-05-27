package GuitarHero;

/**
 * GuitarHero
 * models a vibrating guitar string of a given frequency.
 * The GuitarString object will need to keep track of a ring buffer.
 *
 * @author Gaeun Kim
 * @version 04 March 2024
 * CS122, 10AM
 */

import java.util.*;
public class GuitarString {
    private Queue <Double> ringbuffer;
    private int capacityN;

    /**
     * Constructs a guitar string of the given frequency
     *
     * It creates a ring buffer of
     * the desired capacity N (sampling rate divided by frequency, rounded to the
     * nearest integer), and initializes it to represent a guitar string at rest by
     * enqueueing N zeros.
     * If the frequency is less than or equal to 0 or if
     * the resulting size of the ring buffer would be less than 2, your method
     * should throw an IllegalArgumentException
     *
     *
     * @param frequency, double value bigger than 0
     */
    public GuitarString (double frequency){
        ringbuffer = new LinkedList<Double>();
        capacityN = Math.round((float)(StdAudio.SAMPLE_RATE / frequency));

        //throw IllegalArgumentException
        if (frequency <= 0 || capacityN < 2) throw new IllegalArgumentException();

        for (int i=0; i< capacityN; i++){
            ringbuffer.add(0.0);
        }
    }


    /**
     * Constructs a guitar string and initializes the contents of the ring buffer to
     * the values in the array. If the array has fewer than two elements, your
     * constructor should throw an IllegalArgumentException.
     *
     *
     * @param init, double array element
     */
    public GuitarString (double[] init){
        ringbuffer = new LinkedList<Double>();
        capacityN= init.length;

        //If the array has fewer than two elements
        //your constructor should throw IllegalArgumentException

        if (capacityN < 2) throw new IllegalArgumentException();

        //initialize the ringbuffer with init
        for (int i=0; i<capacityN; i++){
            ringbuffer.add(init[i]);
        }
    }

    /**
     *pluck()
     *
     *replace the N elements in the ring buffer with N
     *random values between -0.5 inclusive and +0.5 exclusive
     *
     */

    public void pluck(){
        Random rand = new Random();
        ringbuffer.clear();

        for (int i=0; i<capacityN; i++){
            ringbuffer.add(rand.nextDouble(-0.5, 0.5));
        }
    }

    /**
     *tic()
     *
     * apply the Karplus-Strong update once (performing one step).
     * It should delete the sample at the front of the ring buffer and add to
     * the end of the ring buffer the average of the first two samples, multiplied
     * by the energy decay factor (0.996).
     */
    public void tic(){
        double k = ringbuffer.remove();
        double j = ringbuffer.peek();
        ringbuffer.add(0.996 * 0.5 * (k + j));

    }

    /**
     * sample()
     *
     * @return the value at the front of the ring buffer
     */
    public double sample(){
        return ringbuffer.peek();
    }

}
