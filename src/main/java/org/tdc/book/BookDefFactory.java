package org.tdc.book;

import org.tdc.config.book.BookDefConfig;

public interface BookDefFactory {
	BookDef getBookDef(BookDefConfig config);
}
