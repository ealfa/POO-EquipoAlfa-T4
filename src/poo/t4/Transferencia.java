/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo.t4;

/**
 * Esta clase representa una transferencia, transacción o movimiento
 * del monedero electrónico
* @author osilru
 */
public class Transferencia {
    
    //Atributos
    /**
     * Todas son constantes porque no debe haber alguna transferencia si alguno de estos datos
     * MONTO: es la cantidad de dinero que fue involucrada en esta transacción
     * ID_CLIENTE: es la ID del cliente que participó en esta transacción.
     * ID_MONEDERO: es el ID del monedero involucrado en esta transacción o transferencia
     * ID_TIENDA: el id de la tienda donde se realizó la transacción
     * ID_CAJA: la caja donde se realizó la transacción o transferencia
     * ES_CARGO: Si es "true" implica que el movimiento es un cargo al monedero
     *  (se le restó saldo al monedero); si es "false" que es un abono (el saldo del monedero
     *  aumentó)
     * FECHA: Cadena de caracteres que representa la fecha de la transferencia o transacción
     */
    private double MONTO;
    private int ID_CLIENTE;
    private int ID_MONEDERO;
    private int ID_TIENDA;
    private int ID_CAJA;
    private boolean ES_CARGO;
    private String FECHA;
   
    //Métodos
    public Transferencia() {
        
    } 
    /**
     * Constructor de una trasnferencia
     * @param monto double que es la cantidad de dinero que fue involucrada en esta transacción
     * @param idCliente int que es la ID del cliente que participó en esta transacción
     * @param idMonedero int que es es el ID del monedero involucrado en esta transacción o transferencia
     * @param idTienda int que es el id de la tienda donde se realizó la transacción
     * @param idCaja int que es la caja donde se realizó la transacción o transferencia
     * @param esCargo boolean que si es "true" implica que el movimiento es un cargo al monedero
     *  (se le restó saldo al monedero); si es "false" que es un abono (el saldo del monedero
     *  aumentó)
     * @param fecha String que representa la fecha de la transferencia
     */
    public Transferencia(double monto, int idCliente, int idMonedero, int idTienda, 
                int idCaja, boolean esCargo, String fecha){
        this.MONTO = monto;
        this.ID_CLIENTE = idCliente;
        this.ID_MONEDERO = idMonedero;
        this.ID_TIENDA = idTienda;
        this.ID_CAJA = idCaja;
        this.ES_CARGO = esCargo;
        this.FECHA = fecha;
    }
    
    /**
     * Metodo para obtener el monto de la transferencia
     * @return double con el Monto de la transacción
     */
    public double getMonto(){
        return this.MONTO;
    }
    
    /**
     * Método que regresa el iddel Cliente
     * @return int con el ID del cliente
     */
    public int getIDCliente(){
        return this.ID_CLIENTE;
    }
    
    /**
     * Metodo para obtener el id del monedero
     * @return int con el ID del monedero que participó en la transferencia
     */
    public int getIDMonedero(){
        return this.ID_MONEDERO;
    }
    
    /**
     * Metodo para obtener el id de la tienda
     * @return int con el ID de la tienda donde se realizó la transferencia
     */
    public int getIDTienda(){
        return this.ID_TIENDA;
    }
    
    /**
     * Metodo para obtener el id de la caja donde se hizo la transfrencia
     * @return int con el ID de la caja donde se realizó la transferencia
     */
    public int getIDCaja(){
        return this.ID_CAJA;
    }
    
    /**
     * Método para saber si la transferencia fue un cargo o un abono.
     * @return boolean que representa que es un "cargo" si es TRUE, y "abono" su es FALSE
     */
    public boolean getEsCargo(){
        return this.ES_CARGO;
    }
    
    /**
     * Método para conocer la feha de la transferencia
     * @return Cadena de caracteres con la fecha de la transferencia
     */
    public String getFecha(){
        return this.FECHA;
    }
    
    /**
     * Método que regresa una descripción de la transacción realizada
     * @return una cadena de caracteres con la información de la transacción
     */
    public String descripcionTransferencia(){
        String auxiliar = "FECHA: " + this.getFecha() + " TIENDA: " + 
                this.getIDTienda() + " CAJA: " + this.getIDCaja() + 
                "\n ---CLIENTE " + this.getIDCliente() + " REALIZÓ UN ";    
        //Verifica si el movimiento fue un cargo o un abono
        if (this.ES_CARGO) {
            auxiliar = auxiliar + "CARGO DE $";
        } else {
            auxiliar = auxiliar + "ABONO DE $";
        }                
        auxiliar = auxiliar + this.getMonto() + " AL MONEDERO " + this.getIDMonedero();                      
        return auxiliar;
    }
}