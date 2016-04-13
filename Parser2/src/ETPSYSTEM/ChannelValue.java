package ETPSYSTEM;

import org.jcsp.lang.Any2AnyChannel;
import org.jcsp.lang.Channel;
import org.jcsp.lang.One2OneChannel;
import org.jcsp.lang.One2OneChannelSymmetric;

public class ChannelValue {
	public String type;
	public	One2OneChannel channel = Channel.createOne2One();
	public ChannelValue(){
	}
}
