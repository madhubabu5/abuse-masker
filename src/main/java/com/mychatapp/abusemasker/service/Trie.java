package com.mychatapp.abusemasker.service;

import java.util.HashMap;
import java.util.Map;

class TrieNode{
    Map<Character,TrieNode> children;
    boolean isEndOfWord;

    TrieNode(){
        this.children = new HashMap<>();
        this.isEndOfWord = false;
    }
}


public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                node = new TrieNode();
                current.children.put(ch, node);
            }
            current = node;
        }
        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.isEndOfWord;
    }

    public boolean startsWith(String sentence) {
        TrieNode current = root;
        for (int i = 0; i < sentence.length(); i++) {
            char ch = sentence.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node != null && node.isEndOfWord) {
                return true;
            }
            if (node == null) {
                return false;
            }
            current = node;
        }
        return true;
    }
}


