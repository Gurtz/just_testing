package org.tdc.modeldef;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMError;
import org.w3c.dom.DOMErrorHandler;

public class ModelDefDOMErrorHandler implements DOMErrorHandler {

	private static final Logger log = LoggerFactory.getLogger(ModelDefDOMErrorHandler.class);
	
	private boolean failOnParserWarning;
	private boolean failOnParserNonFatalError;

	public ModelDefDOMErrorHandler(boolean failOnParserWarning, boolean failOnParserNonFatalError) {
		this.failOnParserWarning = failOnParserWarning;
		this.failOnParserNonFatalError = failOnParserNonFatalError;
	}

	@Override
	public boolean handleError(DOMError error) {
		if (error.getSeverity() == DOMError.SEVERITY_WARNING) {
			log.warn("DOMErrorHandler WARNING: {}", error.getMessage());
			if (failOnParserWarning) {
				throwException(error);
			}
		} else if (error.getSeverity() == DOMError.SEVERITY_ERROR) {
			log.error("DOMErrorHandler ERROR: {}", error.getMessage());
			if (failOnParserNonFatalError) {
				throwException(error);
			}
		} else {
			log.error("DOMErrorHandler FATAL ERROR: {}", error.getMessage());
			throwException(error);
		}
		return true; // continue processing, if possible (false means stop processing)
	}
	
	private void throwException(DOMError error) {
		String message = error.getMessage();
		throw new RuntimeException(message, (Exception)error.getRelatedException());
	}
}
