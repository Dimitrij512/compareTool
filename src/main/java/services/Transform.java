package services;

import beans.Attributes;
import beans.Item;
import beans.Items;
import beans.XmlBean;
import com.thoughtworks.xstream.XStream;
import main.Main;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import java.io.File;
import java.util.Optional;
import java.util.stream.IntStream;

public class Transform {

    // TODO It is temporary method, here need implementation for choose files
    public XmlBean getXMLBean(String path) {

        XStream xstream = new XStream();
        xstream.processAnnotations(XmlBean.class);
        xstream.ignoreUnknownElements();

        File xmlFile = new File(Main.class.getClassLoader().getResource("testXML.xml").getFile());

        System.out.println(xstream.toXML(xstream.fromXML(xmlFile)));

        return (XmlBean) xstream.fromXML(xmlFile);
    }

    public void createTree(XmlBean firstFile, XmlBean secondFile) {

        Display display = new Display();
        final Shell shell = new Shell(display);
        shell.setLayout(new FillLayout());

        Tree treeFirst = new Tree(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        treeFirst.setHeaderVisible(true);

        TreeColumn column1 = createColumn(treeFirst, "The path first file");
        TreeColumn column2 = createColumn(treeFirst, "Value");
        TreeItem header = createHeader(treeFirst, firstFile);
        TreeItem content = createContent(treeFirst,firstFile);

        shell.pack();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }

    private TreeColumn createColumn(Tree tree, String name){
        TreeColumn column = new TreeColumn(tree, SWT.LEFT);
        column.setText(name);
        column.setWidth(400);

        return  column;
    }

    private TreeItem createHeader(Tree tree, XmlBean firstFile){
        TreeItem header  = new TreeItem(tree, SWT.NONE);
        header.setText("XMI.header");

        createDocumentation(header, firstFile);
        createModels(header, firstFile);

        return header;
    }

    private void createDocumentation(TreeItem header,  XmlBean firstFile){
        TreeItem documentation = new TreeItem(header, SWT.NONE);
        documentation.setText("XMI.documentation");

        TreeItem docExporter = new TreeItem(documentation, SWT.NONE);
        docExporter.setText(new String[] {"XMI.exporter", firstFile.getHeader().getDocumentation().getExporter()});

        TreeItem docVersin = new TreeItem(documentation, SWT.NONE);
        docVersin.setText(new String[] {"XMI.exporterVersion", firstFile.getHeader().getDocumentation().getVersion()});
    }

    private void createModels(TreeItem header,  XmlBean firstFile){
        IntStream.range(0, firstFile.getHeader().getModels().size()).forEachOrdered(index ->{
            TreeItem subModel = new TreeItem(header, SWT.NONE);
            subModel.setText(new String[] {"XMI.model"});

            TreeItem subModelName = new TreeItem(subModel, SWT.NONE);
            subModelName.setText(new String[] {"xmi.name", firstFile.getHeader().getModels().get(index).getXmiName()});

            TreeItem subModelVersion = new TreeItem(subModel, SWT.NONE);
            subModelVersion.setText(new String[] {"xmi.version", firstFile.getHeader().getModels().get(index).getVersion()});

            TreeItem subModelHref = new TreeItem(subModel, SWT.NONE);
            subModelHref.setText(new String[] {"href", firstFile.getHeader().getModels().get(index).getHref()});
        });
    }

    private TreeItem createContent(Tree tree, XmlBean firstFile){
        TreeItem content = new TreeItem(tree, SWT.NONE);
        content.setText(new String[] {"XMI.content"});

        TreeItem contentUuid = new TreeItem(content, SWT.NONE);
        contentUuid.setText(new String[] {"xmi.uuid", firstFile.getContent().getUuid()});

        TreeItem contentUuidDel = new TreeItem(content, SWT.NONE);
        contentUuidDel.setText(new String[] {"xmi.uuidDel", firstFile.getContent().getUuiDel()});

        createListItems(content, firstFile);

        return content;
    }

    private void createListItems(TreeItem content, XmlBean firstFile){
        IntStream.range(0, firstFile.getContent().getItems().size()).forEachOrdered(index ->{

            Optional<Item> xmlItem = Optional.of(firstFile.getContent().getItems().get(index));

            TreeItem item = new TreeItem(content, SWT.NONE);
            item.setText(new String[] {"ITEM"});

            TreeItem itemId = new TreeItem(item, SWT.NONE);
            itemId.setText(new String[] {"xmi.id", xmlItem.get().getId()});

            TreeItem itemType = new TreeItem(item, SWT.NONE);
            itemType.setText(new String[] {"itemType", firstFile.getContent().getItems().get(index).getItemType()});

            TreeItem itemName = new TreeItem(item, SWT.NONE);
            itemName.setText(new String[] {"itemName", firstFile.getContent().getItems().get(index).getItemName()});

            if(firstFile.getContent().getItems().get(index).getAtributes() != null) {
                IntStream.range(0, firstFile.getContent().getItems().get(index).getAtributes().size()).forEachOrdered(indexAttr ->{

                    Optional<Attributes> xmlAttributes = Optional.of(firstFile.getContent().getItems().get(index).getAtributes().get(indexAttr));

                    TreeItem attributes = new TreeItem(item, SWT.NONE);
                    attributes.setText(new String[] {"ATTRIBUTES"});

                    IntStream.range(0, xmlAttributes.get().getAttribute().size()).forEachOrdered(indexAttribute -> {

                        TreeItem attribute = new TreeItem(attributes, SWT.NONE);
                        attribute.setText(new String[] {"ATTRIBUTE"});

                        TreeItem attrType = new TreeItem(attribute, SWT.NONE);
                        attrType.setText(new String[] {"attrType", firstFile.getContent().getItems().get(index).getAtributes().get(indexAttr).getAttribute().get(indexAttribute).getAttrType()});

                        TreeItem attrName = new TreeItem(attribute, SWT.NONE);
                        attrName.setText(new String[] {"attrName", firstFile.getContent().getItems().get(index).getAtributes().get(indexAttr).getAttribute().get(indexAttribute).getAttrName()});

                        TreeItem linkType = new TreeItem(attribute, SWT.NONE);
                        linkType.setText(new String[] {"linkType", firstFile.getContent().getItems().get(index).getAtributes().get(indexAttr).getAttribute().get(indexAttribute).getLinkType()});

                        if(xmlAttributes.get().getAttribute().get(indexAttribute).getItems() != null) {

                            IntStream.range(0, xmlAttributes.get().getAttribute().get(indexAttribute).getItems().size()).forEachOrdered(indexAttrItem -> {

                                Optional<Items> xmlAttributesItems = Optional.of(xmlAttributes.get().getAttribute().get(indexAttribute).getItems().get(indexAttrItem));
                                TreeItem attributeItems = new TreeItem(attribute, SWT.NONE);
                                attributeItems.setText(new String[] {"ITEMS"});

                                IntStream.range(0,xmlAttributesItems.get().getItems().size()).forEachOrdered(indexAttrItems ->{

                                    TreeItem attributeItem = new TreeItem(attributeItems, SWT.NONE);
                                    attributeItem.setText(new String[] {"ITEM"});

                                    TreeItem attributeItemId = new TreeItem(attributeItem, SWT.NONE);
                                    attributeItemId.setText(new String[] {"xmi.id",xmlAttributesItems.get().getItems().get(indexAttrItems).getId()});

                                    TreeItem attributeItemType = new TreeItem(attributeItem, SWT.NONE);
                                    attributeItemType.setText(new String[] {"itemType", xmlAttributesItems.get().getItems().get(indexAttrItems).getItemType()});

                                    TreeItem attributeItemName = new TreeItem(attributeItem, SWT.NONE);
                                    attributeItemName.setText(new String[] {"itemName", xmlAttributesItems.get().getItems().get(indexAttrItems).getItemName()});
                                    TreeItem attributeItemScope = new TreeItem(attributeItem, SWT.NONE);
                                    attributeItemScope.setText(new String[] {"itemScope", xmlAttributesItems.get().getItems().get(indexAttrItems).getScope()});
                                });
                            });
                        }
                    });
                });
            }
        });
    }
}
