package com.johan.nomina;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GuardarEmpleado {
    private List<Empleado> listaEmpleados;
    private String archivoJson;
    private Gson gson = new Gson();

    public GuardarEmpleado(String archivoJson) {
        this.archivoJson = archivoJson;
        this.listaEmpleados = cargarEmpleados();
    }

    private List<Empleado> cargarEmpleados() {
        try (FileReader reader = new FileReader(archivoJson)) {
            Type empleadoListType = new TypeToken<List<Empleado>>() {}.getType();
            List<Empleado> empleados = gson.fromJson(reader, empleadoListType);
            return empleados != null ? empleados : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("El archivo no existe o está vacío, se creará uno nuevo.");
            return new ArrayList<>();
        }
    }

    private void guardarEmpleados() {
        try (FileWriter writer = new FileWriter(archivoJson)) {
            gson.toJson(listaEmpleados, writer);
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir el archivo JSON", e);
        }
    }

    public void agregarEmpleado(Empleado empleado) {
        listaEmpleados.add(empleado);
        guardarEmpleados();
        if ("Banco BHD".equals(empleado.getBanco())) {
            generarArchivoTextoBHD(empleado);
        }else if("BanReservas".equals(empleado.getBanco())){
            generarArchivoTextoBanReserva(empleado);
        }else if("Banco Popular".equals(empleado.getBanco())){
            generarArchivoTextoBancoPopular(empleado);
        }
    }

    private void generarArchivoTextoBHD(Empleado empleado) {

        String archivoTxtBHD="data/Banco_BHD.txt";
        long salarioNetoSinDecimales = Math.round(empleado.getSalarioNeto() * 100);

        String linea = String.join(";",
                empleado.getNumeroCuenta(),
                empleado.getNombre() + " " + empleado.getApellido(),
                empleado.getCodigoEmpleado(),
                String.valueOf(salarioNetoSinDecimales),
                "Pago Nomina"
        );

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTxtBHD, true))) {
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo .txt: " + e.getMessage());
        }
    }

    private void generarArchivoTextoBanReserva(Empleado empleado) {

        String archivoTxtBHD="data/BanReservas.txt";

        String linea = String.join(",",
                "CC",
                "DOP",
                "234556",
                empleado.getTipoCuenta(),
                "DOP",
                empleado.getNumeroCuenta(),
                String.format("%.2f",empleado.getSalarioNeto()),
                "NOMINA"
        );

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTxtBHD, true))) {
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo .txt: " + e.getMessage());
        }
    }
    private void generarArchivoTextoBancoPopular(Empleado empleado) {

        String archivoTxtBancoPopular="data/Banco_Popular.txt";
        long salarioNetoSinDecimales = Math.round(empleado.getSalarioNeto() * 100);
        int contactoPreferidoNum=0;
        String contactoUsado="";
        String contactoPreferido= empleado.getContactoPreferido();

        if(contactoPreferido.equals("Email")){
            contactoPreferidoNum=1;
        } else if (contactoPreferido.equals("Fax")) {
            contactoPreferidoNum=2;
        } else if (contactoPreferido.equals("Telefono")) {
             contactoPreferidoNum=3;
        }

        if(contactoPreferidoNum==1){
            contactoUsado="Email";
        }else if(contactoPreferidoNum==2){
            contactoUsado="Fax";
        }

        String linea = String.join("/",
                "N",
                "3456182",
                "234556",
                "10006",
                empleado.getNumeroCuenta(),
                empleado.getTipoCuenta(),
                "214",
                "10101070",
                "8",
                String.valueOf(salarioNetoSinDecimales),
                "OT",
                empleado.getCedula(),
                empleado.getNombre(),
                empleado.getCodigoEmpleado(),
                String.valueOf(contactoPreferidoNum),
                contactoUsado
        );

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTxtBancoPopular, true))) {
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo .txt: " + e.getMessage());
        }
    }
}
