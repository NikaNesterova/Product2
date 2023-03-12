import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ProductManagerTest {

    private ProductRepository repo = new ProductRepository();
    private ProductManager manager = new ProductManager(repo);

    private Product book1 = new Book(1, "Idiot", 100, "Dostoevsky");
    private Product book2 = new Book(2, "Anna Karenina", 400, "Tolstoy");
    private Product book3 = new Book(3, "MasterAndMargarita", 200, "Bulgakov");
    private Product smartphone1 = new Smartphone(4, "Samsung A70", 40000, "China");
    private Product smartphone2 = new Smartphone(5, "Honor X", 25000, "China");
    private Product smartphone3 = new Smartphone(6, "Iphone X", 30000, "USA");

    @BeforeEach

    public void SetUp() {
        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);
    }

    @Test

    public void shouldAddProductGetItems() {
        Product[] actual = new Product[]{book1, book2, book3, smartphone1, smartphone2, smartphone3};
        Product[] expected = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test

    public void shouldSearchBySmartphoneName() {
        Product[] actual = manager.searchBy("Samsung A70");
        Product[] expected = new Product[]{smartphone1};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByBookName() {
        Product[] actual = manager.searchBy("Anna Karenina");
        Product[] expected = new Product[]{book2};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test

    public void shouldSearchByNoSmartphoneName() {
        Product[] actual = manager.searchBy("Huawei");
        Product[] expected = new Product[0];

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByNoBookName() {
        Product[] actual = manager.searchBy("WarAndPeace");
        Product[] expected = new Product[0];

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByTwoParametersInBook() {
        Product[] actual = manager.searchBy("Anna Karenina");
        Product[] actual2 = manager.searchBy("Tolstoy");
        Product[] expected = new Product[]{book2};
        Assertions.assertArrayEquals(expected, actual, Arrays.toString(actual2));
    }

    @Test
    public void searchByTwoParametersInSmartphone() {
        Product[] actual = manager.searchBy("Iphone X");
        Product[] actual2 = manager.searchBy("USA");
        Product[] expected = new Product[]{smartphone3};

        Assertions.assertArrayEquals(expected, actual, Arrays.toString(actual2));
    }

    @Test

    public void shouldSearchByProduct() {
        Product[] actual = manager.searchBy("Idiot");
        Product[] expected = new Product[]{book1};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test

    public void shouldSearchBy() {
        Product[] actual = manager.searchBy("Idiot");
        Product[] actual2 = manager.searchBy("Iphone X");
        Product[] expected = new Product[]{book1};

        Assertions.assertArrayEquals(expected, actual, Arrays.toString(actual2));
    }

    @Test

    public void shouldSearchByProductTo() {

        Product[] actual = manager.searchBy("14 Max");
        Product[] expected = new Product[]{};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test

    public void shouldSearchByBookNameOne() {

        Product[] actual = manager.searchBy("Anna");
        Product[] expected = new Product[]{book2};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test

    public void shouldSearchTwoSmartphones() {

        Product[] expected = new Product[]{smartphone2, smartphone3};
        Product[] actual = manager.searchBy("X");

        Assertions.assertArrayEquals(expected, actual);
    }
}
