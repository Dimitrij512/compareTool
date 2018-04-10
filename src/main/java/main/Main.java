package main;

import beans.XmlBean;
import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


//TODO  It is test version
public class Main {
    public static void main(String[] args) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(XmlBean.class);
        xstream.ignoreUnknownElements();

        File xmlFile = new File(Main.class.getClassLoader().getResource("testXML.xml").getFile());

        XmlBean b = (XmlBean) xstream.fromXML(xmlFile);
        //System.out.println(b);
        String xml = xstream.toXML(b);
        //System.out.println(xml);


        Shell shell = new Shell();
        shell.setLayout(new FillLayout());
        shell.setMaximized(true);

        Display display = shell.getDisplay();

        StyledText styledText1 = new StyledText(shell, SWT.BORDER);
        styledText1.setText(xml);

        StyledText styledText2 = new StyledText(shell, SWT.BORDER);
        styledText2.setText(xml);

        shell.open ();

        while (!shell.isDisposed())
            if (!display.readAndDispatch()) display.sleep();
    }
}
