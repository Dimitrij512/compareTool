package beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;
import java.util.List;

@Data
@XStreamAlias("XMI.content")
public class Content {
    @XStreamAlias("xmi.uuid")
    @XStreamAsAttribute
    String uuid;

    @XStreamAlias("uuidDel")
    @XStreamAsAttribute
    String uuiDel;

    @XStreamImplicit
    private List<Item> items;
}
