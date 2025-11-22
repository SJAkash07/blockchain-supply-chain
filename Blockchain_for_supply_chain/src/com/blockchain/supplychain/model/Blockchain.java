package com.blockchain.supplychain.model;

import com.blockchain.supplychain.exception.BlockchainValidationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Blockchain {

    private final List<Block> chain = new ArrayList<>();
    private int difficulty = 3;

    public Blockchain() {
        chain.add(createGenesisBlock());
    }

    private Block createGenesisBlock() {
        Block genesis = new Block(0, "0");
        genesis.mineBlock(difficulty);
        return genesis;
    }

    public synchronized Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public synchronized void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        chain.add(newBlock);
    }

    public synchronized boolean isChainValid() throws BlockchainValidationException {
        for (int i = 1; i < chain.size(); i++) {
            Block current = chain.get(i);
            Block previous = chain.get(i - 1);

            if (!current.getHash().equals(current.calculateHash())) {
                throw new BlockchainValidationException("Current hash is invalid at index " + i);
            }

            if (!current.getPreviousHash().equals(previous.getHash())) {
                throw new BlockchainValidationException("Previous hash mismatch at index " + i);
            }
        }
        return true;
    }

    public synchronized List<Block> getBlocks() {
        return Collections.unmodifiableList(chain);
    }
}
