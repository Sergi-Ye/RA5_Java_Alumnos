/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import java.util.Scanner;
import java.io.*;

public class RegistroAlumnos {

    static Scanner sc = new Scanner(System.in);
    static String salto = System.getProperty("line.separator");
    static String rutaProyecto = System.getProperty("user.dir");
    static String separator = File.separator;
    static String rutaCarpeta = rutaProyecto + separator + "archivos";

    static File carpeta = new File(rutaCarpeta); 
    static File registro = new File(rutaCarpeta + separator + "registro.txt");
        
    
    public static void main(String args[]) throws IOException{
        try{
            if(!(carpeta.exists())){
                carpeta.mkdir();
            }

            if(!(registro.exists())){
                registro.createNewFile();
            }
        }catch(Exception e) {
            System.err.println(e + "\n");
            sc.nextLine(); 
        }
     
        int opc;

        do{
            opc = menu();
            try {
                switch(opc){
                    case 1: agregarAlumno(); break;
                    case 2: mostrarAlumnos(); break;
                    case 3: eliminarAlumno(); break;
                    case 4: buscarDNI(); break;
                    case 5: System.out.println("Adios!"); break;
                    default: System.out.println("Fuera de rango");
                }
            }catch(Exception e) {
                System.err.println(e + "\n");
                sc.nextLine(); 
            } 
        }while(opc != 5);
    }

    public static void agregarAlumno() throws IOException{
        
        FileWriter fw = new FileWriter(registro, true);
        BufferedWriter bw = new BufferedWriter(fw);
        
        
            System.out.println("Introduzca el nombre:");
            String nombre = sc.nextLine();
            System.out.println("Introduzca el apellido:");
            String apellido = sc.nextLine();
            System.out.println("Introduzca la edad:");
            int edad = sc.nextInt(); 
            sc.nextLine();
            System.out.println("Introduzca el curso:");
            String curso = sc.nextLine();
            System.out.println("Introduzca el dni:");
            String dni = sc.nextLine();
        
            Alumno alumno = new Alumno(nombre, apellido, edad, curso, dni);
        
            bw.write(nombre + ";" + apellido + ";" + edad + ";" + curso + ";" + dni);
            bw.write(salto);
            bw.flush();
            bw.close();
        }
    public static void mostrarAlumnos() throws IOException{
        FileReader fr = new FileReader(registro);
        BufferedReader br = new BufferedReader(fr);
        
            String linea;
            
            while((linea = br.readLine())!= null){
                System.out.println(linea);
            }
            br.close();

    }
    
    public static void eliminarAlumno() throws IOException {
        String linea;
        String temporal = "";
        boolean encontrado = false;

        System.out.println("Lista de alumnos:");
        BufferedReader br = new BufferedReader(new FileReader(registro));
        while ((linea = br.readLine()) != null) {
            System.out.println(linea);
        }
        br.close();

        System.out.println("Escriba el DNI del alumno a eliminar:");
        String dni = sc.nextLine();

        br = new BufferedReader(new FileReader(registro));
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(";");
            String dniArchivo = partes[4];

            if (!dniArchivo.equals(dni)) {
                temporal += linea + salto;
            } else {
                encontrado = true;
            }
        }
        br.close();

            if (encontrado) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(registro));
                bw.write(temporal);
                bw.close();
                System.out.println("Alumno eliminado correctamente");
            } else {
                System.out.println("No se ha encontrado este DNI");
            }
        }
   
    public static void buscarDNI() throws IOException {    
        String linea;
        boolean encontrado = false;

        System.out.println("Escriba el DNI del alumno:");
        String dni = sc.nextLine();

        BufferedReader br = new BufferedReader(new FileReader(registro));
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(";");
            String dniArchivo = partes[4];

            if (dniArchivo.equals(dni)) {
                System.out.println(linea);
                encontrado = true;
            }
        }
        br.close();

        if (!encontrado) {
            System.out.println("No se ha encontrado este DNI");
        }
    }

    public static int menu(){
        System.out.println("1- Agregar un nuevo alumno al registro");
        System.out.println("2- Mostrar la lista de alumnos registrados");
        System.out.println("3- Eliminar un alumno del registro. ");
        System.out.println("4- Buscar un alumno por su DNI.");
        System.out.println("5- Salir del programa.");

        System.out.println("Introduce una opcion:");
        int opc = sc.nextInt(); sc.nextLine();
        return opc;
    }



}


