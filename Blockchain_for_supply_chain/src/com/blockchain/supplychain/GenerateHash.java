package com.blockchain.supplychain;

import com.blockchain.supplychain.util.HashUtil;

public class GenerateHash {
    public static void main(String[] args) {
        String hash = HashUtil.applySha256("123456");
        System.out.println(hash);
    }
}


