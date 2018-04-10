package beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;

import java.util.List;

@Data
@XStreamAlias("XMI.header")
public class Header {
    @XStreamAlias("XMI.documentation")
    private Documentation documentation;
    @XStreamImplicit
    private List<Model> models;
}


