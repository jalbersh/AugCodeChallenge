package com.dish.ofm.service.codeChallenge083017.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CipherService {
    private static Logger LOGGER = LoggerFactory.getLogger(CipherService.class);
    private String key;
    private static final int SPACE = 32;
    private static final int A = 65;
    private static final int Z = 90;
    private static final int NUM_ALPHA = 26;
    private static final int aValue = Character.codePointAt("A", 0);

    public CipherService(String key) {
        //if (key != null && !key.matches("/ ^[a - z]+$ /) ")
        this.key = key;
    }

    public String encode(String plainText){
            LOGGER.info("in encode with "+plainText+";aValue="+aValue);
            String output = "";
            String [] letters = plainText.split("(?=[A-Z ])");
            LOGGER.info("there are "+letters.length+" letters in "+plainText);
            for (int i=0;i<letters.length;i++) {
                String letter = letters[i];
                LOGGER.info("letter["+i+"]="+letter);
                char newLetter = Character.forDigit(0,10);
                if (!letter.equals(" ")) {
                    int code = letter.codePointAt(0);
                    LOGGER.info("code=" + code);
                    // shift is code for key minus code for a
                    char keyLetter = this.key.toCharArray()[0];
                    LOGGER.info("keyLetter=" + keyLetter);
                    int keyCode = (int) keyLetter;
                    LOGGER.info("keyCode=" + keyCode);
                    int shift = keyCode - aValue;
                    LOGGER.info("encode shift=" + shift);
                    int newCode = code + shift;
                    if (newCode > Z) {
                        newCode -= NUM_ALPHA;
                    }
                    LOGGER.info("encode newCode=" + newCode);
                    newLetter = (char) newCode;
                } else {
                    newLetter = (char) SPACE; // space
                }
                LOGGER.info("encode newLetter="+newLetter);
                output += newLetter;
            }
            LOGGER.info("encode output="+output);
            return output;
        }

     public String decode(String cipherText) {
            LOGGER.info("in decode with "+cipherText+";aValue="+aValue);
            String output = "";
            String [] letters = cipherText.split("(?=[A-Z ])");
            for (int i=0;i<letters.length;i++) {
                String letter = letters[i];
                LOGGER.info("letter["+i+"]="+letter);
                char newLetter = Character.forDigit(0,10);
                if (!letter.equals(" ")) {
                    int code = letter.codePointAt(0);
                    // shift is code for key minus code for a
                    char keyLetter = this.key.toCharArray()[0];
                    int keyCode = (int) keyLetter;
                    int shift = aValue - keyCode;
                    LOGGER.info("decode shift=" + shift);
                    int newCode = code + shift;
                    if (newCode < A) {
                        newCode += NUM_ALPHA;
                    }
                    LOGGER.info("decode newCode=" + newCode);
                    newLetter = (char) newCode;
                } else {
                    newLetter = (char) SPACE; // space
                }
                LOGGER.info("decode newLetter="+newLetter);
                output += newLetter;
            }
            LOGGER.info("decode output="+output);
            return output;
        }
}
