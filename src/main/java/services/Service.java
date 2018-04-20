package services;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TreeItem;

public interface Service {

    static TreeItem setCollorNode(TreeItem item) {
        item.setForeground(item.getDisplay().getSystemColor(SWT.COLOR_BLUE));

        return item;
    }
}
