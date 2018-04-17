package beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;

import java.util.List;

@Data
@XStreamAlias("ATTRIBUTE")
public class Attribute {

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

}
