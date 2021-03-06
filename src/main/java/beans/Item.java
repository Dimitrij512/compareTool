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
@XStreamAlias("ITEM")
public class Item implements ItemElement {
    @XStreamAlias("xmi.id")
    @XStreamAsAttribute
    String id;

    @XStreamAlias("itemType")
    @XStreamAsAttribute
    String itemType;

    @XStreamAlias("itemName")
    @XStreamAsAttribute
    String itemName;

    @XStreamAlias("itemScope")
    @XStreamAsAttribute
    String scope;

    @XStreamImplicit
    List<Attributes> atributes;

    @Override
    public void accept(BeanItemVisitor visitor, Object compaingItem, TreeItem treeItem) {
        visitor.visit(this, compaingItem, treeItem);
    }
}
