package beans.interfaces;

import beans.Attribute;
import beans.Attributes;
import beans.Item;
import beans.Items;
import org.eclipse.swt.widgets.TreeItem;

public interface BeanItemVisitor {
    void visit(Item firstItem, Object secondItem, TreeItem treeItem);

    void visit(Items firstItems, Object secondItems, TreeItem treeItem);

    void visit(Attributes firstAttributes, Object secondAttributes, TreeItem treeItem);

    void visit(Attribute firstAttribute, Object secondAttribute, TreeItem treeItem);
}
