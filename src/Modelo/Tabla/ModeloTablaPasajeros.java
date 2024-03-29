package Modelo.Tabla;

import Modelo.JDBC.BaseDatos;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloTablaPasajeros extends AbstractTableModel {

    private BaseDatos bd;
    ArrayList datos = new ArrayList();
    String[] nombreColumnas = new String[]{"ID Pasajero", "dni", "Nombre", "Apellido 1", "Apellido 2", "Edad", "Telefono", "ID Vuelo"};
    Class[] type = new Class[]{java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class};

    public ModeloTablaPasajeros(String dato) {
        bd = new BaseDatos();
        Object[][] pica = new String[bd.consultaTablaPasajero(dato).length][8];
        pica = bd.consultaTablaPasajero(dato);
        for (int i = 0; i < pica.length; i++) {
            datos.add(new String[]{pica[i][0].toString(), pica[i][1].toString(), pica[i][2].toString(), pica[i][3].toString(), pica[i][4].toString(), pica[i][5].toString(), pica[i][6].toString(), pica[i][7].toString()});
        }
    }

    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return nombreColumnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[] elemento = (Object[]) datos.get(rowIndex);
        return elemento[columnIndex];
    }

    public void setValueAt(Object elemento, int row, int col) {
        Object[] fila = (Object[]) datos.get(row);
        fila[col] = elemento;
        fireTableCellUpdated(row, col);
    }

    public String getColumnName(int col) {
        return nombreColumnas[col];
    }

    public Class getColumnClass(int col) {
        return type[col];
    }

    public void anadirFila(Object[] fila) {
        datos.add(fila);
        fireTableDataChanged();
    }

    public void borrarFila(int fila) {
        datos.remove(fila);
        fireTableDataChanged();
    }
}
