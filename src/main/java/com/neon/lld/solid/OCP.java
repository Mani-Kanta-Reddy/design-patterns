package com.neon.lld.solid;

import java.util.List;

public class OCP {
    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product("TV", Product.Color.BLUE, Product.Category.GADGETS),
                new Product("Chudidar", Product.Color.GREEN, Product.Category.CLOTHING),
                new Product("Palazzo Sets", Product.Color.RED, Product.Category.CLOTHING),
                new Product("T Shirt", Product.Color.GREEN, Product.Category.CLOTHING),
                new Product("Pads", Product.Color.BLUE, Product.Category.SANITORY)
        );
//        ProductFilter productFilter = new ProductFilter(products);
//        System.out.println(productFilter.filterByColor(Product.Color.GREEN));
//        System.out.println(productFilter.filterByCategory(Product.Category.CLOTHING));

        BetterFilter betterFilter = new BetterFilter();
        System.out.println(betterFilter.filter(products, new ColorSpecification(Product.Color.GREEN)));
        System.out.println(betterFilter.filter(products, new CategorySpecification(Product.Category.CLOTHING)));
        System.out.println(betterFilter.filter(products, new AndSpecification(
                new ColorSpecification(Product.Color.GREEN),
                new CategorySpecification(Product.Category.CLOTHING)
        )));
        System.out.println(betterFilter.filter(products, new OrSpecification(
                new ColorSpecification(Product.Color.GREEN),
                new CategorySpecification(Product.Category.CLOTHING)
        )));
    }
}

class Product {
    String name;
    enum Color {
        BLUE,
        GREEN,
        RED
    }

    Color color;
    enum Category {
        CLOTHING,
        GADGETS,
        SANITORY
    }

    Category category;

    public Product(String name, Color color, Category category) {
        this.name = name;
        this.color = color;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
               "name='" + name + '\'' +
               ", color=" + color +
               ", category=" + category +
               '}';
    }
}

// BAD
class ProductFilter {
    List<Product> productList;

    public ProductFilter(List<Product> productList) {
        this.productList = productList;
    }

    // filter by color
    public List<Product> filterByColor(Product.Color color) {
        return productList.stream()
                .filter(p -> p.color == color)
                .toList();
    }

    public List<Product> filterByCategory(Product.Category category) {
        return productList.stream()
                .filter(p -> p.category == category)
                .toList();
    }
}

// OCP + Specification Pattern
// GOOD
interface Specification<T> {
    boolean isSatisfied(T item);
}

interface Filter<T>  {
    List<T> filter(List<T> items, Specification<T> specification);
}

class BetterFilter implements Filter<Product> {
    @Override
    public List<Product> filter(List<Product> items, Specification<Product> specification) {
        return items.stream().filter(item -> specification.isSatisfied(item)).toList();
    }
}

class ColorSpecification implements Specification<Product> {
    Product.Color color;

    public ColorSpecification(Product.Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.color == color;
    }
}

class CategorySpecification implements Specification<Product> {
    Product.Category category;

    public CategorySpecification(Product.Category category) {
        this.category = category;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return this.category == item.category;
    }
}

class AndSpecification implements Specification<Product> {
    Specification<Product> left, right;

    public AndSpecification(Specification<Product> left, Specification<Product> right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return left.isSatisfied(item) && right.isSatisfied(item);
    }
}

class OrSpecification implements Specification<Product> {
    Specification<Product> left, right;

    public OrSpecification(Specification<Product> left, Specification<Product> right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return left.isSatisfied(item) || right.isSatisfied(item);
    }
}

