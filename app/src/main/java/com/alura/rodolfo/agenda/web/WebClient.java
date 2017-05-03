package com.alura.rodolfo.agenda.web;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.util.Scanner;

/**
 * Created by Rodolfo on 17/04/17.
 */

public class WebClient {
    public String post(String json){

        try {
            URL url = new URL("https://www.caelum.com.br/mobile");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setDoOutput(true);

            PrintStream output = new PrintStream(connection.getOutputStream());
            output.println(json);

            connection.connect();

            Scanner scanner = new Scanner(connection.getInputStream());

            String resposta = scanner.next();
            return resposta;
        } catch (MalformedInputException e) {
            e.printStackTrace();;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
