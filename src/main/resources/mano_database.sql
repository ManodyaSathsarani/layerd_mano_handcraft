CREATE TABLE categories (
    category_id VARCHAR(100)  PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL
);

CREATE TABLE customers (
    customer_id VARCHAR(100)  PRIMARY KEY,
    customer_name VARCHAR(100),
    phone VARCHAR(20),
    address TEXT,
    email VARCHAR(100)

);

CREATE TABLE employees (
    employee_id VARCHAR(100)  PRIMARY KEY,
    employee_name VARCHAR(100),
    role VARCHAR(50),
    hire_date DATE,
    phone VARCHAR(20),
    phone TEXT
);



CREATE TABLE ingredients (
    ingredient_id VARCHAR(100)  PRIMARY KEY,
    ingredient_name VARCHAR(100),
    unit VARCHAR(50)
);



CREATE TABLE inventory (
    inventory_id VARCHAR(100)  PRIMARY KEY,
    ingredient_id VARCHAR(100),
    quantity_in_stock DECIMAL(10,2),
    last_updated DATETIME,
    FOREIGN KEY (ingredient_id) REFERENCES ingredients(ingredient_id)
);

CREATE TABLE orders (
    order_id VARCHAR(100)  PRIMARY KEY,
    customer_id VARCHAR(100),
    employee_id VARCHAR(100),
    order_date DATETIME,
    total_amount DECIMAL(10,2),
    product_id VARCHAR(100),
    quantity INT,
    price DECIMAL,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);
CREATE TABLE products (
    product_id VARCHAR(100)  PRIMARY KEY,
    product_name VARCHAR(100),
    category_id VARCHAR(100),
    price DECIMAL(10,2),
    description TEXT,
    FOREIGN KEY (category_id) REFERENCES categories(category_id)
);


CREATE TABLE payment (
    payment_id VARCHAR(100)  PRIMARY KEY,
    order_id VARCHAR(100),
    amount DECIMAL(10,2),
    method VARCHAR(50),
    status VARCHAR(50),
    payment_date DATETIME,
    FOREIGN KEY (order_id) REFERENCES orders(order_id)
);



CREATE TABLE product_ingredients (
    product_id VARCHAR(100),
    ingredient_id VARCHAR(100),
    quantity DECIMAL(10,2),
    PRIMARY KEY (product_id, ingredient_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id),
    FOREIGN KEY (ingredient_id) REFERENCES ingredients(ingredient_id)
);

CREATE TABLE suppliers (
    supplier_id VARCHAR(100) PRIMARY KEY,
    supplier_name VARCHAR(100),
    contact_info TEXT
);


CREATE TABLE users (
    user_id VARCHAR(100) PRIMARY KEY,
    name VARCHAR(100),
    username VARCHAR(100),
    password VARCHAR(255),
    role VARCHAR(50),
    Registration_date DATE
);