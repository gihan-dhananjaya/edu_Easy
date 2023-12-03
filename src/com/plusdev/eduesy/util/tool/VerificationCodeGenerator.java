package com.plusdev.eduesy.util.tool;

import java.util.Random;

public class VerificationCodeGenerator {
    private final String NUMBER = "0123456789";
    public String getCode(int length){
     StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(NUMBER.charAt(new Random().nextInt(NUMBER.length()))) ;
        }
        return sb.toString();
    }
}
