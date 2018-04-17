package beans.interfaces;

import beans.Attribute;
import beans.Attributes;
import beans.Item;
import beans.Items;
import org.eclipse.swt.widgets.TreeItem;

public interface BeanItemVisitor {
    void visit(Item item, TreeItem treeItem);

    void visit(Items items, TreeItem treeItem);

    void visit(Attributes attributes, TreeItem treeItem);

    void visit(Attribute attribute, TreeItem treeItem);
}
