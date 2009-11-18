/**
 * 
 */
package view.tabtables;

import javax.swing.table.DefaultTableModel;

/**
 * Table model for table in class D????????TableDrawer.
 *
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 *
 */
public class DescriptiveTableModel extends DefaultTableModel {

    /**
     * JDK 1.1 serialVersionUID.
     */
    private static final long serialVersionUID = 4869321333024726705L;

    /**
     * Constructor. It contains superclass constructor.
     * @param data array of data of the table.
     * @param columnNames array of column names of  the table.
     */
    public DescriptiveTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    /* (non-Javadoc)
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Class getColumnClass(final int columnIndex) {
        if (columnIndex == 0) {
            return String.class;
        } else {
            return Integer.class;
        }
    }
}
