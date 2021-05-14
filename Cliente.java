import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import com.google.gson.*;

class Cliente{

    public static void main(String args[]){
        char opcion=' ';
        String email, nombre, apellido_paterno, apellido_materno, fecha_nacimiento, telefono, genero;
        Scanner sc = new Scanner(System.in);
        do{
            
            System.out.println("MENÚ\n\ta. Alta usuario\n\tb. Consulta usuario\n\tc. Borra usuario\n\td. Salir");
            opcion = sc.next().charAt(0);
            if(opcion=='a'){
                //alta
                Usuario r = new Usuario();
                System.out.println("Ingrese email:\n");
                email = sc.nextLine();
                System.out.println("Ingrese nombre:\n");
                nombre = sc.nextLine();
                System.out.println("Ingrese apellido paterno:\n");
                apellido_paterno = sc.nextLine();
                System.out.println("Ingrese apellido materno:\n");
                apellido_materno = sc.nextLine();
                System.out.println("Ingrese fecha de nacimiento en formato dd/mm/yyyy:\n");
                fecha_nacimiento = sc.nextLine();
                System.out.println("Ingrese telefono:\n");
                telefono = sc.nextLine();
                System.out.println("Ingrese genero en formato 'M' o 'F':\n");
                genero = sc.nextLine();
                r.id_usuario = 0;
            	r.email = email;
            	r.nombre = nombre;
            	r.apellido_paterno = apellido_paterno;
            	r.apellido_materno = apellido_materno;
            	r.fecha_nacimiento = fecha_nacimiento;
            	r.telefono = telefono;
            	r.genero = genero;
                try{
                URL url = new URL("http://13.85.21.6:8080/Servicio/rest/ws/alta_usuario");
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                conexion.setDoOutput(true);
                // en este caso utilizamos el método POST de HTTP
                conexion.setRequestMethod("POST");
                // indica que la petición estará codificada como URL
                conexion.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                GsonBuilder builder = new GsonBuilder();
                builder.serializeNulls();
                Gson gson = builder.create();
                String cuerpo = gson.toJson(r);
                String parametros = "usuario="+URLEncoder.encode(cuerpo,"UTF-8");
                OutputStream os = conexion.getOutputStream();
                os.write(parametros.getBytes());
                os.flush();
                // se debe verificar si hubo error
                if (conexion.getResponseCode() == 200){
                    // no hubo error
                    BufferedReader br = new BufferedReader(new InputStreamReader((conexion.getInputStream())));
                    String respuesta;
                    // el método web regresa una string en formato JSON
                    while ((respuesta = br.readLine()) != null) System.out.println(respuesta);
                }else{
                    // hubo error
                    BufferedReader br = new BufferedReader(new InputStreamReader((conexion.getErrorStream())));
                    String respuesta;
                    // el método web regresa una instancia de la clase Error en formato JSON
                    while ((respuesta = br.readLine()) != null) System.out.println(respuesta);
                    // dispara una excepción para terminar el programa
                    throw new RuntimeException("Codigo de error HTTP: " + conexion.getResponseCode());
                }
                conexion.disconnect();
                }catch(Exception exception){

                }
            }else if(opcion=='b'){
                //consulta
                
                try{
                	int id_usuarioS;
                	System.out.println("Ingresa el id del usuario a consultar:\n");
                	id_usuarioS = sc.nextInt();
                    URL url = new URL("http://13.85.21.6:8080/Servicio/rest/ws/consulta_usuario");
                    HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                    conexion.setDoOutput(true);
                    // en este caso utilizamos el método POST de HTTP
                    conexion.setRequestMethod("POST");
                    // indica que la petición estará codificada como URL
                    conexion.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                    // el método web "consulta_usuario" recibe como parámetro el id de un usuario, en este caso el id es 10
                    String parametros = "id=" + URLEncoder.encode(Integer.toString(id_usuarioS),"UTF-8");
                    OutputStream os = conexion.getOutputStream();
                    os.write(parametros.getBytes());
                    os.flush();
                    // se debe verificar si hubo error
                    if (conexion.getResponseCode() == 200){
                        // no hubo error
                        BufferedReader br = new BufferedReader(new InputStreamReader((conexion.getInputStream())));
                        String respuesta;
                        // el método web regresa una string en formato JSON
                        while ((respuesta = br.readLine()) != null) System.out.println(respuesta);
                    }else{
                        // hubo error
                        BufferedReader br = new BufferedReader(new InputStreamReader((conexion.getErrorStream())));
                        String respuesta;
                        // el método web regresa una instancia de la clase Error en formato JSON
                        while ((respuesta = br.readLine()) != null) System.out.println(respuesta);
                        // dispara una excepción para terminar el programa
                        throw new RuntimeException("Codigo de error HTTP: " + conexion.getResponseCode());
                    }
                    conexion.disconnect();
                    }catch(Exception exception){
    
                    }
            }else if(opcion=='c'){
                //borrado
                try{
                	int id_usuarioD;
                	System.out.println("Ingresa el id del usuario a borrar:\n");
                	id_usuarioD = sc.nextInt();
                    URL url = new URL("http://13.85.21.6:8080/Servicio/rest/ws/borra_usuario");
                    HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                    conexion.setDoOutput(true);
                    // en este caso utilizamos el método POST de HTTP
                    conexion.setRequestMethod("POST");
                    // indica que la petición estará codificada como URL
                    conexion.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                    // el método web "consulta_usuario" recibe como parámetro el id de un usuario, en este caso el id es 10
                    String parametros = "id=" + URLEncoder.encode(Integer.toString(id_usuarioD),"UTF-8");
                    OutputStream os = conexion.getOutputStream();
                    os.write(parametros.getBytes());
                    os.flush();
                    // se debe verificar si hubo error
                    if (conexion.getResponseCode() == 200){
                        // no hubo error
                        BufferedReader br = new BufferedReader(new InputStreamReader((conexion.getInputStream())));
                        String respuesta;
                        // el método web regresa una string en formato JSON
                        while ((respuesta = br.readLine()) != null) System.out.println(respuesta);
                    }else{
                        // hubo error
                        BufferedReader br = new BufferedReader(new InputStreamReader((conexion.getErrorStream())));
                        String respuesta;
                        // el método web regresa una instancia de la clase Error en formato JSON
                        while ((respuesta = br.readLine()) != null) System.out.println(respuesta);
                        // dispara una excepción para terminar el programa
                        throw new RuntimeException("Codigo de error HTTP: " + conexion.getResponseCode());
                    }
                    conexion.disconnect();
                    }catch(Exception exception){
    
                    }
            }else if(opcion=='d'){
                System.out.println("Terminando el cliente");
            }else{
                System.out.println("Esa opción no se encuentra en el menú");
            }
            sc.close();
        }while(opcion!='d');
    }
}