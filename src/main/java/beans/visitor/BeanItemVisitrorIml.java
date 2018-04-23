package beans.visitor;

import beans.Attribute;
import beans.Attributes;
import beans.Item;
import beans.Items;
import beans.interfaces.BeanItemVisitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TreeItem;
import services.Service;

import java.util.stream.IntStream;

public class BeanItemVisitrorIml implements BeanItemVisitor {

    @Override
    public void visit(Item item, Object comparingItem, TreeItem treeItem) {

        Item compareItem = (Item) comparingItem;

        TreeItem subTreeItem = Service.createTreeItem(item, comparingItem, treeItem, new String[]{"ITEM"});

        TreeItem itemId = Service.createTreeItem(item.getId(), ((Item) comparingItem).getId(), subTreeItem, new String[]{"xmi.id"});

        TreeItem itemType = Service.createTreeItem(item.getItemType(), ((Item) comparingItem).getItemType(), subTreeItem, new String[]{"itemType", item.getItemType()});

        TreeItem itemName = Service.createTreeItem(item.getItemName(), ((Item) comparingItem).getItemName(), subTreeItem, new String[]{"itemName", item.getItemName()});

        if (item.getAtributes() != null && compareItem.getAtributes() != null) {
            IntStream.range(0, item.getAtributes().size()).forEachOrdered(indexAttribute -> {
                this.visit(item.getAtributes().get(indexAttribute), compareItem.getAtributes().get(indexAttribute), subTreeItem);
            });
        }
    }

    @Override
    public void visit(Attributes attributes, Object comparingItem, TreeItem treeItem) {

        Attributes compareAttributes = (Attributes) comparingItem;

        TreeItem treeItemAttributes = Service.createTreeItem(attributes, compareAttributes, treeItem, new String[]{"ATTRIBUTES"});


        if (attributes.getAttribute() != null && compareAttributes.getAttribute() != null) {
            IntStream.range(0, attributes.getAttribute().size()).forEachOrdered(indexAttributes -> {
                this.visit(attributes.getAttribute().get(indexAttributes), compareAttributes.getAttribute().get(indexAttributes), treeItemAttributes);
            });
        }
    }

    @Override
    public void visit(Attribute attribute, Object comparingItem, TreeItem treeItem) {

        Attribute compareAttribute = (Attribute) comparingItem;

        TreeItem treeItemAttribute = Service.createTreeItem(attribute, comparingItem, treeItem, new String[]{"ATTRIBUTE"});

        TreeItem attrType = Service.createTreeItem(attribute.getAttrType(), compareAttribute.getAttrType(), treeItemAttribute, new String[]{"attrType", attribute.getAttrType()});

        TreeItem attrName = Service.createTreeItem(attribute.getAttrName(), ((Attribute) comparingItem).getAttrName(), treeItemAttribute, new String[]{"attrName", attribute.getAttrName()});

        TreeItem linkType = Service.createTreeItem(attribute.getLinkType(), compareAttribute.getLinkType(), treeItemAttribute, new String[]{"linkType", attribute.getLinkType()});

        if (attribute.getItems() != null && compareAttribute.getItems() != null) {
            IntStream.range(0, attribute.getItems().size()).forEachOrdered(indexItems -> {
                this.visit(attribute.getItems().get(indexItems), compareAttribute.getItems().get(indexItems), treeItemAttribute);
            });
        }
    }

    @Override
    public void visit(Items items, Object comparingItem, TreeItem treeItem) {

        Items compareItems = (Items) comparingItem;

        TreeItem treeItems = Service.createTreeItem(items, compareItems, treeItem, new String[]{"ITEMS"});

        if (items.getItems() != null && compareItems.getItems() != null) {
            IntStream.range(0, items.getItems().size()).forEachOrdered(indexItem -> {
                this.visit(items.getItems().get(indexItem), compareItems.getItems().get(indexItem), treeItem);
            });
        }
    }
}
