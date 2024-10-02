package com.mychatapp.abusemasker.service;

import com.mychatapp.abusemasker.util.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AbuseMaskService implements CommandLineRunner {

    private Trie trie;

    @Autowired
    private FileService fileService;

    public AbuseMaskService() {
        trie = new Trie();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Chat Application started");

        String fileContent = fileService.getFileAsString("static/abusemessages.txt");

        String[] wordArray = fileContent.split("\\s+");
        for (String word : wordArray) {
            trie.insert(word);
        }
    }

    public boolean isAbusive(String word) {
        return trie.startsWith(word);
    }

    public String removeAbusiveContent(String sentence) {
        String[] wordsArray = sentence.split("\\s+");
        List<String> wordsList = Arrays.asList(wordsArray);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < wordsList.size(); i++) {
            if (isAbusive(wordsList.get(i))) {
                result.append("****");
            } else {
                result.append(wordsList.get(i));
            }
            if (i < wordsList.size() - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }
}

