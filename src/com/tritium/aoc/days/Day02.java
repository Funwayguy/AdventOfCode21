package com.tritium.aoc.days;

import com.tritium.aoc.Stopwatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

// Advent of Code 2021 - Day 2
public class Day02 {
    public static void partOne() {
        Stopwatch timer = new Stopwatch().start();
        // Init variables
        int[] pos = new int[2];
        int dist;
        char dir;
        
        // Begin input
        File fileIn = new File("resources/input_02.txt");
        try (FileReader fr = new FileReader(fileIn); BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                //if(parts.length < 2) continue; // Sanity check?
                dist = Integer.parseInt(parts[1]);
                dir = parts[0].charAt(0);
                switch (dir) {
                    case 'u':
                        pos[1] -= dist;
                        break;
                    case 'd':
                        pos[1] += dist;
                        break;
                    case 'f':
                        pos[0] += dist;
                }
            }
            System.out.println("Answer: " + (pos[0] * pos[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        timer.stop();
        System.out.println("Completed in " + timer);
    }
    public static void partTwo() {
        Stopwatch timer = new Stopwatch().start();
        // Init variables
        int[] pos = new int[3];
        int dist;
        char dir;
        
        // Begin input
        File fileIn = new File("resources/input_02.txt");
        try (FileReader fr = new FileReader(fileIn); BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                //if(parts.length < 2) continue; // Sanity check?
                dist = Integer.parseInt(parts[1]);
                dir = parts[0].charAt(0);
                // We really only need the first character to denote direction
                switch (dir) {
                    case 'u':
                        pos[2] -= dist;
                        break;
                    case 'd':
                        pos[2] += dist;
                        break;
                    case 'f':
                        pos[0] += dist;
                        pos[1] += dist * pos[2];
                }
            }
            System.out.println("Answer: " + (pos[0] * pos[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        timer.stop();
        System.out.println("Completed in " + timer);
    }
}
