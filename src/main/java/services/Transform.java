package services;

import beans.Item;
import beans.Model;
import beans.XmlBean;
import beans.interfaces.BeanItemVisitor;
import beans.visitor.BeanItemVisitrorIml;
import com.thoughtworks.xstream.XStream;
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

    private BeanItemVisitor vizitor = new BeanItemVisitrorIml();

    // TODO It is temporary method, here need implementation for choose files
    public XmlBean getXMLBean(String path) {
        XStream xstream = new XStream();
        xstream.processAnnotations(XmlBean.class);
        xstream.ignoreUnknownElements();

        File xmlFile = new File(path);

        System.out.println(xstream.toXML(xstream.fromXML(xmlFile)));

        return (XmlBean) xstream.fromXML(xmlFile);
    }

    public void createTree(XmlBean firstFile, XmlBean secondFile) {

        Display display = new Display();
        final Shell shell = new Shell(display);
        shell.setLayout(new FillLayout());

        Tree treeFirst = new Tree(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        treeFirst.setHeaderVisible(true);

        Tree treeSecond = new Tree(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        treeSecond.setHeaderVisible(true);

        TreeColumn column1 = createColumn(treeFirst, "The path first file");
        TreeColumn column2 = createColumn(treeFirst, "Value");
        TreeItem header = createHeader(treeFirst, firstFile, secondFile);
        TreeItem content = createContent(treeFirst, firstFile, secondFile);

        TreeColumn sTcolumn1 = createColumn(treeSecond, "The path second file");
        TreeColumn sTcolumn2 = createColumn(treeSecond, "Value");
        TreeItem sTheader = createHeader(treeSecond, secondFile, firstFile);

        TreeItem sTcontent = createContent(treeSecond, secondFile, firstFile);

        shell.pack();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }

    private TreeColumn createColumn(Tree tree, String name) {
        TreeColumn column = new TreeColumn(tree, SWT.LEFT);
        column.setText(name);
        column.setWidth(400);

        return column;
    }

    private TreeItem createHeader(Tree tree, XmlBean firstFile, XmlBean secondFile) {
        TreeItem header = new TreeItem(tree, SWT.NONE);
        header.setText("XMI.header");
        header.setExpanded(true);

        if (firstFile.getHeader().equals(secondFile.getHeader())) {
            header.setExpanded(true);
        }

        createDocumentation(header, firstFile, secondFile);
        createModels(header, firstFile, secondFile);

        return header;
    }

    private void createDocumentation(TreeItem header, XmlBean firstFile, XmlBean secondFile) {
        TreeItem documentation = Service.createTreeItem(firstFile.getHeader().getDocumentation(), secondFile.getHeader().getDocumentation(), header, new String[]{"XMI.documentation"});

        TreeItem docExporter = Service.createTreeItem(firstFile.getHeader().getDocumentation().getExporter(), secondFile.getHeader().getDocumentation().getExporter(), documentation, new String[]{"XMI.exporter", firstFile.getHeader().getDocumentation().getExporter()});

        TreeItem docVersin = Service.createTreeItem(firstFile.getHeader().getDocumentation().getVersion(), secondFile.getHeader().getDocumentation().getVersion(), documentation, new String[]{"XMI.exporterVersion", firstFile.getHeader().getDocumentation().getVersion()});

    }

    private void createModels(TreeItem header, XmlBean firstFile, XmlBean secondFile) {
        IntStream.range(0, firstFile.getHeader().getModels().size()).forEachOrdered(index -> {

            Model firstModel = firstFile.getHeader().getModels().get(index);
            Model secondModel =  secondFile.getHeader().getModels().get(index);

            TreeItem subModel = Service.createTreeItem(firstModel, secondModel, header, new String[]{"XMI.model"});

            TreeItem subModelName = Service.createTreeItem(firstModel.getXmiName(), secondModel.getXmiName(), subModel, new String[]{"xmi.name", firstModel.getXmiName()});

            TreeItem subModelVersion = Service.createTreeItem(firstModel.getVersion(), secondModel.getVersion(), subModel, new String[]{"xmi.version", firstModel.getVersion()});

            TreeItem subModelHref = Service.createTreeItem(firstModel.getHref(), secondModel.getHref(), subModel, new String[]{"href", firstModel.getHref()});

        });
    }

    private TreeItem createContent(Tree tree, XmlBean firstFile, XmlBean secondFile) {
        TreeItem content = new TreeItem(tree, SWT.NONE);
        content.setText(new String[]{"XMI.content"});

        TreeItem contentUuid = new TreeItem(content, SWT.NONE);
        contentUuid.setText(new String[]{"xmi.uuid", firstFile.getContent().getUuid()});

        TreeItem contentUuidDel = new TreeItem(content, SWT.NONE);
        contentUuidDel.setText(new String[]{"xmi.uuidDel", firstFile.getContent().getUuiDel()});
        createListItems(content, firstFile, secondFile);

        return content;
    }

    private void createListItems(TreeItem content, XmlBean firstFile, XmlBean secondFile) {
        IntStream.range(0, firstFile.getContent().getItems().size()).forEachOrdered(index -> {
            Optional<Item> firstXmlItem = Optional.of(firstFile.getContent().getItems().get(index));
            Optional<Item> secondXmlItem = Optional.of(secondFile.getContent().getItems().get(index));

            firstXmlItem.get().accept(vizitor, secondXmlItem.get(), content);
        });
    }
}
