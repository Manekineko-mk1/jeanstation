@SpringBootTest
public class ProductRepositoryIntegrationTest {

    @Autowired
    private ProductRepository productRepository;
    private Product product;

    @BeforeEach
    public void setUp() {
        List<Product> reviews = new ArrayList<>();
        product = new Product();
        product.setId(1);
        product.setName("product1");
        product.setPrice(10.00);
        product.setDiscount(0.1);
        product.setDimension("1x1x1");
        product.setQuantity(20);
        product.setReview(reviews);
    }

    @AfterEach
    public void tearDown() {
        productRepository.deleteAll();
        product = null;
    }

    @Test
    public void givenProductToSaveThenShouldReturnSavedProduct() {
        productRepository.save(product);
        Product fetchedproduct = productRepository.findById(product.getId()).get();
        assertEquals(1, fetchedproduct.getId());
    }


    @Test
    public void givenGetAllProductsThenShouldReturnListOfAllProducts() {
        Product product = new Product(2, "product2", 20.05, 0.0, "2x2x2", 10, reviews);
        Product product1 = new Product(3, "product3", 15.99, 0.25, "3x3x3", 15, reviews);
        productRepository.save(product);
        productRepository.save(product1);

        List<Product> productList = (List<Product>) productRepository.findAll();
        assertEquals("product3", productList.get(1).getName());
    }

    @Test
    public void givenProductIdThenShouldReturnRespectiveProduct() {
        Product product = new Product(9, "product9", 30.25, 0.12, "9x9x9", 56, reviews);
        Product product1 = productRepository.save(product);
        Optional<Product> optional = productRepository.findById(product1.getId());
        assertEquals(product1.getId(), optional.get().getId());
        assertEquals(product1.getName(), optional.get().getName());
        assertEquals(product1.getPrice(), optional.get().getPrice());
        assertEquals(product1.getDiscount(), optional.get().getDiscount());
        assertEquals(product1.getDimension(), optional.get().getDimension());
        assertEquals(product1.getQuantity(), optional.get().getQuantity());
    }

    @Test
    public void givenProductIdToDeleteThenShouldReturnDeletedProduct() {
        Product product = new Product(4, "product4", 1.99, 0.05 , "4x4x4", 106, reviews);
        productRepository.save(product);
        productRepository.deleteById(product.getId());
        Optional optional = productRepository.findById(product.getId());
        assertEquals(Optional.empty(), optional);
    }
    
}
