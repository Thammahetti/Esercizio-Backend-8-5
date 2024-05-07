package com.example.backend4f;

import java.io.*;
import java.util.ArrayList;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "voti", value = "/voti")
public class VotiServlet extends HttpServlet {
    private String message;

    public void init() {
       
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String usernameSalvato = (String) session.getAttribute("username");
        String filePath = "voti.txt";  
        String username = usernameSalvato;
        int[] grades = getGradesByUsername(username, filePath);
        if (grades.length > 0) {
            System.out.println("Grades for " + username + ":");
            for (int grade : grades) {
                System.out.print(grade + " ");
            }
        } else {
            System.out.println("No grades found for " + username);
        }
    }
    
     public static int[] getGradesByUsername(String username, String filePath) {
        File file = new File(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(username + " ")) {
                    return parseGrades(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        return new int[]{};  // Return empty array if username is not found
    }

    private static int[] parseGrades(String data) {
        // Expect data in format "username [grade1, grade2, grade3, ...]"
        int startIndex = data.indexOf('[') + 1;
        int endIndex = data.indexOf(']');
        if (startIndex < 0 || endIndex < 0) return new int[]{};

        String gradesString = data.substring(startIndex, endIndex).trim();
        if (gradesString.isEmpty()) return new int[]{};

        String[] gradesArray = gradesString.split(",\\s*");
        int[] grades = new int[gradesArray.length];
        for (int i = 0; i < gradesArray.length; i++) {
            try {
                grades[i] = Integer.parseInt(gradesArray[i]);
            } catch (NumberFormatException e) {
                System.out.println("Failed to parse grade: " + gradesArray[i]);
                return new int[]{};  // Return empty array in case of parsing error
            }
        }
        return grades;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
    public void destroy() {
    }
}