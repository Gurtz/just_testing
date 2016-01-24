package org.tdc.book;

import org.tdc.config.book.BookDefConfig;
import org.tdc.config.model.ModelConfigFactory;
import org.tdc.modelinst.ModelInstFactory;

public interface BookDefBuilder {
	BookDef build(BookDefConfig config, ModelInstFactory modelInstFactory, ModelConfigFactory modelInstConfigFactory);
}
