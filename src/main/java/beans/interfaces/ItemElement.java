package beans.interfaces;

import org.eclipse.swt.widgets.TreeItem;

public interface ItemElement {
    void accept(BeanItemVisitor visitor, TreeItem treeItem);
}
