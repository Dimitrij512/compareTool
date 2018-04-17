package beans;

import beans.interfaces.BeanItemVisitor;
import beans.interfaces.ItemElement;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;
import org.eclipse.swt.widgets.TreeItem;

import java.util.List;

@Data
@XStreamAlias("ITEMS")
public class Items implements ItemElement {

    @XStreamImplicit
    List<Item> items;

    @Override
    public void accept(BeanItemVisitor visitor, TreeItem treeItem) {
        visitor.visit(this, treeItem);
    }
}
