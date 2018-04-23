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
@XStreamAlias("ITEMS")
public class Items implements ItemElement {

    @XStreamImplicit
    List<Item> items;

    @Override
    public void accept(BeanItemVisitor visitor, Object compaingItem, TreeItem treeItem) {
        visitor.visit(this, compaingItem, treeItem);
    }
}
