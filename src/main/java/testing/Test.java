package testing;

import commands.rollcube.CubeParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String inputLine;
        while(!(inputLine = reader.readLine()).equals("")) {
            System.out.println(new CubeParser(inputLine).roll());
        }
    }
}