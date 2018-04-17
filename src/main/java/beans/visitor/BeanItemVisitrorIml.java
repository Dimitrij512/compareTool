package beans.visitor;

import beans.Attribute;
import beans.Attributes;
import beans.Item;
import beans.Items;
import beans.interfaces.BeanItemVisitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TreeItem;

import java.util.stream.IntStream;

public class BeanItemVisitrorIml implements BeanItemVisitor {

    @Override
    public void visit(Item item, TreeItem treeItem) {

        TreeItem subTreeItem = new TreeItem(treeItem, SWT.NONE);
        subTreeItem.setText(new String[]{"ITEM"});

        TreeItem itemId = new TreeItem(subTreeItem, SWT.NONE);
        itemId.setText(new String[]{"xmi.id", item.getId()});

        TreeItem itemType = new TreeItem(subTreeItem, SWT.NONE);
        itemType.setText(new String[]{"itemType", item.getItemType()});

        TreeItem itemName = new TreeItem(subTreeItem, SWT.NONE);
        itemName.setText(new String[]{"itemName", item.getItemName()});

        if (item.getAtributes() != null) {
            IntStream.range(0, item.getAtributes().size()).forEachOrdered(indexAttribute -> {
                this.visit(item.getAtributes().get(indexAttribute), subTreeItem);
            });
        }
    }

    @Override
    public void visit(Attributes attributes, TreeItem treeItem) {

        TreeItem treeItemAttributes = new TreeItem(treeItem, SWT.NONE);
        treeItemAttributes.setText(new String[]{"ATTRIBUTES"});

        if (attributes.getAttribute() != null) {
            IntStream.range(0, attributes.getAttribute().size()).forEachOrdered(indexAttributes -> {
                this.visit(attributes.getAttribute().get(indexAttributes), treeItemAttributes);
            });
        }
    }

    @Override
    public void visit(Attribute attribute, TreeItem treeItem) {

        TreeItem treeItemAttribute = new TreeItem(treeItem, SWT.NONE);
        treeItemAttribute.setText(new String[]{"ATTRIBUTE"});

        TreeItem attrType = new TreeItem(treeItemAttribute, SWT.NONE);
        attrType.setText(new String[]{"attrType", attribute.getAttrType()});

        TreeItem attrName = new TreeItem(treeItemAttribute, SWT.NONE);
        attrName.setText(new String[]{"attrName", attribute.getAttrName()});

        TreeItem linkType = new TreeItem(treeItemAttribute, SWT.NONE);
        linkType.setText(new String[]{"linkType", attribute.getLinkType()});

        if (attribute.getItems() != null) {
            IntStream.range(0, attribute.getItems().size()).forEachOrdered(indexItems -> {
                this.visit(attribute.getItems().get(indexItems), treeItemAttribute);
            });
        }
    }

    @Override
    public void visit(Items items, TreeItem treeItem) {
        TreeItem treeItems = new TreeItem(treeItem, SWT.NONE);
        treeItems.setText(new String[]{"ITEMS"});

        if (items.getItems() != null) {
            IntStream.range(0, items.getItems().size()).forEachOrdered(indexItem -> {
                this.visit(items.getItems().get(indexItem), treeItem);
            });
        }
    }


}
