package services;

import beans.Attribute;
import beans.Attributes;
import beans.Documentation;
import beans.Header;
import beans.Item;
import beans.Items;
import beans.Model;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TreeItem;

public interface Service {

    static TreeItem createTreeItem(@NotNull Object item, @Nullable Object comparingItem, @NotNull TreeItem treeItem, @NotNull String params[]) {
        TreeItem subTreeItem = new TreeItem(treeItem, SWT.NONE);
        subTreeItem.setText(params);

        if (comparingItem != null) {
            if (!comparingObjects(item, comparingItem)) {
                subTreeItem.setForeground(subTreeItem.getDisplay().getSystemColor(SWT.COLOR_BLUE));
                subTreeItem.setExpanded(true);
            }
        }

        return subTreeItem;
    }

    static boolean comparingObjects(Object firstObject, Object secondObject) {
        if (firstObject instanceof String && secondObject instanceof String) {
            String firstString = (String) firstObject;
            String secondString = (String) secondObject;

            return firstString.equals(secondString);

        } else if (firstObject instanceof Item && secondObject instanceof Item) {
            Item firstItem = (Item) firstObject;
            Item secondItem = (Item) secondObject;

            return firstItem.equals(secondItem);

        } else if (firstObject instanceof Attributes && secondObject instanceof Attributes) {
            Attributes firstAttributes = (Attributes) firstObject;
            Attributes secondAttributes = (Attributes) secondObject;

            return firstAttributes.equals(secondAttributes);

        } else if (firstObject instanceof Attribute && secondObject instanceof Attribute) {
            Attribute firstAttribute = (Attribute) firstObject;
            Attribute secondAttibute = (Attribute) secondObject;

            return firstAttribute.equals(secondAttibute);
        } else if (firstObject instanceof Items && secondObject instanceof Items) {
            Items firstItems = (Items) firstObject;
            Items secondItems = (Items) secondObject;

            return firstItems.equals(secondItems);

        } else if (firstObject instanceof Documentation && secondObject instanceof Documentation) {
            Documentation firstDoc = (Documentation) firstObject;
            Documentation secondDoc = (Documentation) secondObject;

            return firstDoc.equals(secondDoc);

        } else if (firstObject instanceof Header && secondObject instanceof Header) {
            Header firstHeader = (Header) firstObject;
            Header secondHeader = (Header) secondObject;

            return firstHeader.equals(secondHeader);
        } else if (firstObject instanceof Model && secondObject instanceof Model) {
            Model firstModel = (Model) firstObject;
            Model secondModel = (Model) secondObject;

            return firstModel.equals(secondModel);
        } else {
            return false;
        }
    }
}
