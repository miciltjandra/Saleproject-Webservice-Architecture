
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
     *     returns boolean
     * @throws IOException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "checkAccess", targetNamespace = "http://marketplace/", className = "marketplace.CheckAccess")
    @ResponseWrapper(localName = "checkAccessResponse", targetNamespace = "http://marketplace/", className = "marketplace.CheckAccessResponse")
    @Action(input = "http://marketplace/Marketplace/checkAccessRequest", output = "http://marketplace/Marketplace/checkAccessResponse", fault = {
        @FaultAction(className = IOException_Exception.class, value = "http://marketplace/Marketplace/checkAccess/Fault/IOException")
    })
    public boolean checkAccess(
        @WebParam(name = "token", targetNamespace = "")
        String token,
        @WebParam(name = "id", targetNamespace = "")
        String id)
        throws IOException_Exception
    ;

    /**
     * 
     * @param image
     * @param price
     * @param prdname
     * @param id
     * @param desc
     * @param token
     * @param username
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addProduct", targetNamespace = "http://marketplace/", className = "marketplace.AddProduct")
    @ResponseWrapper(localName = "addProductResponse", targetNamespace = "http://marketplace/", className = "marketplace.AddProductResponse")
    @Action(input = "http://marketplace/Marketplace/addProductRequest", output = "http://marketplace/Marketplace/addProductResponse")
    public boolean addProduct(
        @WebParam(name = "prdname", targetNamespace = "")
        String prdname,
        @WebParam(name = "desc", targetNamespace = "")
        String desc,
        @WebParam(name = "price", targetNamespace = "")
        String price,
        @WebParam(name = "token", targetNamespace = "")
        String token,
        @WebParam(name = "id", targetNamespace = "")
        String id,
        @WebParam(name = "username", targetNamespace = "")
        String username,
        @WebParam(name = "image", targetNamespace = "")
        Object image);

    /**
     * 
     * @param searchtype
     * @param id
     * @param value
     * @param token
     * @return
     *     returns java.util.List<marketplace.Purchase>
     */
    @WebMethod
    @WebResult(name = "Purchase", targetNamespace = "")
    @RequestWrapper(localName = "retrieveSales", targetNamespace = "http://marketplace/", className = "marketplace.RetrieveSales")
    @ResponseWrapper(localName = "retrieveSalesResponse", targetNamespace = "http://marketplace/", className = "marketplace.RetrieveSalesResponse")
    @Action(input = "http://marketplace/Marketplace/retrieveSalesRequest", output = "http://marketplace/Marketplace/retrieveSalesResponse")
    public List<Purchase> retrieveSales(
        @WebParam(name = "token", targetNamespace = "")
        String token,
        @WebParam(name = "id", targetNamespace = "")
        String id,
        @WebParam(name = "searchtype", targetNamespace = "")
        String searchtype,
        @WebParam(name = "value", targetNamespace = "")
        String value);

    /**
     * 
     * @param productid
     * @param userid
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "increaseLike", targetNamespace = "http://marketplace/", className = "marketplace.IncreaseLike")
    @ResponseWrapper(localName = "increaseLikeResponse", targetNamespace = "http://marketplace/", className = "marketplace.IncreaseLikeResponse")
    @Action(input = "http://marketplace/Marketplace/increaseLikeRequest", output = "http://marketplace/Marketplace/increaseLikeResponse")
    public String increaseLike(
        @WebParam(name = "userid", targetNamespace = "")
        String userid,
        @WebParam(name = "productid", targetNamespace = "")
        String productid);

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

    /**
     * 
     * @param productid
     * @param userid
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getLiked", targetNamespace = "http://marketplace/", className = "marketplace.GetLiked")
    @ResponseWrapper(localName = "getLikedResponse", targetNamespace = "http://marketplace/", className = "marketplace.GetLikedResponse")
    @Action(input = "http://marketplace/Marketplace/getLikedRequest", output = "http://marketplace/Marketplace/getLikedResponse")
    public String getLiked(
        @WebParam(name = "userid", targetNamespace = "")
        String userid,
        @WebParam(name = "productid", targetNamespace = "")
        String productid);

    /**
     * 
     * @param id
     * @param idproduk
     * @param token
     * @return
     *     returns marketplace.Product
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "retrieveId", targetNamespace = "http://marketplace/", className = "marketplace.RetrieveId")
    @ResponseWrapper(localName = "retrieveIdResponse", targetNamespace = "http://marketplace/", className = "marketplace.RetrieveIdResponse")
    @Action(input = "http://marketplace/Marketplace/retrieveIdRequest", output = "http://marketplace/Marketplace/retrieveIdResponse")
    public Product retrieveId(
        @WebParam(name = "token", targetNamespace = "")
        String token,
        @WebParam(name = "id", targetNamespace = "")
        String id,
        @WebParam(name = "idproduk", targetNamespace = "")
        String idproduk);

}
