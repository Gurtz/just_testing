package org.tdc.schema;

import org.tdc.config.schema.SchemaConfig;
import org.tdc.util.Addr;

public interface SchemaFactory {
	Schema getSchema(SchemaConfig config);
}
