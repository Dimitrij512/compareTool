package beans;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Data;

@Data
@XStreamAlias("XMI.model")
public class Model {

    @XStreamAlias("xmi.name")
    @XStreamAsAttribute
    private String xmiName;

    @XStreamAlias("xmi.version")
    @XStreamAsAttribute
    private String version;

    @XStreamAsAttribute
    private String href;
}
