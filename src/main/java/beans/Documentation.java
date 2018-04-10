package beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("XMI.documentation")
public class Documentation {
    @XStreamAlias("XMI.exporter")
    private String exporter;
    @XStreamAlias("XMI.exporterVersion")
    private String version;
}
