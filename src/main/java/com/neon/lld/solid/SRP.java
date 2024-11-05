package com.neon.lld.solid;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class SRP {
    public static void main(String[] args) throws IOException {
        Journal journal = new Journal();
        journal.addEntry("sample entry 1");
        journal.addEntry("sample entry 2");
        journal.addEntry("sample entry 3");
        FilePersistence filePersistence = new FilePersistence();
        filePersistence.save("journals.txt", journal);
        System.out.println(filePersistence.load("journals.txt"));
    }
}

class Journal {
    List<String> entries = new ArrayList<>();
    // managing
    public void addEntry(String entry) {
        entries.add(entry);
    }

    public void removeEntry(int index) {
        entries.remove(index);
    }
}

class FilePersistence {
    // persisting
    public void save(String fileName, Journal journal) throws IOException {
        try(PrintStream printStream = new PrintStream(fileName)) {
            for(String entry : journal.entries) {
                printStream.println(entry);
            }
        }
    }

    public List<String> load(String fileName) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String entry;
            List<String> result = new ArrayList<>();
            while((entry = br.readLine()) != null) {
                result.add(entry);
            }
            return result;
        }
    }
}



