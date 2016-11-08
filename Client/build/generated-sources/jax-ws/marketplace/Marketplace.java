
package marketplace;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.11-b150120.1832
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Marketplace", targetNamespace = "http://marketplace/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Marketplace {


    /**
     * 
     * @param id
     * @param token
     * @return
     *     returns java.lang.String
     * @throws IOException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "checkAccess", targetNamespace = "http://marketplace/", className = "marketplace.CheckAccess")
    @ResponseWrapper(localName = "checkAccessResponse", targetNamespace = "http://marketplace/", className = "marketplace.CheckAccessResponse")
    @Action(input = "http://marketplace/Marketplace/checkAccessRequest", output = "http://marketplace/Marketplace/checkAccessResponse", fault = {
        @FaultAction(className = IOException_Exception.class, value = "http://marketplace/Marketplace/checkAccess/Fault/IOException")
    })
    public String checkAccess(
        @WebParam(name = "token", targetNamespace = "")
        String token,
        @WebParam(name = "id", targetNamespace = "")
        String id)
        throws IOException_Exception
    ;

    /**
     * 
     * @param searchtype
     * @param id
     * @param value
     * @param token
     * @return
     *     returns java.util.List<marketplace.Product>
     */
    @WebMethod
    @WebResult(name = "Product", targetNamespace = "")
    @RequestWrapper(localName = "retrieveProduct", targetNamespace = "http://marketplace/", className = "marketplace.RetrieveProduct")
    @ResponseWrapper(localName = "retrieveProductResponse", targetNamespace = "http://marketplace/", className = "marketplace.RetrieveProductResponse")
    @Action(input = "http://marketplace/Marketplace/retrieveProductRequest", output = "http://marketplace/Marketplace/retrieveProductResponse")
    public List<Product> retrieveProduct(
        @WebParam(name = "token", targetNamespace = "")
        String token,
        @WebParam(name = "id", targetNamespace = "")
        String id,
        @WebParam(name = "searchtype", targetNamespace = "")
        String searchtype,
        @WebParam(name = "value", targetNamespace = "")
        String value);

}
