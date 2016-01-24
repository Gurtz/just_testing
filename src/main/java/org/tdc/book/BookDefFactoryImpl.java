package org.tdc.book;

import org.tdc.config.book.BookDefConfig;
import org.tdc.config.model.ModelConfigFactory;
import org.tdc.modelinst.ModelInstFactory;
import org.tdc.util.Addr;
import org.tdc.util.Cache;
import org.tdc.util.CacheImpl;

public class BookDefFactoryImpl implements BookDefFactory {
	
	private Cache<BookDef> cache = new CacheImpl<>();
	private ModelInstFactory modelInstFactory;
	private ModelConfigFactory modelConfigFactory;
	
	public BookDefFactoryImpl(ModelInstFactory modelInstFactory, ModelConfigFactory modelConfigFactory) {
		this.modelInstFactory = modelInstFactory;
		this.modelConfigFactory = modelConfigFactory;
	}
	
	@Override
	public BookDef getBookDef(BookDefConfig config) {
		Addr addr = config.getAddr();
		BookDef bookDef = cache.get(addr);
		if (bookDef == null) {
			bookDef = buildNewBookDef(config);
			cache.put(addr, bookDef);
		}
		return bookDef;
	}
	
	private BookDef buildNewBookDef(BookDefConfig config) {
		BookDefBuilder bookDefBuilder = getBookDefBuilder();
		return bookDefBuilder.build(config, modelInstFactory, modelConfigFactory);
	}
	
	protected BookDefBuilder getBookDefBuilder() {
		return new BookDefBuilderImpl();
	}
}
