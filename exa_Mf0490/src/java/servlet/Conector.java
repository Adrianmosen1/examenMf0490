
package servlet;


import controlador.EjecutaSQL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumno
 */
@WebServlet("/Conector")
public class Conector extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //getParameter recibe un parámetro
        String usuario=request.getParameter("usuario");
        String password=request.getParameter("contraseña");
        
        PrintWriter out=response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("El usuario es: "+usuario);
        out.println("<br>El password es: "+password);
        out.println("</br>");
        
        // Crear una tabla en taller que se llame seguridad
        //id_Seguridad PK
        //login varchar(10)
        //password varchar(10)
       ResultSet rs=EjecutaSQL.devuelveRS("Select * from seguridad "
               + "where usu='"+usuario+"' and pass='"+password+"';","root", "1234", 
                "jdbc:mysql://localhost:3306/bd_exa_mf0409");
       
        try {
            rs.next();
            if (rs.getRow()==1)
            
               out.println("Gracias por utilizar nuestros servicios");
            
            else
                out.println("Usuario o Password incorrecto");
            
           
             out.println("</body>");
             out.println("</html>");
            
        } catch (SQLException ex) {
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
