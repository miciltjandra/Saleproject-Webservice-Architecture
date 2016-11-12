
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
    private final static QName _Purchase_QNAME = new QName("http://marketplace/", "Purchase");
    private final static QName _AddProduct_QNAME = new QName("http://marketplace/", "addProduct");
    private final static QName _AddProductResponse_QNAME = new QName("http://marketplace/", "addProductResponse");
    private final static QName _CheckAccess_QNAME = new QName("http://marketplace/", "checkAccess");
    private final static QName _CheckAccessResponse_QNAME = new QName("http://marketplace/", "checkAccessResponse");
    private final static QName _GetLiked_QNAME = new QName("http://marketplace/", "getLiked");
    private final static QName _GetLikedResponse_QNAME = new QName("http://marketplace/", "getLikedResponse");
    private final static QName _IncreaseLike_QNAME = new QName("http://marketplace/", "increaseLike");
    private final static QName _IncreaseLikeResponse_QNAME = new QName("http://marketplace/", "increaseLikeResponse");
    private final static QName _RetrieveId_QNAME = new QName("http://marketplace/", "retrieveId");
    private final static QName _RetrieveIdResponse_QNAME = new QName("http://marketplace/", "retrieveIdResponse");
    private final static QName _RetrieveProduct_QNAME = new QName("http://marketplace/", "retrieveProduct");
    private final static QName _RetrieveProductResponse_QNAME = new QName("http://marketplace/", "retrieveProductResponse");
    private final static QName _RetrieveSales_QNAME = new QName("http://marketplace/", "retrieveSales");
    private final static QName _RetrieveSalesResponse_QNAME = new QName("http://marketplace/", "retrieveSalesResponse");

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
     * Create an instance of {@link Purchase }
     * 
     */
    public Purchase createPurchase() {
        return new Purchase();
    }

    /**
     * Create an instance of {@link AddProduct }
     * 
     */
    public AddProduct createAddProduct() {
        return new AddProduct();
    }

    /**
     * Create an instance of {@link AddProductResponse }
     * 
     */
    public AddProductResponse createAddProductResponse() {
        return new AddProductResponse();
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
     * Create an instance of {@link GetLiked }
     * 
     */
    public GetLiked createGetLiked() {
        return new GetLiked();
    }

    /**
     * Create an instance of {@link GetLikedResponse }
     * 
     */
    public GetLikedResponse createGetLikedResponse() {
        return new GetLikedResponse();
    }

    /**
     * Create an instance of {@link IncreaseLike }
     * 
     */
    public IncreaseLike createIncreaseLike() {
        return new IncreaseLike();
    }

    /**
     * Create an instance of {@link IncreaseLikeResponse }
     * 
     */
    public IncreaseLikeResponse createIncreaseLikeResponse() {
        return new IncreaseLikeResponse();
    }

    /**
     * Create an instance of {@link RetrieveId }
     * 
     */
    public RetrieveId createRetrieveId() {
        return new RetrieveId();
    }

    /**
     * Create an instance of {@link RetrieveIdResponse }
     * 
     */
    public RetrieveIdResponse createRetrieveIdResponse() {
        return new RetrieveIdResponse();
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
     * Create an instance of {@link RetrieveSales }
     * 
     */
    public RetrieveSales createRetrieveSales() {
        return new RetrieveSales();
    }

    /**
     * Create an instance of {@link RetrieveSalesResponse }
     * 
     */
    public RetrieveSalesResponse createRetrieveSalesResponse() {
        return new RetrieveSalesResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link Purchase }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace/", name = "Purchase")
    public JAXBElement<Purchase> createPurchase(Purchase value) {
        return new JAXBElement<Purchase>(_Purchase_QNAME, Purchase.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace/", name = "addProduct")
    public JAXBElement<AddProduct> createAddProduct(AddProduct value) {
        return new JAXBElement<AddProduct>(_AddProduct_QNAME, AddProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace/", name = "addProductResponse")
    public JAXBElement<AddProductResponse> createAddProductResponse(AddProductResponse value) {
        return new JAXBElement<AddProductResponse>(_AddProductResponse_QNAME, AddProductResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLiked }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace/", name = "getLiked")
    public JAXBElement<GetLiked> createGetLiked(GetLiked value) {
        return new JAXBElement<GetLiked>(_GetLiked_QNAME, GetLiked.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLikedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace/", name = "getLikedResponse")
    public JAXBElement<GetLikedResponse> createGetLikedResponse(GetLikedResponse value) {
        return new JAXBElement<GetLikedResponse>(_GetLikedResponse_QNAME, GetLikedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IncreaseLike }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace/", name = "increaseLike")
    public JAXBElement<IncreaseLike> createIncreaseLike(IncreaseLike value) {
        return new JAXBElement<IncreaseLike>(_IncreaseLike_QNAME, IncreaseLike.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IncreaseLikeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace/", name = "increaseLikeResponse")
    public JAXBElement<IncreaseLikeResponse> createIncreaseLikeResponse(IncreaseLikeResponse value) {
        return new JAXBElement<IncreaseLikeResponse>(_IncreaseLikeResponse_QNAME, IncreaseLikeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace/", name = "retrieveId")
    public JAXBElement<RetrieveId> createRetrieveId(RetrieveId value) {
        return new JAXBElement<RetrieveId>(_RetrieveId_QNAME, RetrieveId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace/", name = "retrieveIdResponse")
    public JAXBElement<RetrieveIdResponse> createRetrieveIdResponse(RetrieveIdResponse value) {
        return new JAXBElement<RetrieveIdResponse>(_RetrieveIdResponse_QNAME, RetrieveIdResponse.class, null, value);
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

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveSales }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace/", name = "retrieveSales")
    public JAXBElement<RetrieveSales> createRetrieveSales(RetrieveSales value) {
        return new JAXBElement<RetrieveSales>(_RetrieveSales_QNAME, RetrieveSales.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetrieveSalesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://marketplace/", name = "retrieveSalesResponse")
    public JAXBElement<RetrieveSalesResponse> createRetrieveSalesResponse(RetrieveSalesResponse value) {
        return new JAXBElement<RetrieveSalesResponse>(_RetrieveSalesResponse_QNAME, RetrieveSalesResponse.class, null, value);
    }

}
