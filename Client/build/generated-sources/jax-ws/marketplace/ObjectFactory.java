
package marketplace;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the marketplace package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _IOException_QNAME = new QName("http://marketplace/", "IOException");
    private final static QName _Product_QNAME = new QName("http://marketplace/", "Product");
    private final static QName _CheckAccess_QNAME = new QName("http://marketplace/", "checkAccess");
    private final static QName _CheckAccessResponse_QNAME = new QName("http://marketplace/", "checkAccessResponse");
    private final static QName _RetrieveProduct_QNAME = new QName("http://marketplace/", "retrieveProduct");
    private final static QName _RetrieveProductResponse_QNAME = new QName("http://marketplace/", "retrieveProductResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: marketplace
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link Product }
     * 
     */
    public Product createProduct() {
        return new Product();
    }

    /**
     * Create an instance of {@link CheckAccess }
     * 
     */
    public CheckAccess createCheckAccess() {
        return new CheckAccess();
    }

    /**
     * Create an instance of {@link CheckAccessResponse }
     * 
     */
    public CheckAccessResponse createCheckAccessResponse() {
        return new CheckAccessResponse();
    }

    /**
     * Create an instance of {@link RetrieveProduct }
     * 
     */
    public RetrieveProduct createRetrieveProduct() {
        return new RetrieveProduct();
    }

    /**
     * Create an instance of {@link RetrieveProductResponse }
     * 
     */
    public RetrieveProductResponse createRetrieveProductResponse() {
        return new RetrieveProductResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Product }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace/", name = "Product")
    public JAXBElement<Product> createProduct(Product value) {
        return new JAXBElement<Product>(_Product_QNAME, Product.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAccess }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace/", name = "checkAccess")
    public JAXBElement<CheckAccess> createCheckAccess(CheckAccess value) {
        return new JAXBElement<CheckAccess>(_CheckAccess_QNAME, CheckAccess.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAccessResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace/", name = "checkAccessResponse")
    public JAXBElement<CheckAccessResponse> createCheckAccessResponse(CheckAccessResponse value) {
        return new JAXBElement<CheckAccessResponse>(_CheckAccessResponse_QNAME, CheckAccessResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace/", name = "retrieveProduct")
    public JAXBElement<RetrieveProduct> createRetrieveProduct(RetrieveProduct value) {
        return new JAXBElement<RetrieveProduct>(_RetrieveProduct_QNAME, RetrieveProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace/", name = "retrieveProductResponse")
    public JAXBElement<RetrieveProductResponse> createRetrieveProductResponse(RetrieveProductResponse value) {
        return new JAXBElement<RetrieveProductResponse>(_RetrieveProductResponse_QNAME, RetrieveProductResponse.class, null, value);
    }

}
