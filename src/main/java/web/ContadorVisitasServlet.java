package web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ContadorVisitasServlet")
public class ContadorVisitasServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //Declaramos la variable contador
        int contador = 0;
        
        //Revisamos si existe la cookie contadorVisitas
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie c: cookies){
                if(c.getName().equals("contadorVisitas")){
                    contador = Integer.parseInt(c.getValue());
                }
            }
        }
        //Incrementar el contador en uno
        contador++;
        //Agregamos la respuesta al navegador
        Cookie c = new Cookie("contadorVisitas", Integer.toString(contador));
        //La cookie se almacenara en el cliente por 1 hora (3600 seg)
        c.setMaxAge(3600);
        response.addCookie(c);
        
        //Mandamos el mesaje al navegador
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("Contador de visitas de cada cliente: " + contador);
    }
}
