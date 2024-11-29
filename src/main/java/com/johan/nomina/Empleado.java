package com.johan.nomina;

public class Empleado {
    private String nombre;
    private String cedula;
    private double salario;
    private String cargo;
    private String apellido;
    private String banco;
    private String NumeroCuenta;
    private double salarioNeto;
    private String Email;
    private String telefono;
    private String fax;
    private String codigoEmpleado;
    private String tipoCuenta;
    private String contactoPreferido;
    private static final double tramoExtento = 416220.0 / 12;
    private static final double tramo1 = 624329.0 / 12;
    private static final double tramo2 = 867123.0 / 12;

    public Empleado(String nombre, String cedula, double salario, String cargo, String apellido, String banco, double salarioNeto, String NumeroCuenta, String Email, String telefono, String fax, String codigoEmpleado, String tipoCuenta, String contactoPreferido) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.salario = salario;
        this.cargo = cargo;
        this.apellido = apellido;
        this.banco=banco;
        this.salarioNeto=getSalarioNeto();
        this.NumeroCuenta=NumeroCuenta;
        this.Email=Email;
        this.telefono=telefono;
        this.fax=fax;
        this.codigoEmpleado=codigoEmpleado;
        this.tipoCuenta=tipoCuenta;
        this.contactoPreferido=contactoPreferido;
    }
    public String getBanco(){
        return banco;
    }
    public void setBanco(String banco){
        this.banco = banco;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public String getTipoCuenta(){
        return tipoCuenta;
    }
    public String getContactoPreferido(){
        return contactoPreferido;
    }


    public String getNumeroCuenta(){
        return NumeroCuenta;
    }
    public String getEmail(){
        return Email;
    }
    public String getTelefono(){
        return telefono;
    }
    public String getFax(){
        return fax;
    }
    public String getCodigoEmpleado(){
        return codigoEmpleado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public double getSalarioNeto(){
        return salario - ( getISR() + getSFS() + getAFP());
    }

    public double getSFS() {
        return salario * 0.0304;
    }
    public double getAFP(){
        return salario * 0.287;
    }

    public double getISR(){
            if(salario<=tramoExtento){
                return 0;
            }else if(salario <= tramo1){
                return (salario - tramoExtento)*0.15;
            }else if(salario <= tramo2){
                return (31216.0/12) + (salario-tramo1)*0.20;
            }else
                return (79776.0/12)+(salario-tramo2)*0.25;
    }


}
