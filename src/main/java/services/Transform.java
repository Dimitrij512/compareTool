package services;

import beans.Item;
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

        if (!firstFile.getHeader().equals(secondFile.getHeader())) {
            Service.setCollorNode(header).setExpanded(true);
        }

        TreeItem content = createContent(treeFirst, firstFile, secondFile);

        if (!firstFile.getContent().equals(secondFile.getContent())) {
            Service.setCollorNode(content).setExpanded(true);
        }

        TreeColumn sTcolumn1 = createColumn(treeSecond, "The path second file");
        TreeColumn sTcolumn2 = createColumn(treeSecond, "Value");

        TreeItem sTheader = createHeader(treeSecond, secondFile, firstFile);

        if (!secondFile.getHeader().equals(firstFile.getHeader())) {
            Service.setCollorNode(sTheader).setExpanded(true);
        }

        TreeItem sTcontent = createContent(treeSecond, secondFile, firstFile);

        if (!secondFile.getContent().equals(firstFile.getContent())) {
            Service.setCollorNode(sTcontent).setExpanded(true);
        }

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
            Service.setCollorNode(header).setExpanded(true);
        }

        createDocumentation(header, firstFile, secondFile);
        createModels(header, firstFile, secondFile);

        return header;
    }

    private void createDocumentation(TreeItem header, XmlBean firstFile, XmlBean secondFile) {
        TreeItem documentation = new TreeItem(header, SWT.NONE);
        documentation.setText("XMI.documentation");

        TreeItem docExporter = new TreeItem(documentation, SWT.NONE);
        docExporter.setText(new String[]{"XMI.exporter", firstFile.getHeader().getDocumentation().getExporter()});

        if (!firstFile.getHeader().getDocumentation().getExporter().equals(secondFile.getHeader().getDocumentation().getExporter())) {
            Service.setCollorNode(documentation).setExpanded(true);
            Service.setCollorNode(docExporter).setExpanded(true);
        }

        TreeItem docVersin = new TreeItem(documentation, SWT.NONE);
        docVersin.setText(new String[]{"XMI.exporterVersion", firstFile.getHeader().getDocumentation().getVersion()});
        if (!firstFile.getHeader().getDocumentation().getVersion().equals(secondFile.getHeader().getDocumentation().getVersion())) {
            Service.setCollorNode(docVersin).setExpanded(true);
        }
    }

    private void createModels(TreeItem header, XmlBean firstFile, XmlBean secondFile) {
        IntStream.range(0, firstFile.getHeader().getModels().size()).forEachOrdered(index -> {
            TreeItem subModel = new TreeItem(header, SWT.NONE);
            subModel.setText(new String[]{"XMI.model"});

            TreeItem subModelName = new TreeItem(subModel, SWT.NONE);
            subModelName.setText(new String[]{"xmi.name", firstFile.getHeader().getModels().get(index).getXmiName()});

            if (!firstFile.getHeader().getModels().get(index).getXmiName().equals(secondFile.getHeader().getModels().get(index).getXmiName())) {
                Service.setCollorNode(subModel).setExpanded(true);
                Service.setCollorNode(subModelName).setExpanded(true);
            }

            TreeItem subModelVersion = new TreeItem(subModel, SWT.NONE);
            subModelVersion.setText(new String[]{"xmi.version", firstFile.getHeader().getModels().get(index).getVersion()});

            if (!firstFile.getHeader().getModels().get(index).getVersion().equals(secondFile.getHeader().getModels().get(index).getVersion())) {
                Service.setCollorNode(subModelVersion).setExpanded(true);
            }

            TreeItem subModelHref = new TreeItem(subModel, SWT.NONE);
            subModelHref.setText(new String[]{"href", firstFile.getHeader().getModels().get(index).getHref()});

            if (firstFile.getHeader().getModels().get(index).getHref() != null && secondFile.getHeader().getModels().get(index).getHref() != null) {
                if (!firstFile.getHeader().getModels().get(index).getHref().equals(secondFile.getHeader().getModels().get(index).getHref())) {
                    Service.setCollorNode(subModelHref).setExpanded(true);
                }
            }

        });
    }

    private TreeItem createContent(Tree tree, XmlBean firstFile, XmlBean secondFile) {
        TreeItem content = new TreeItem(tree, SWT.NONE);
        content.setText(new String[]{"XMI.content"});

        TreeItem contentUuid = new TreeItem(content, SWT.NONE);
        contentUuid.setText(new String[]{"xmi.uuid", firstFile.getContent().getUuid()});

        TreeItem contentUuidDel = new TreeItem(content, SWT.NONE);
        contentUuidDel.setText(new String[]{"xmi.uuidDel", firstFile.getContent().getUuiDel()});
        createListItems(content, firstFile);

        return content;
    }

    private void createListItems(TreeItem content, XmlBean firstFile) {
        IntStream.range(0, firstFile.getContent().getItems().size()).forEachOrdered(index -> {
            Optional<Item> xmlItem = Optional.of(firstFile.getContent().getItems().get(index));
            xmlItem.get().accept(vizitor, content);
        });
    }
}
