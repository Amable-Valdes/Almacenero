package igu;

import javax.swing.table.*;

public class ModeloEditableCarrito extends DefaultTableModel {
	private static final long serialVersionUID = 1L;

	public ModeloEditableCarrito(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		if (column == 1)
			return true;
		else
			return false;
	}
}