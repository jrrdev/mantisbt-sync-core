/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 J�rard Devarulrajah
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.jrrdev.mantisbtsync.core.common.auth.beans;

import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.github.jrrdev.mantisbtsync.core.common.auth.request.AuthHttpPost;

/**
 * Bean describing a POST request in the XML file.
 * This bean is used to build an {@link AuthHttpPost} object.
 *
 * @author jrrdev
 *
 */
@XmlType(propOrder = { "formAction", "parameters"})
public class HttpPostBean extends AbstractHttpRequestTypeBean {

	/**
	 * Name of the html form related to the POST request.
	 */
	private String formAction;

	/**
	 * Map containing the values of the form inputs.
	 */
	private Map<String, String> parameters;

	/**
	 * Default constructor.
	 */
	public HttpPostBean() {
	}

	/**
	 * @return the formAction
	 */
	public String getFormAction() {
		return formAction;
	}

	/**
	 * @param formAction the formAction to set
	 */
	@XmlElement
	public void setFormAction(final String formAction) {
		this.formAction = formAction;
	}

	/**
	 * @return the parameters
	 */
	public Map<String, String> getParameters() {
		return parameters;
	}

	/**
	 * @param parameters the parameters to set
	 */
	@XmlElement(nillable = true)
	public void setParameters(final Map<String, String> parameters) {
		this.parameters = parameters;
	}
}
