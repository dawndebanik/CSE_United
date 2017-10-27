package com.cseunited.alumni.cseunited;

import android.util.Log;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by suyash on 10/27/2017
 * Written by Debanik.
 */

class HashMaker {

    private MessageDigest mDigest;
    /**
     * Initializes the Hashing Algorithm.
     * Appropriate string containing the name of the algorithm to be applied is passed (e.g: "SHA-256").
     * NoSuchAlgorithmException is thrown if given algorithm doesn't exist.
     **/
    HashMaker(String algorithm){
        try {
            this.mDigest = MessageDigest.getInstance(algorithm);
        }
        catch (NoSuchAlgorithmException e){
            Log.d("HASH ERROR: ",e.toString());
        }
    }

    /**
     * Returns the hash of the message string in a modified way (a sequence of positive integers up to 255 separated by a dot '.')
     **/
    String getHash(String message){
        mDigest.update(message.getBytes(StandardCharsets.UTF_8));
        byte[] byteHash = mDigest.digest();
        String hash="";
        for(byte i : byteHash)
            hash = hash+(i+128)+".";
        return hash;
    }
}
