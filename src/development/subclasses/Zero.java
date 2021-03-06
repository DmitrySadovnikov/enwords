package development.subclasses;

import development.Main;

import java.io.*;
import java.util.*;

public class Zero {

//    public static void main(String[] args) throws IOException {
//
//        Zero x = new Zero();
//        x.tmp();
//
//    }
//
//    public void tmp() throws IOException {
//        File jpnWords = new File("/home/sadedv/Documents/repo/jpnWords");
//        List<String> l = fileToList(jpnWords);
//        List<String> l2 = new ArrayList<>();
//
//        int count = 1;
//        for (String s : l) {
//            l2.add(count + "\t" + s);
//            count++;
//        }
//
//
//        listToFile(l2, new File("/home/sadedv/Documents/repo/jpnWordsTmp"));
//    }

    public <T> Set<T> fileToSet(File file, int param) throws IOException {
        Set<T> result = new HashSet<>();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
            String line;
            while (fileReader.ready()) {
                line = fileReader.readLine();

                String[] arr = parseLineLight(line);

                result.add((T) arr[param]);

            }
        }
        return result;
    }

    public <T> Set<T> fileToSet(File file) throws IOException {
        Set<T> result = new HashSet<>();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
            String line;
            while (fileReader.ready()) {
                line = fileReader.readLine();
                result.add((T) line);

            }
        }
        return result;
    }


    public void listToFile(List<String> list, File file) throws IOException {
        try (PrintWriter printWriter = new PrintWriter(file)) {

            for (String sentence : list) {
                printWriter.println(sentence);
            }
        }
    }

    public String[] parseLineLight(String line) {
        String[] list = line.split(Main.separator);
        return list;
    }

    public List<String> parseLine(String line) throws IOException {
        List<String> result = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(line, Main.separator);
        while (tokenizer.hasMoreTokens()) {
            result.add(tokenizer.nextToken());
        }
        return result;
    }

    public List<String> fileToList(File file) throws IOException {
        List<String> result = new ArrayList<>();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(file.getAbsolutePath()))) {
            String line;
            while (fileReader.ready()) {
                line = fileReader.readLine();
                result.add(line);
            }
        }
        return result;
    }

    private List<String> mapToList(Map<String, String> map) {
        List<String> res = new ArrayList<>();

        for (Map.Entry<String, String> pair : map.entrySet()) {
            res.add(pair.getValue());
        }
        return res;
    }

    /**
     * delete bad words for english
     */
    public Set<String> conditions(String[] words) {
        Set<String> set = new HashSet<>();

//        for (String word : words) {
//            if (((word.length() > 1) || "i".equals(word)) && !word.startsWith("'")
//                    && (!"the".equals(word) && !"tom".equals(word) && !"mary".equals(word)
//                    && !word.startsWith("tom'") && !word.startsWith("mary'"))) {
//                set.add(word);
//            }
//        }


        for (String word : words) {
            if (((word.length() >= Main.minWordLength) || "i".equals(word)) && !word.startsWith("'")
                    && (!"tom".equals(word) && !"mary".equals(word) && !"tatoeba".equals(word) && !"th".equals(word)
                    && !word.startsWith("tom'") && !word.startsWith("mary'"))) {
                set.add(word);
            }
        }
        return set;
    }


    public String removePunctuationAndDigits(String word) {
//        return word.replaceAll("(?!')\\W+\\p{Digit}+", " ").toLowerCase();

            return word.replaceAll("[^'\\p{L}]+", Main.splitSpace).toLowerCase();

    }


}
