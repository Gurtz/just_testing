package org.tdc.book;

import org.tdc.config.book.BookDefConfig;
import org.tdc.config.modelinst.ModelInstConfigFactory;
import org.tdc.modelinst.ModelInstFactory;

public interface BookDefBuilder {
	BookDef build(BookDefConfig config, ModelInstFactory modelInstFactory, ModelInstConfigFactory modelInstConfigFactory);
}
