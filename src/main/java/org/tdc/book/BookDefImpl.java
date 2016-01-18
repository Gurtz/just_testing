package org.tdc.book;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tdc.config.book.BookDefConfig;
import org.tdc.modelinst.ModelInst;

public class BookDefImpl implements BookDef {
	
	private static final Logger log = LoggerFactory.getLogger(BookDefImpl.class);
	
	private BookDefConfig config;
	private Map<String, ModelInst> pageNameModelInstMap;
	
	public BookDefImpl(BookDefConfig config, Map<String, ModelInst> pageNameModelInstMap) {
		this.config = config;
		this.pageNameModelInstMap = pageNameModelInstMap;
		log.debug("Creating BookDefImpl: {}", config.getAddr());
	}

}
