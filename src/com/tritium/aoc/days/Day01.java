package com.tritium.aoc.days;

import com.tritium.aoc.Stopwatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

// Advent of Code 2021 - Day 1
public class Day01 {
    public static void partOne() {
        // Init variables
        int lastNum = -1;
        int count = 0;
        int num;
        
        // Begin input
        File fileIn = new File("resources/input_01.txt");
        try (FileReader fr = new FileReader(fileIn); BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                num = Integer.parseInt(line);
                // Skip if we haven't previously set lastNum
                if (lastNum >= 0 && num > lastNum) count++;
                lastNum = num;
            }
            System.out.println("Answer: " + count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void partTwo() {
        Stopwatch timer = new Stopwatch().start();
        // Init variables
        int lastSum = -1;
        int[] window = new int[3];
        int idx = 0;
        int count = 0;
        int sum;
        
        // Begin input
        File fileIn = new File("resources/input_01.txt");
        try (FileReader fr = new FileReader(fileIn); BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                // Wrap idx using modulo to 'slide' the window
                window[idx++ % 3] = Integer.parseInt(line);
                sum = window[0] + window[1] + window[2];
                // Skip sum check until first window is full
                if (idx > 3 && sum > lastSum) count++;
                lastSum = sum;
            }
            System.out.println("Answer: " + count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        timer.stop();
        System.out.println("Completed in " + timer);
    }
}
