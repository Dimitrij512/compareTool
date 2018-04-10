package beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;

import java.util.List;

@Data
@XStreamAlias("ATTRIBUTES")
public class Attributes {

    @XStreamImplicit
    List<Attribute> attribute;
}
