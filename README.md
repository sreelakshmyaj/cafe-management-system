# Cafe Management System — Bistro Blend

A Java Swing application for cafe operations, featuring role-based access, menu management, order placement, and order deletion. The app is designed with clear separation between UI frames, models, and database connectivity, and uses MySQL via JDBC.

## Features

### Welcome and Home navigation
- **WelcomePage**: entry screen with Admin/Customer login.
- **CafeHome**: routes to WelcomePage or HomePage depending on session.
- **HomePage**: main dashboard with navigation to View Menu, Place Order, Edit Menu, and View/Delete Orders.

### Authentication and user management
- **Login**: username/password authentication for admin and customer roles.
- **Register**: user signup with id, name, username, password (confirmation), phone, and role.
- **CurrentUser**: singleton storing the logged-in User session.

### Menu management
- **MenuModel**: CRUD for menu items (getAllItems, addItem, deleteItem, updatePrice).
- **MenuItem**: item entity (id, name, price, category).
- **MenuList**: holds a list of MenuItem and populates from DB.
- **MenuCard/Menu display**: UI component to show menu items.
- **EditMenu**: UI workflow to add, delete, or update menu items.

### Order management
- **PlaceOrder**: UI to create and place orders, with total updates.
- **DeleteOrder**: UI to view and delete existing orders.
- **OrderModel**: DB operations to add orders, add order items, get orders, delete orders, and fetch all orders.

### Database connectivity
- **DbCon**: JDBC connection manager for MySQL (shared by models).

## Tech Stack

- **Language**: Java
- **UI**: Swing
- **Database**: MySQL
- **Persistence**: JDBC
- **Architecture**: MVC-style separation between UI (frames), models, and DB layer

## Class Overview

### cafe
- **CafeHome**: holds WelcomePage and HomePage; toggles visibility based on CurrentUser.
- **WelcomePage**: role selection and navigation to Login.
- **HomePage**: main actions (view menu, place orders, edit menu, view/delete orders).
- **MenuCard**: displays items (displayItems()).
- **EditMenu**: add/update/delete menu flows (displayAddItemForm, displayDeleteItemForm, displayUpdateItemForm).
- **PlaceOrder**: place() and updateTotal() actions.
- **DeleteOrder**: displayOrders().

### models
- **MenuItem**: itemId, name, price, category.
- **MenuList**: setFromDB(), getItems().
- **MenuModel**: getAllItems(), addItem(), deleteItem(), updatePrice().
- **OrderModel**: addToOrders(), addToOrderItems(), getMyOrders(), deleteOrder(), getAllOrders().
- **UserModel**: registerUser(), loginUser().

### usermanager
- **Login**: login form and logic.
- **Register**: registration form and logic.
- **User**: id, username, name, phone, role.
- **CurrentUser**: singleton (getInstance, set/get current user).

### databaseconfig
- **DbCon**: getConnection(), closeConnection().


## Database Setup

### 1) Create database
- Create a MySQL database (default name: cafe). Adjust as needed.

### 2) Connection configuration
- Default in DbCon.java (replace with your values):
  - URL: `jdbc:mysql://localhost:3307/cafe`
  - USER: `root`
  - PASSWORD: `0113`

### 3) Suggested schema

Menu and orders:

```sql
CREATE TABLE user (
  id INT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  username VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  phone VARCHAR(20) NOT NULL,
  role VARCHAR(20) NOT NULL
);

CREATE TABLE category (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE menu_item (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(120) NOT NULL,
  category_id INT,
  price DECIMAL(10,2) NOT NULL,
  is_active TINYINT(1) DEFAULT 1,
  FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE orders (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  subtotal DECIMAL(10,2) NOT NULL,
  tax DECIMAL(10,2) NOT NULL,
  discount DECIMAL(10,2) NOT NULL,
  total DECIMAL(10,2) NOT NULL,
  status VARCHAR(20) DEFAULT 'PLACED',
  FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE order_item (
  order_id INT NOT NULL,
  item_id INT NOT NULL,
  quantity INT NOT NULL,
  unit_price DECIMAL(10,2) NOT NULL,
  line_total DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (order_id, item_id),
  FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
  FOREIGN KEY (item_id) REFERENCES menu_item(id)
);
```

Adjust names/columns to match your models if they differ.

## Building and Running

- Make sure the MySQL JDBC driver (mysql-connector-j) is on the classpath.
- **Option A: IDE**
  - Import as a Java project and run your entry point (e.g., CafeHome or a Main class).
- **Option B: CLI**
  - Compile with the driver on classpath and run your Main as shown earlier.

Example Main:

```java
public class Main {
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(() -> {
      new cafe.CafeHome(); // internally shows Welcome or Home based on session
    });
  }
}
```

## Usage

- Start the app.
- **WelcomePage**:
  - Choose Admin Login or Customer Login to open Login with that role context.
- **Login**:
  - Enter credentials. On success, CurrentUser is set and the app routes to HomePage.
- **Register**:
  - If new, open Register from Login. Provide id, name, username, password, confirm password, and phone. The role is taken from the login path (admin/customer).
- **HomePage**:
  - **View Menu**: display items via MenuCard/MenuList.
  - **Place Order**: build an order, update totals, and place().
  - **Edit Menu**: add, delete, or update item price using EditMenu and MenuModel.
  - **View/Delete Orders**: list existing orders and delete as needed.

## Configuration

- Database credentials in `databaseconfig/DbCon.java`.
- Optional: move credentials to `src/main/resources/application.properties` and read them in DbCon.

Example properties:

```
db.url=jdbc:mysql://localhost:3307/cafe
db.user=root
db.password=0113
```


## Roadmap

- Secure auth (hashing + salted passwords).
- Robust menu and order screens with search/filtering.
- Billing/receipt generation (print/PDF).
- Reports (daily sales, top items).
- Config file support and environment-based settings.
- Logging and exception handling.
- Unit tests for models and DB layer.


## Credits

- **Author**: Sreelakshmy A J
- **Branding**: "Bistro Blend: Blending Tastes, Crafting Moments"
