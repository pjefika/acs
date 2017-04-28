/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author G0042204
 */
public enum EnumFrequency {

    DEFAULT("2.4GHz"),
    ALTER("5GHz");

    String valor;

    private EnumFrequency(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

}
