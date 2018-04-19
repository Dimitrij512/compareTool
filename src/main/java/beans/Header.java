package beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
@XStreamAlias("XMI.header")
public class Header {
    @XStreamAlias("XMI.documentation")
    private Documentation documentation;

    @XStreamImplicit
    private List<Model> models;
}


