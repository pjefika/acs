/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.wifiInfo;

/**
 *
 * @author G0042204
 */
public class WifiEntity {

    private String pivotColumn;

    private WifiInfoHolder[] holder;

    public WifiEntity() {
    }

    public String getPivotColumn() {
        return pivotColumn;
    }

    public void setPivotColumn(String pivotColumn) {
        this.pivotColumn = pivotColumn;
    }

    public WifiInfoHolder[] getHolder() {
        return holder;
    }

    public void setHolder(WifiInfoHolder[] holder) {
        this.holder = holder;
    }

}
