package beans;

import beans.interfaces.BeanItemVisitor;
import beans.interfaces.ItemElement;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.eclipse.swt.widgets.TreeItem;

import java.util.List;

@Data
@EqualsAndHashCode
@XStreamAlias("ATTRIBUTES")
public class Attributes implements ItemElement {

    @XStreamImplicit
    List<Attribute> attribute;

    @Override
    public void accept(BeanItemVisitor visitor, TreeItem treeItem) {
        visitor.visit(this, treeItem);
    }
}
