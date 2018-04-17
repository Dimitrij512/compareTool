package beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("XMI.main")
public class XmlBean {
    @XStreamAlias("XMI.header")
    Header header;

    @XStreamAlias("XMI.content")
    Content content;
}
