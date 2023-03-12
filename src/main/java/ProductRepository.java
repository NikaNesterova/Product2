public class ProductRepository {

    private Product[] items = new Product[0];

    public Product[] findAll() {
        return items;
    }

    public Product findById(int id) {
        for (Product item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void save(Product item) {
        Product[] tmp = new Product[items.length + 1];
        for (int i = 0; i < items.length; i++) {
            tmp[i] = items[i];
        }
        tmp[tmp.length - 1] = item;
        items = tmp;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }
        Product[] tmp = new Product[items.length - 1];
        int CopyToIndex = 0;
        for (Product item : items) {
            if (item.getId() != id) {
                tmp[CopyToIndex] = item;
                CopyToIndex++;
            }
        }
        items = tmp;
    }

}