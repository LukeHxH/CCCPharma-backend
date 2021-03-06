package br.edu.ufcg.ccc.pharma.product;

import br.edu.ufcg.ccc.pharma.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productDAO;

    @Autowired
    public ProductService(ProductRepository productDAO) {
        this.productDAO = productDAO;
    }

    public Product save(Product product) {
        boolean isAvaliable = product.getAmount() > 0;
        product.setAvailable(isAvaliable);
        return productDAO.save(product);
    }

    public void delete(long id) { productDAO.deleteById(id);}
    public Product findById(long id) {
        Product product = null;
        Optional<Product> optional = productDAO.findById(id);
        if (optional.isPresent())
            product = optional.get();
        return product;
    }
    public List<Product> findAll() { return (List<Product>) productDAO.findAll(); }

    public Product update(long id, Product product) {
        product.setId(id);
        return save(product);
    }

    public Product getProduct(Long id) {
        return this.productDAO
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
}
