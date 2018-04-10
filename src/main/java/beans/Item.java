package beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;

import java.util.List;

@Data
@XStreamAlias("ITEM")
public class Item {
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
}
