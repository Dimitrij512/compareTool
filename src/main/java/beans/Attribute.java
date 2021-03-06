package beans;

import beans.interfaces.BeanItemVisitor;
import beans.interfaces.ItemElement;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.eclipse.swt.widgets.TreeItem;

import java.util.List;

@Data
@EqualsAndHashCode
@XStreamAlias("ATTRIBUTE")
public class Attribute implements ItemElement {

    @XStreamAsAttribute
    String attrType;

    @XStreamAsAttribute
    String attrName;

    @XStreamAsAttribute
    String linkType;

    @XStreamAsAttribute
    String contentType;

    @XStreamImplicit
    List<Items> items;

    @Override
    public void accept(BeanItemVisitor visitor, Object compaingItem, TreeItem treeItem) {
        visitor.visit(this, compaingItem, treeItem);
    }
}
