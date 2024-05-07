package com.example.backend4f;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    private String message;

    public void init() {
       
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("utente");
        String password = request.getParameter("pass");

        BufferedReader br = new BufferedReader(new FileReader("login.txt"));
		String pw;
		String us;
		int flag = 1;
		String riga = br.readLine();
        boolean cor = false;
		While(riga!= null){
			us= "";
			pw= "";
			System.out.println(riga);
			riga = br.readLine();
			flag = 0;
			for(int i = 0; i < riga.length(); i++){
				if(riga.charAt != ' '){
					if(flag== 0){
						us = us + riga.charAt(i);
					}else{
						pw = pw + riga.charAt(i);
					}
				}else{
					flag = 1;
				}
			}
			if(username.equals(us) && password.equals(pw)) {
                cor = true;
                String usa = username;
            }

		}
			if(cor= true){
				HttpSession session = request.getSession(true);
				session.setAttribute("username", usa);
				response.sendRedirect("/errore");
			}else{
				HttpSession session = request.getSession(true);
				session.setAttribute("messaggio", "Credenziali sbagliati");
				response.sendRedirect("/errore");
			}
		

 
    }
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
			   response.sendRedirect("/errore");
		}

    public void destroy() {

    }
}